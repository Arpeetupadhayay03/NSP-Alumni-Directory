# B.Tech Minor Project Proposal (NSP)

**Project Title:** Alumni Directory & Networking Management System  
**Academic Session:** 2026–27  
**Department:** Computer Science & Engineering (Section: 3CSD)  
**Institution:** Poornima Institute of Engineering & Technology (PIET), Jaipur  

---

## Candidate Information
- **Student Name:** ARPEET UPADHAYAY  
- **Student ID:** PIET25CR007  
- **Course:** B.Tech II Year – III Semester  
- **Subject / Scheme:** Minor Project (NSP)  

---

## 1. Project Title
**ALUMNI DIRECTORY & NETWORKING MANAGEMENT SYSTEM**

---

## 2. Introduction & Background
In higher educational institutions, the alumni network represents one of the most vital strategic assets for student mentorship, career guidance, placement opportunities, industrial guest lectures, and institutional prestige. However, in many engineering colleges, alumni records are either maintained in fragmented Excel spreadsheets, static yearbooks, physical registers, or obsolete contact logs. Consequently, current students face enormous difficulty discovering alumni working in target companies or domains, while college administrators struggle to maintain up-to-date career trajectories and contact information.

The **Alumni Directory & Networking Management System** is engineered as a robust, desktop-based management application utilizing standard academic core technologies: Java Swing, JDBC, and MySQL Server 8.4. It introduces an administrative console enabling institutional administrators to maintain comprehensive alumni records, perform full CRUD operations (Create, Read, Update, Delete, Search), and monitor departmental distributions. Simultaneously, it provides undergraduate students with a dedicated networking portal to browse alumni records, execute multi-criteria searches (by Name, Department, Organization, and City), and view complete professional networking profiles to facilitate mentorship outreach.

---

## 3. Problem Statement
Traditional alumni management and networking in educational institutions suffer from several critical shortcomings:
1. **Decentralized & Outdated Contact Information:** Alumni data is scattered across paper registers and departmental files, making record updates difficult and error-prone.
2. **Lack of Student Accessibility:** Current engineering students have no accessible interface to look up senior graduates working in specific organizations (e.g., Google, Microsoft, Infosys) or industries for career guidance.
3. **Absence of Multi-Criteria Search:** Finding alumni based on department, graduation year, current employer, or geographical location is tedious and practically impossible using manual records.
4. **Data Integrity & Security Issues:** Unsecured spreadsheets allow accidental overwrites, lacks role-based access control, and fails to separate administrative maintenance from student read-only directory browsing.

---

## 4. Aim and Objectives
### Aim
To design, develop, and evaluate a desktop-based Alumni Directory & Networking Management System for Poornima Institute of Engineering & Technology using Java Swing, JDBC, and MySQL.

### Objectives
1. **Role-Based Authentication:** Implement secure authentication for two distinct user roles: **Admin** (record management, full CRUD operations) and **User / Student** (search, directory browsing, profile viewing).
2. **Alumni Record CRUD Management:** Provide administrative functionality to Add, View, Update, Delete, and Search alumni records with input validation and confirmation dialogs.
3. **Multi-Criteria Directory Search:** Allow users to filter alumni records dynamically by Name, Department, Organization, and City with single-click reset.
4. **Alumni Networking Profile Card:** Render an itemized professional profile view displaying graduation batch, department, current job role, employer organization, city, official email, and contact phone number.
5. **Relational Database Design:** Engineer a normalized MySQL database (`alumni_db`) comprising `users` and `alumni` tables enforcing primary keys, unique constraints, and data integrity.
6. **Robust JDBC DAO Layer:** Implement `UserDAO` and `AlumniDAO` utilizing parameterized `PreparedStatement` to prevent SQL injection vulnerabilities and cleanly separate GUI events from persistence logic.
7. **Input Validation & Exception Handling:** Prevent invalid inputs (e.g., non-numeric graduation years, malformed emails, invalid phone numbers, empty mandatory fields) and handle database disconnection gracefully without application crashes.
8. **Academic Swing UI Standards:** Provide a professional academic UI theme (Deep Navy / Slate / Cool White), high-contrast visible buttons, custom styled table headers, and responsive split-pane layouts.

---

## 5. Technology Stack & Tools
- **Programming Language:** Java (JDK 17 LTS / OpenJDK 26)
- **GUI Toolkit:** Java Swing (`JFrame`, `JPanel`, `JTable`, `JScrollPane`, `JTabbedPane`, `JButton`, `JOptionPane`, `GridBagLayout`, `BorderLayout`)
- **Database Engine:** MySQL Server 8.0+ / 8.4+ (InnoDB Engine)
- **Database Driver:** MySQL Connector/J 8.4.0 (JDBC)
- **Integrated Development Environment (IDE):** IntelliJ IDEA (Community or Ultimate Edition)
- **Operating System:** Windows 10 / 11

---

## 6. Scope of the System
### In Scope
- Admin command center with quick metrics cards (Total Alumni, Total Departments, Total Cities).
- Full CRUD operations for alumni records (Add, View, Update, Delete with confirmation, Search).
- Multi-field search filters (Name, Department, Organization, City) for both Admin and Student portals.
- Read-only networking profile inspector for students seeking mentorship or placement contacts.
- Password change dialog with old password verification and validation.
- Safe logout mechanism returning to login screen without application restart.

### Out of Scope (PIET Strict Compliance)
In strict accordance with the PIET Minor Project guidelines, no unnecessary enterprise or web technologies have been included:
- No web frameworks (Spring Boot, Node.js, React, Angular).
- No cloud databases or Firebase.
- No social media bloatware (instant messaging servers, social feeds, friend requests, video calls).
- No web scraping or external LinkedIn API integrations.

---

## 7. Expected Outcomes
1. A fully compiled, error-free Java Swing desktop application runnable from IntelliJ IDEA or command-line scripts (`compile_and_run.bat` / `compile_and_run.ps1`).
2. A clean, normalized MySQL database script (`sql/schema.sql`) verifiable in MySQL Workbench or CLI.
3. Complete evidence for all PIET Minor Project (NSP) evaluation criteria across Mid Term I, Mid Term II, Mid Term III, and External Examination.
