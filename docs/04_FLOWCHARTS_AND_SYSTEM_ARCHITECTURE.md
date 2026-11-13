# Flowcharts and System Architecture

**Project Title:** Alumni Directory & Networking Management System  
**Document Code:** PIET-NSP-ARCH-04  
**Student Name:** ARPEET UPADHAYAY (PIET25CR007)  
**Session:** 2026–27 | **Section:** 3CSD  
**College:** Poornima Institute of Engineering & Technology (PIET)  

---

## 1. System Architecture

The application adopts a clean, three-tier modular architectural pattern:
1. **Presentation Layer (Swing GUI):** Desktop frames, panels, custom tables, form inputs, and dialogs (`LoginFrame`, `AdminDashboardFrame`, `UserDashboardFrame`, `ManageAlumniPanel`, `AlumniDirectoryPanel`, `ChangePasswordDialog`).
2. **Data Access Layer (DAO Pattern):** Java classes encapsulating database queries (`UserDAO`, `AlumniDAO`, `DBConnection`) using parameterized `PreparedStatement`.
3. **Data Storage Layer (MySQL Database):** Relational tables hosted locally (`alumni_db` with `users` and `alumni` tables).

```mermaid
graph TD
    subgraph Client [Presentation Tier - Java Swing]
        LF[LoginFrame]
        ADF[AdminDashboardFrame]
        UDF[UserDashboardFrame]
        MAP[ManageAlumniPanel]
        ADP[AlumniDirectoryPanel]
        CPD[ChangePasswordDialog]
    end

    subgraph Logic [Data Access Tier - DAOs]
        UDAO[UserDAO]
        ADAO[AlumniDAO]
        DBC[DBConnection]
    end

    subgraph Storage [Database Tier - MySQL 8.4]
        T_USERS[(users)]
        T_ALUMNI[(alumni)]
    end

    LF --> UDAO
    ADF --> MAP
    ADF --> ADP
    ADF --> CPD
    UDF --> ADP
    UDF --> CPD

    MAP --> ADAO
    ADP --> ADAO

    UDAO --> DBC
    ADAO --> DBC

    DBC --> T_USERS
    DBC --> T_ALUMNI
```

---

## 2. Authentication & Role Routing Flowchart

```mermaid
flowchart TD
    Start([User Launches Application]) --> ShowLogin[Display LoginFrame]
    ShowLogin --> EnterCreds[User Enters Username & Password]
    EnterCreds --> ClickLogin{User Clicks Login}
    
    ClickLogin --> ValidateInput{Empty Username or Password?}
    ValidateInput -- Yes --> WarnEmpty[Show Warning Dialog: Enter Credentials]
    WarnEmpty --> ShowLogin
    
    ValidateInput -- No --> CheckDB{Database Reachable?}
    CheckDB -- No --> ShowDBErr[Show Error: Database Connection Failure]
    ShowDBErr --> ShowLogin
    
    CheckDB -- Yes --> QueryUser[Execute UserDAO.authenticate]
    QueryUser --> AuthSuccess{User Exists & Password Matches?}
    
    AuthSuccess -- No --> ShowAuthErr[Show Error: Invalid Credentials]
    ShowAuthErr --> ShowLogin
    
    AuthSuccess -- Yes --> CheckRole{Role == 'Admin'?}
    CheckRole -- Yes --> OpenAdmin[Open AdminDashboardFrame & Close Login]
    CheckRole -- No --> OpenUser[Open UserDashboardFrame & Close Login]
    
    OpenAdmin --> EndAdmin([Admin Session Active])
    OpenUser --> EndUser([User Session Active])
```

---

## 3. Alumni Record Management Flowchart (Admin CRUD)

```mermaid
flowchart TD
    Start([Admin Opens Manage Alumni Tab]) --> ViewTable[Display All Records in JTable]
    
    ViewTable --> ActionChoice{Admin Action}
    
    ActionChoice -- Add Alumni --> EnterFields[Enter Name, Year, Dept, Org, Role, City, Email, Phone]
    EnterFields --> ValidateAdd{All Fields Filled & Valid Year/Email/Phone?}
    ValidateAdd -- No --> WarnAdd[Show Validation Error Dialog]
    ValidateAdd -- Yes --> ExecAdd[Execute AlumniDAO.addAlumni]
    ExecAdd --> RefreshTable1[Refresh JTable, Clear Form & Show Success]
    RefreshTable1 --> ViewTable
    
    ActionChoice -- Select Row --> ClickRow[Click Row in JTable]
    ClickRow --> PopulateForm[Populate Fields in Form & Enable Update/Delete]
    
    PopulateForm --> ClickUpdate{Admin Clicks Update?}
    ClickUpdate -- Yes --> ValidateUpd{Valid Data & Year/Email/Phone?}
    ValidateUpd -- Yes --> ExecUpd[Execute AlumniDAO.updateAlumni]
    ExecUpd --> RefreshTable2[Refresh JTable & Show Success]
    RefreshTable2 --> ViewTable
    
    PopulateForm --> ClickDelete{Admin Clicks Delete?}
    ClickDelete -- Yes --> ConfirmDel{Confirm: Are you sure you want to delete?}
    ConfirmDel -- Yes --> ExecDel[Execute AlumniDAO.deleteAlumni]
    ExecDel --> RefreshTable3[Refresh JTable, Clear Form & Show Success]
    ConfirmDel -- No --> CancelDel[Action Cancelled]
    RefreshTable3 --> ViewTable
    CancelDel --> ViewTable
    
    ActionChoice -- Quick Search --> EnterTerm[Enter Search Term / Filter Dept]
    EnterTerm --> ExecQuick[Execute AlumniDAO.searchAlumni]
    ExecQuick --> ShowFiltered[Display Filtered Rows in JTable]
    ShowFiltered --> ViewTable
```

---

## 4. Multi-Criteria Directory Search & Networking Profile Flowchart

```mermaid
flowchart TD
    Start([User Opens Alumni Directory]) --> LoadCatalog[Load All Alumni into Directory JTable]
    LoadCatalog --> UserAction{User Action}
    
    UserAction -- Enter Filters --> SetFilters[Enter Name, Select Dept, Enter Org, Enter City]
    SetFilters --> ClickSearch[Click 'Search' Button]
    ClickSearch --> QueryFilters[Execute AlumniDAO.searchByCriteria]
    QueryFilters --> RenderResults[Display Matching Alumni Records in JTable]
    RenderResults --> UpdateCount[Update Status: Showing N of Total Alumni]
    UpdateCount --> UserAction
    
    UserAction -- Click Reset --> ResetFilters[Clear All Search TextFields & Set Dept to 'All']
    ResetFilters --> QueryAll[Execute AlumniDAO.getAllAlumni]
    QueryAll --> RenderAll[Reload All Records into JTable]
    RenderAll --> UserAction
    
    UserAction -- Select Alumni Row --> ClickTable[Click Alumni Row in Table]
    ClickTable --> FetchDetails[Retrieve Selected Alumni Details]
    FetchDetails --> RenderCard[Render Professional Networking Profile Card]
    RenderCard --> ShowInfo[Display: Name, Batch Year, Dept, Org, Designation, City, Email, Phone]
    ShowInfo --> UserAction
```

---

## 5. Account Password Change Flowchart

```mermaid
flowchart TD
    Start([User / Admin Clicks Change Password]) --> OpenModal[Open ChangePasswordDialog Modal]
    OpenModal --> EnterFields[Enter Current Password, New Password, Confirm New Password]
    EnterFields --> ClickSave{Click Save Changes}
    
    ClickSave --> CheckEmpty{Any Field Blank?}
    CheckEmpty -- Yes --> WarnBlank[Show Warning: All fields are required]
    WarnBlank --> EnterFields
    
    CheckEmpty -- No --> CheckMatch{New Password == Confirm Password?}
    CheckMatch -- No --> WarnMismatch[Show Warning: New passwords do not match]
    WarnMismatch --> EnterFields
    
    CheckMatch -- Yes --> CheckLen{Password Length >= 4?}
    CheckLen -- No --> WarnLen[Show Warning: Password must be at least 4 characters]
    WarnLen --> EnterFields
    
    CheckLen -- Yes --> VerifyOld[UserDAO.changePassword: Verify Old Password]
    VerifyOld --> OldValid{Old Password Correct?}
    OldValid -- No --> WarnOld[Show Error: Current password is incorrect]
    WarnOld --> EnterFields
    
    OldValid -- Yes --> UpdatePass[Persist New Password to MySQL]
    UpdatePass --> ShowSuccess[Show Success Dialog & Dispose Modal]
    ShowSuccess --> Done([Password Updated Successfully])
```
