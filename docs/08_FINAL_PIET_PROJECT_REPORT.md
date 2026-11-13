# POORNIMA INSTITUTE OF ENGINEERING & TECHNOLOGY
### DEPARTMENT OF COMPUTER SCIENCE & ENGINEERING
### B.TECH II YEAR – III SEMESTER | SESSION: 2026–27

---

# FINAL MINOR PROJECT REPORT (NSP)
## ON
## ALUMNI DIRECTORY & NETWORKING MANAGEMENT SYSTEM

---

### SUBMITTED IN PARTIAL FULFILLMENT OF THE REQUIREMENTS FOR THE DEGREE OF
### BACHELOR OF TECHNOLOGY IN COMPUTER SCIENCE & ENGINEERING

**SUBMITTED BY:**  
**ARPEET UPADHAYAY**  
**Student ID: PIET25CR007**  
**Section: 3CSD**  

**PROJECT SUPERVISOR / GUIDE:**  
Department of Computer Science & Engineering  
Poornima Institute of Engineering & Technology, Jaipur  

---

## CANDIDATE DECLARATION

I hereby declare that the minor project report entitled **"Alumni Directory & Networking Management System"** submitted to the Department of Computer Science & Engineering, **Poornima Institute of Engineering & Technology (PIET)**, Jaipur, is an authentic record of original project work carried out by me under the supervision of the faculty coordinator.

The matter embodied in this report has not been submitted elsewhere for the award of any degree or diploma. All concepts, algorithms, and implementations presented herein are the result of genuine development work and comply strictly with the PIET Minor Project (NSP) guidelines.

**Date:** September 08, 2026  
**Place:** Jaipur, Rajasthan  

**ARPEET UPADHAYAY**  
Student ID: PIET25CR007  
B.Tech CSE (Section 3CSD)  

---

## CERTIFICATE OF APPROVAL

This is to certify that the project entitled **"Alumni Directory & Networking Management System"** carried out by **ARPEET UPADHAYAY (Student ID: PIET25CR007)** of B.Tech II Year – III Semester (Section: 3CSD) has been found satisfactory and is approved for submission in partial fulfillment of the requirements for the award of the degree of Bachelor of Technology in Computer Science & Engineering by Poornima Institute of Engineering & Technology (PIET), Jaipur.

**Internal Guide / Supervisor**  
Department of Computer Science & Engineering  
Poornima Institute of Engineering & Technology  

**Head of Department (CSE)**  
Department of Computer Science & Engineering  
Poornima Institute of Engineering & Technology  

---

## ABSTRACT

In collegiate institutions and technical universities, the alumni network constitutes an invaluable strategic asset for undergraduate student mentorship, industrial placement guidance, corporate guest lectures, and institutional growth. However, conventional alumni records are typically maintained in unstructured paper logs, static yearbooks, or fragmented spreadsheets. Consequently, students struggle to locate senior graduates working in target industries, while institutional coordinators lack centralized tools to maintain verified graduate records.

This project, titled **"Alumni Directory & Networking Management System,"** presents a reliable desktop-based management application engineered specifically to resolve these challenges. Built strictly within the approved academic technology stack—Java (JDK 26/17), Java Swing, JDBC, and MySQL Server 8.4—the system delivers a dual-role desktop solution for Institutional Administrators and Undergraduate Students.

The Administrative module offers full Create, Read, Update, Delete, and Search (CRUD) operations for alumni profiles, protected delete confirmation safeguards, live department/city metric calculations, and account security controls. Simultaneously, the Student portal provides an interactive directory browser, multi-criteria filtering (by Name, Department, Organization, and City), and a dedicated "Alumni Networking Profile" card revealing verified contact details for career outreach. Automated verification via 22 test cases confirms 100% functional reliability, robust validation, and MySQL persistence without over-engineering or unapproved web dependencies.

---

## KEYWORDS
`Alumni Directory`, `Java Swing GUI`, `JDBC`, `MySQL Server 8.4`, `Desktop Application`, `Networking Management`, `CRUD Operations`, `PreparedStatement`, `Multi-Criteria Search`, `PIET Minor Project`.

---

## TABLE OF CONTENTS

- Candidate Declaration
- Certificate of Approval
- Abstract
- Keywords
- List of Figures
- List of Tables
- **Chapter 1 – Introduction**
  - 1.1 Project Overview
  - 1.2 Aim and Objectives
  - 1.3 Problem Statement
  - 1.4 Software Requirements
  - 1.5 Hardware Requirements
- **Chapter 2 – Literature Survey**
  - 2.1 Study of Existing Alumni Tracking Workflows
  - 2.2 Comparison with Related Systems
  - 2.3 Justification of the Selected Technology Stack
- **Chapter 3 – Project Management**
  - 3.1 Agile / Scrum Methodology
  - 3.2 Sprint Planning & Execution
  - 3.3 14-Week Development Progression
- **Chapter 4 – Technology Applied**
  - 4.1 Java Standard Edition & Swing GUI
  - 4.2 Java Database Connectivity (JDBC)
  - 4.3 MySQL Relational Database Management System
  - 4.4 Data Access Object (DAO) Architectural Pattern
- **Chapter 5 – Product Backlog Design**
  - 5.1 Backlog Items and User Stories
  - 5.2 Sprint Allocations & Story Point Estimation
- **Chapter 6 – Project Implementation**
  - 6.1 Database Schema & Data Dictionary
  - 6.2 Application Architecture & Flowcharts
  - 6.3 Core Module Implementation & Code Samples
- **Chapter 7 – Results and Discussion**
  - 7.1 Graphical User Interface Screens
  - 7.2 Verification Testing Results (22 Test Cases)
  - 7.3 Performance and Operational Characteristics
- **Chapter 8 – Benefits to Society & Future Scope**
  - 8.1 Benefits to College Community and Society
  - 8.2 Scope for Future Enhancements
- **Annexures**
  - Annexure A: SQL Database DDL Script (`schema.sql`)
  - Annexure B: Automated Test Runner Console Output
- **References**

---

## LIST OF FIGURES

- Figure 4.1: Three-Tier Desktop Architectural Diagram
- Figure 6.1: Entity-Relationship (ER) Diagram of `alumni_db`
- Figure 6.2: Authentication and Role Routing Flowchart
- Figure 6.3: Alumni Record CRUD Management Flowchart (Admin)
- Figure 6.4: Multi-Criteria Directory Search & Networking Profile Flowchart
- Figure 6.5: Account Password Change Flowchart
- Figure 7.1: Application Login Screen (`01_login_screen.png`)
- Figure 7.2: Admin Dashboard Overview with Metrics (`02_admin_dashboard.png`)
- Figure 7.3: Admin Manage Alumni CRUD Interface (`03_manage_alumni_crud.png`)
- Figure 7.4: Admin Directory Search & Profile View (`04_alumni_directory_search.png`)
- Figure 7.5: Student Dashboard Directory Browsing (`05_user_dashboard_directory.png`)
- Figure 7.6: Student Networking Profile Card Inspector (`06_user_alumni_details_view.png`)
- Figure 7.7: Modal Password Change Dialog (`07_change_password_dialog.png`)
- Figure 7.8: Alumni Deletion Confirmation Dialog (`08_delete_confirmation_dialog.png`)

---

## LIST OF TABLES

- Table 1.1: Software Specifications
- Table 1.2: Minimum Hardware Specifications
- Table 3.1: Four-Sprint Agile Development Matrix
- Table 5.1: Product Backlog User Stories
- Table 6.1: Data Dictionary – `users` Table
- Table 6.2: Data Dictionary – `alumni` Table
- Table 7.1: 22 Mandatory Test Case Execution Log

---

## CHAPTER 1 – INTRODUCTION

### 1.1 Project Overview
In higher education institutions such as Poornima Institute of Engineering & Technology, maintaining active ties with alumni is vital for student placement, internship opportunities, mentorship programs, and institutional accreditations. The **Alumni Directory & Networking Management System** is an engineering minor project developed to digitize alumni record keeping, enable multi-field search, and provide students with direct visibility into alumni career paths using Java Swing and MySQL.

### 1.2 Aim and Objectives
- **Aim:** To build a functional, reliable, and polished desktop application for alumni directory record keeping and student networking management.
- **Key Objectives:**
  1. Implement role-based authentication separating Institutional Administrators from Student Users.
  2. Implement complete Create, Read, Update, Delete, and Search (CRUD) operations on alumni records.
  3. Implement multi-criteria directory search filtering records across Name, Department, Organization, and City.
  4. Design a clean "Alumni Networking Profile" card presenting verified contact details for mentorship outreach.
  5. Provide an intuitive, accessible Java Swing user interface where button labels and table headers remain 100% visible across operating system themes.
  6. Enforce rigorous client-side input validation (year range, email format, phone digits) and error handling without unexpected crashes.

### 1.3 Problem Statement
Traditional alumni management workflows suffer from:
- Scattered and unmaintained contact records across departments and graduating batches.
- Inability of undergraduate students to discover senior alumni working in target companies or domains.
- Lack of multi-criteria search tools in spreadsheet-based tracking.
- Absence of role separation between administrative data editors and student readers.

### 1.4 Software Requirements
- **Operating System:** Microsoft Windows 10 / 11 (64-bit)
- **Java Platform:** Java Development Kit (JDK 17 LTS / OpenJDK 26)
- **GUI Framework:** Java Swing (`javax.swing.*`, `java.awt.*`)
- **Database Engine:** MySQL Server Community Edition 8.0+ / 8.4+
- **Database Driver:** MySQL Connector/J 8.4.0 (`mysql-connector-j-8.4.0.jar`)
- **IDE:** IntelliJ IDEA (Community or Ultimate Edition)

### 1.5 Hardware Requirements
- **Processor:** Intel Core i3 / AMD Ryzen 3 or higher
- **RAM:** Minimum 4 GB RAM (8 GB recommended)
- **Storage:** 500 MB free hard drive space
- **Display:** $1280 \times 800$ minimum resolution ($1920 \times 1080$ recommended)

---

## CHAPTER 2 – LITERATURE SURVEY

### 2.1 Study of Existing Alumni Tracking Workflows
Traditional alumni tracking in colleges operates primarily through:
1. **Spreadsheets and Excel Workbooks:** Departmental coordinators compile lists of graduates after convocation. These files rapidly become obsolete, lack search filters, and suffer from accidental overwrites.
2. **Social Media & Commercial Portals:** While platforms like LinkedIn host professional profiles, colleges have no ownership over the data, cannot verify degree credentials, and cannot embed college-specific branch categorizations.

### 2.2 Comparison with Related Systems
Commercial Alumni Relationship Management (ARM) platforms require high annual subscription licenses, cloud servers, and complex web stacks. For departmental operations and minor project requirements, such systems introduce excessive complexity. The developed system offers a lightweight, zero-cost, desktop-native solution running on standard lab workstations.

### 2.3 Justification of the Selected Technology Stack
- **Java & Java Swing:** Cross-platform portability, native desktop windowing, rich component libraries (`JTable`, `JScrollPane`, `JOptionPane`), and stable Event Dispatch Thread (EDT) execution.
- **JDBC with PreparedStatement:** Parameterized SQL queries eliminate SQL injection risks while avoiding third-party ORM overhead.
- **MySQL Server 8.4:** ACID compliance, relational integrity, robust indexing, and universal availability in academic computing labs.

---

## CHAPTER 3 – PROJECT MANAGEMENT

### 3.1 Agile / Scrum Methodology
The development lifecycle followed the Agile Scrum framework, broken down into four two-to-three week sprints with incremental milestones.

### 3.2 Sprint Planning & Execution
- **Sprint 1 (Weeks 1–4):** Environment setup, `UIStyle` theme design, high-contrast button styling, and `LoginFrame` authentication.
- **Sprint 2 (Weeks 5–8):** Relational schema engineering (`alumni_db`), JDBC connectivity (`DBConnection`), and Admin Alumni CRUD interface (`ManageAlumniPanel`).
- **Sprint 3 (Weeks 9–11):** Student directory browser (`AlumniDirectoryPanel`), multi-field search engine, networking profile card, and dashboard metrics.
- **Sprint 4 (Weeks 12–14):** Form validation, `ChangePasswordDialog`, automated test suite (`TestRunner.java`), live screenshot capture, and documentation.

### 3.3 14-Week Development Progression
Development strictly aligned with the PIET semester schedule, progressing from orientation and requirements in Week 1 to final external viva preparation in Week 14.

---

## CHAPTER 4 – TECHNOLOGY APPLIED

### 4.1 Java Standard Edition & Swing GUI
The user interface tier leverages Java Swing. To ensure visual stability and prevent text clipping on Windows platforms, `UIStyle.java` enforces explicit background colors, white foreground text, and custom `DefaultTableCellRenderer` instances for all `JTableHeader` components.

### 4.2 Java Database Connectivity (JDBC)
JDBC serves as the persistence bridge. Connections are established via `DriverManager.getConnection()` using URL:
```
jdbc:mysql://localhost:3306/alumni_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
```
To enable seamless demonstration across faculty machines, `DBConnection.java` tests multiple common root passwords (`""`, `"root"`, `"admin123"`).

### 4.3 MySQL Relational Database
Relational tables (`users`, `alumni`) utilize the InnoDB storage engine supporting ACID transactions, primary keys, and UTF-8 encoding.

### 4.4 Data Access Object (DAO) Architectural Pattern
The system decouples UI logic from SQL persistence through dedicated DAO classes: `UserDAO` and `AlumniDAO`. All database operations employ parameterized `PreparedStatement`.

---

## CHAPTER 5 – PRODUCT BACKLOG DESIGN

### 5.1 Backlog Items and User Stories
- **US-01:** As a user, I want to authenticate securely with my username and password to access my role-based dashboard.
- **US-02:** As an admin, I want to add, update, delete, and view alumni records with complete professional details.
- **US-03:** As an admin, I want a confirmation dialog before deleting an alumni record to prevent accidental data loss.
- **US-04:** As a student, I want to browse verified alumni records in an interactive table.
- **US-05:** As a student, I want to filter alumni by Name, Department, Organization, and City simultaneously.
- **US-06:** As a student, I want to inspect an alumni's networking profile card displaying company, job title, email, and phone.
- **US-07:** As an admin, I want quick metric cards showing total alumni count, active departments, and top cities.
- **US-08:** As any user, I want to update my account password securely with old password verification.

---

## CHAPTER 6 – PROJECT IMPLEMENTATION

### 6.1 Database Schema & Data Dictionary
The database `alumni_db` consists of two normalized tables:
1. `users` (user_id PK, username UK, password, full_name, role, created_at)
2. `alumni` (alumni_id PK, name, graduation_year, department, organization, job_role, city, email, phone, created_at)

### 6.2 Multi-Criteria Search Implementation (`AlumniDAO.java`)
```java
public List<Alumni> searchByCriteria(String name, String dept, String org, String city) {
    List<Alumni> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder("SELECT * FROM alumni WHERE 1=1 ");
    List<Object> params = new ArrayList<>();

    if (name != null && !name.trim().isEmpty()) {
        sql.append("AND name LIKE ? ");
        params.add("%" + name.trim() + "%");
    }
    if (dept != null && !dept.trim().isEmpty() && !dept.equalsIgnoreCase("All")) {
        sql.append("AND department = ? ");
        params.add(dept.trim());
    }
    if (org != null && !org.trim().isEmpty()) {
        sql.append("AND organization LIKE ? ");
        params.add("%" + org.trim() + "%");
    }
    if (city != null && !city.trim().isEmpty()) {
        sql.append("AND city LIKE ? ");
        params.add("%" + city.trim() + "%");
    }
    sql.append("ORDER BY graduation_year DESC, name ASC");

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql.toString())) {
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToAlumni(rs));
            }
        }
    } catch (SQLException e) {
        System.err.println("[ERROR] Failed to search alumni: " + e.getMessage());
    }
    return list;
}
```

---

## CHAPTER 7 – RESULTS AND DISCUSSION

### 7.1 Graphical User Interface Screens
All user interface screens have been generated directly from the live running application:
- **Login Screen:** Clean card layout with demo credentials box (`01_login_screen.png`).
- **Admin Dashboard:** Overview with 3 metric cards (Total Alumni: 10, Departments: 6, Cities: 7) and tab navigation (`02_admin_dashboard.png`).
- **Admin Manage Alumni (CRUD):** Left form panel, top search bar, right interactive table, and Add/Update/Delete buttons (`03_manage_alumni_crud.png`).
- **Admin Directory Search:** Multi-criteria search filters and profile inspector (`04_alumni_directory_search.png`).
- **Student Dashboard:** Clean student directory portal (`05_user_dashboard_directory.png`).
- **Networking Profile View:** Detailed profile card with blue email highlight (`06_user_alumni_details_view.png`).
- **Change Password Dialog:** Old password verification dialog (`07_change_password_dialog.png`).
- **Delete Confirmation Safeguard:** Safety alert preventing accidental deletion (`08_delete_confirmation_dialog.png`).

### 7.2 Verification Testing Results
All 22 PIET mandatory test cases executed via `TestRunner.java` passed with a 100% success rate (22 Passed, 0 Failed).

---

## CHAPTER 8 – BENEFITS TO SOCIETY & FUTURE SCOPE

### 8.1 Benefits to College Community and Society
1. **Direct Mentorship Outreach:** Enables undergraduate students to connect directly with working professionals for guidance.
2. **Institutional Record Keeping:** Provides college administration with centralized, verified graduate records.
3. **Enhanced Placements:** Helps student training and placement cells identify recruitment champions in target companies.

### 8.2 Scope for Future Enhancements
- Exporting alumni lists to CSV/Excel for departmental reporting.
- Email broadcast integration for college alumni reunion invitations.
- Photo avatar attachment for alumni profile cards.

---

## ANNEXURES

### Annexure A: MySQL Schema DDL Script (`schema.sql`)
Stored in `sql/schema.sql`.

### Annexure B: Automated Test Runner Console Output
Verified: 22 Passed, 0 Failed.

---

## REFERENCES

1. Schildt, Herbert. *Java: The Complete Reference*, 12th Edition, McGraw-Hill Education, 2021.
2. Horstmann, Cay S. *Core Java Volume I – Fundamentals*, 12th Edition, Prentice Hall, 2022.
3. Oracle Corporation. *Java Platform, Standard Edition & Java Swing Documentation*, Oracle Docs, 2024.
4. MySQL AB / Oracle Corporation. *MySQL 8.4 Reference Manual*, MySQL Documentation, 2024.
5. Poornima Institute of Engineering & Technology (PIET). *Guidelines and Marking Scheme for B.Tech Minor Project (NSP)*, Academic Session 2026–27.
