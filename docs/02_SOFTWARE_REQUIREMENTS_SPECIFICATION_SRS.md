# Software Requirements Specification (SRS)
## Alumni Directory & Networking Management System

**Document Version:** 1.0  
**Project:** PIET Minor Project (NSP) - B.Tech II Year (III Sem)  
**Student Name:** ARPEET UPADHAYAY (PIET25CR007)  
**Session:** 2026–27 | **Section:** 3CSD  
**Institution:** Poornima Institute of Engineering & Technology (PIET), Jaipur  

---

## 1. Introduction

### 1.1 Purpose
The purpose of this Software Requirements Specification (SRS) document is to provide a complete, definitive description of the functional, operational, and non-functional requirements for the **Alumni Directory & Networking Management System**. It specifies the external behavior, internal architecture, and database design of the desktop application developed in Java Swing, JDBC, and MySQL for academic evaluation under the PIET Minor Project (NSP) scheme.

### 1.2 Scope of the System
The system is a desktop-based software solution engineered to maintain graduate alumni records, facilitate student-alumni networking, and provide multi-criteria directory search for Poornima Institute of Engineering & Technology. It provides two operational roles:
- **Administrator:** Full administrative rights over alumni records (CRUD: Create, Read, Update, Delete), multi-criteria search, department-level distribution metrics, and password management.
- **User / Student:** Browse verified alumni directories, filter alumni by Name, Department, Organization, and City, inspect detailed networking profile cards for mentorship outreach, and manage student account credentials.

### 1.3 Definitions, Acronyms, and Abbreviations
- **PIET:** Poornima Institute of Engineering & Technology
- **NSP:** Non-Syllabus Project / Minor Project Scheme
- **GUI:** Graphical User Interface (Java Swing)
- **JDBC:** Java Database Connectivity
- **DAO:** Data Access Object Architectural Pattern
- **CRUD:** Create, Read, Update, Delete
- **PK / FK:** Primary Key / Foreign Key
- **UI:** User Interface

---

## 2. Overall Description

### 2.1 Product Perspective
The Alumni Directory & Networking Management System operates as an autonomous desktop client communicating directly with a local MySQL relational database via JDBC. It requires no web server, application server, or third-party web services, adhering strictly to the approved PIET technology stack.

```
+-------------------------------------------------------------------+
|                        Desktop Client (JVM)                       |
|  +-------------------------------------------------------------+  |
|  |             Java Swing GUI (JFrame, JPanel, JTable)         |  |
|  +------------------------------+------------------------------+  |
|                                 |                                 |
|  +------------------------------v------------------------------+  |
|  |                 Data Access Layer (DAO Classes)             |  |
|  +------------------------------+------------------------------+  |
|                                 |                                 |
|  +------------------------------v------------------------------+  |
|  |              JDBC Driver (MySQL Connector/J 8.4.0)          |  |
|  +------------------------------+------------------------------+  |
+---------------------------------|---------------------------------+
                                  | TCP/IP (Port 3306)
+---------------------------------v---------------------------------+
|                    MySQL Server 8.4 Database                      |
|                     (Database: alumni_db)                         |
+-------------------------------------------------------------------+
```

### 2.2 User Characteristics
1. **Institutional Administrator:** Departmental coordinator or alumni cell officer responsible for maintaining alumni records, verifying graduation details, updating current employment status, and deleting outdated or duplicate entries.
2. **Student / User:** Enrolled B.Tech student who accesses the directory to identify senior alumni working in target companies (e.g., Google, Amazon, Microsoft, TCS) or target cities (e.g., Bengaluru, Hyderabad, Pune, Jaipur) for professional mentorship and career guidance.

### 2.3 Operating Environment
- **Operating System:** Microsoft Windows 10 / Windows 11 (64-bit)
- **Java Runtime:** Java Development Kit (JDK) 17+ / OpenJDK 26
- **Database Server:** MySQL Server Community Edition 8.0+ / 8.4+
- **Display Resolution:** Minimum 1024x768 pixels (Recommended: 1280x800 or higher)

### 2.4 Design and Implementation Constraints
- Strictly constrained to Java, Java Swing, JDBC, and MySQL.
- No third-party enterprise web frameworks (Spring Boot, Node.js, React).
- No cloud database services or social media APIs (e.g., LinkedIn API, Firebase).
- All button texts, table headers, and form inputs must maintain high visual contrast regardless of OS theme.

---

## 3. System Features & Functional Requirements

### 3.1 Module 1: User Authentication & Role Management
- **FR-1.1:** The system shall present a clean desktop Login window requesting `Username` and `Password`.
- **FR-1.2:** The system shall validate that neither the username nor the password field is empty before submitting queries.
- **FR-1.3:** The system shall authenticate credentials against the `users` table via `UserDAO`.
- **FR-1.4:** Upon successful authentication, the system shall redirect users based on role:
  - Role `Admin` $\rightarrow$ `AdminDashboardFrame`
  - Role `User` $\rightarrow$ `UserDashboardFrame`
- **FR-1.5:** Invalid credentials shall trigger a clear `JOptionPane` error dialog without terminating the application.
- **FR-1.6:** If the MySQL database service is unreachable, the system shall report a clear connectivity alert with diagnostic guidance.

### 3.2 Module 2: Alumni Record Management (Admin CRUD)
- **FR-2.1 (Create):** The Admin shall be able to add new alumni records specifying: `Full Name`, `Graduation Year`, `Department`, `Organization / Company`, `Job Role / Designation`, `City`, `Email`, and `Phone Number`.
- **FR-2.2 (Read):** All alumni records shall be displayed in an interactive `JTable` hosted inside a `JScrollPane` with custom-styled headers.
- **FR-2.3 (Selection Sync):** Clicking any row in the alumni table shall immediately populate all fields into the input form for inspection or editing.
- **FR-2.4 (Update):** The Admin shall be able to modify attributes of an existing alumni record and persist changes to MySQL via `AlumniDAO.updateAlumni()`.
- **FR-2.5 (Delete Safeguard):** The Admin shall be able to delete a selected alumni record, requiring explicit confirmation ("Are you sure you want to delete alumni record: '<Name>' (ID: <ID>)?") before execution.
- **FR-2.6 (Search & Filter):** The Admin shall be able to search records in real time across Name, Organization, or City, and filter by Department.
- **FR-2.7 (Form Reset):** A "Clear Form" button shall reset all text inputs, reset the department dropdown, and clear table selection.

### 3.3 Module 3: Alumni Directory & Multi-Criteria Search (Student Portal)
- **FR-3.1:** Students shall view verified alumni records in an interactive, read-only `JTable`.
- **FR-3.2:** Students shall be able to search and filter records using four criteria simultaneously:
  - By Name (contains search, case-insensitive)
  - By Department (dropdown selection: All, CSE, AI & DS, IT, ECE, EE, ME, Civil)
  - By Organization / Company (contains search, case-insensitive)
  - By City / Location (contains search, case-insensitive)
- **FR-3.3:** Clicking "Search" shall execute the filtered query against MySQL; clicking "Reset" shall restore the complete catalog.
- **FR-3.4:** The directory table shall display real-time result counts in the status header.

### 3.4 Module 4: Detailed Alumni Networking Profile View
- **FR-4.1:** Selecting any row in the directory table shall render a comprehensive "Alumni Networking Profile" card on the right-hand panel.
- **FR-4.2:** The profile card shall display:
  - Full Name with bold header styling
  - Alumni ID and Graduation Batch Year
  - Academic Department
  - Current Organization / Employer
  - Professional Designation / Job Role
  - Work Location / City
  - Official Email Address (highlighted in blue)
  - Contact Phone Number
- **FR-4.3:** If no record is selected, the panel shall display an instructional placeholder message ("Select an alumni from the directory table to view full networking details.").

### 3.5 Module 5: Account Security & Session Handling
- **FR-5.1:** Both Admin and Student users shall be able to change their password via a modal dialog requiring verification of the old password.
- **FR-5.2:** New password must match confirmation and contain at least 4 characters.
- **FR-5.3:** Users shall be able to log out safely via Menu or toolbar, terminating the active session and returning to the Login window.

---

## 4. Non-Functional Requirements

### 4.1 Performance Requirements
- Local database queries (authentication, directory search, record insertion) shall execute in less than 200 ms under typical workstation conditions.
- Memory consumption shall remain below 150 MB JVM heap space.

### 4.2 Security Requirements
- Parameterized SQL statements (`PreparedStatement`) must be utilized across all DAO methods to eliminate SQL injection vulnerabilities.
- Passwords must be handled using `JPasswordField` with character array memory clearing.
- Students must be strictly restricted to read-only browsing without any access to CRUD actions.

### 4.3 Usability & Human Factors
- Button labels must have explicit foreground and background colors ensuring 100% visible text across all operating systems.
- Visual status bars and `JOptionPane` dialogs shall provide clear feedback for all user actions.
- Form inputs must perform strict client-side validation (graduation year range, phone number digits, email format) before dispatching SQL queries.

### 4.4 Reliability & Fault Tolerance
- Database connection failure must not cause unhandled runtime crashes; user-friendly alerts must guide the user to check the MySQL service.
- Database operations must be transactional where applicable and ensure referential integrity.
