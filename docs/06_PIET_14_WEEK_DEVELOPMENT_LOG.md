# PIET 14-Week Development Progress Log

**Academic Session:** 2026–27  
**Course:** B.Tech II Year – III Semester  
**Subject:** Minor Project (NSP)  
**Student Name:** ARPEET UPADHAYAY  
**Student ID:** PIET25CR007  
**Section:** 3CSD  
**Department:** Computer Science & Engineering  
**Institution:** Poornima Institute of Engineering & Technology (PIET), Jaipur  

---

## Weekly Progress Record (Semester Timeline)

| Week | Target Topic / Milestone | Key Tasks & Technical Activities Performed | Deliverables & Artifacts Produced | Faculty / Guide Evaluation |
|---|---|---|---|---|
| **Week 1** | Project Orientation & Java Fundamentals | Analyzed PIET minor project syllabus and marking scheme; reviewed core Java concepts (OOPs, inheritance, encapsulation, collections). | Project Title Selection: Alumni Directory & Networking Management System | Satisfactory |
| **Week 2** | Development Environment Setup | Installed OpenJDK (Java 26 / JDK 17), IntelliJ IDEA, MySQL Server 8.4, and MySQL Workbench; verified PATH configurations. | Environment setup verification; directory structure initialization | Verified |
| **Week 3** | Swing Basics & Prototype Login | Studied Java Swing components (`JFrame`, `JPanel`, `JLabel`, `JTextField`, `JPasswordField`, `JButton`); created initial mock login window. | Prototype `LoginFrame` draft | Reviewed |
| **Week 4** | Complete GUI Wireframes | Designed component layouts for Admin and Student views using `BorderLayout` and `GridBagLayout`. | Wireframe layouts for Admin & User Dashboards | Approved |
| **Week 5** | Swing Event Handling & ActionListeners | Implemented `ActionListener`, `WindowAdapter`, and button click event delegation; added clear form and exit handlers. | Dynamic event handling routines in Swing | Completed |
| **Week 6** | Core Alumni UI Panels | Constructed initial layouts for `ManageAlumniPanel` and `AlumniDirectoryPanel`; created `UIStyle.java` for color and button contrast. | Base UI panels with card borders and headers | Verified |
| **Week 7** | GUI Progression (50% Milestone) | Built alumni input forms, combo boxes for departments, scrollable directory view, and profile card mockup. | Comprehensive Swing prototype (50% milestone) | Milestone 1 Approved |
| **Week 8** | Relational Database Design | Designed normalized schema (`alumni_db`) with tables: `users` and `alumni`; authored DDL script with seed records. | `sql/schema.sql` with primary keys and constraints | Approved |
| **Week 9** | JDBC Connectivity & Architecture | Added MySQL Connector/J driver; implemented `DBConnection.java` with candidate password probing and resource safety. | `db/DBConnection.java` and database health check | Verified |
| **Week 10** | CRUD Implementation (JDBC) | Developed Model classes (`User`, `Alumni`) and DAO layer (`UserDAO`, `AlumniDAO`); tested Add/Update/Delete methods. | Functional DAO classes with `PreparedStatement` | Milestone 2 Approved |
| **Week 11** | GUI & Database Integration | Connected Swing forms to DAOs; added input validations (graduation year range, valid email, phone digits) and `JOptionPane` feedback dialogs. | Integrated Alumni CRUD module with live database | Verified |
| **Week 12** | Interactive JTable & Search Filters | Implemented `JTable` selection listeners syncing rows to form fields; built multi-field search (Name, Dept, Org, City) in `AlumniDAO`. | `AlumniDirectoryPanel` with live table selection and search | Verified |
| **Week 13** | Networking Profile & Admin Metrics | Implemented right-side "Alumni Networking Profile" card, summary metrics cards (Total Alumni, Depts, Cities), and `ChangePasswordDialog`. | Networking profile viewer, admin statistics cards | Milestone 3 Approved |
| **Week 14** | Testing, Live Capture & Final Viva Prep | Executed 22 automated test cases via `TestRunner.java` (100% pass rate); generated UI screenshots; compiled final reports. | `TestRunner`, `screenshots/`, `README.md`, final report | Ready for External Viva |
