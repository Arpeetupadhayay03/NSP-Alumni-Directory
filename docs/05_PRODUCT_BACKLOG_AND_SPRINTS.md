# Product Backlog & Agile Sprint Plan

**Project Title:** Alumni Directory & Networking Management System  
**Framework:** Agile / Scrum (4 Practical Minor Project Sprints)  
**Student Name:** ARPEET UPADHAYAY (PIET25CR007)  
**Session:** 2026–27 | **Section:** 3CSD  
**College:** Poornima Institute of Engineering & Technology (PIET)  

---

## 1. Product Backlog Design

Each backlog item corresponds directly to an implemented module or component in the codebase.

| User Story ID | Module | User Story / Feature Description | Priority | Estimation | Target Sprint | Status |
|---|---|---|---|---|---|---|
| **PB-01** | Architecture | Set up JDK, IntelliJ IDEA project structure, libraries, and runtime environment | High | 3 pts | Sprint 1 | Completed |
| **PB-02** | Security | Design and implement desktop `LoginFrame` with credentials fields and DB health check | High | 5 pts | Sprint 1 | Completed |
| **PB-03** | Presentation | Build unified `UIStyle` theme with high-contrast buttons and custom table header renderer | High | 3 pts | Sprint 1 | Completed |
| **PB-04** | Dashboard | Implement `AdminDashboardFrame` with metrics cards, menu bar, and tabbed navigation | High | 5 pts | Sprint 1 | Completed |
| **PB-05** | Dashboard | Implement `UserDashboardFrame` hosting student directory browsing and networking | High | 5 pts | Sprint 1 | Completed |
| **PB-06** | Database | Design normalized MySQL schema (`alumni_db`) and DDL scripts with seed data | High | 5 pts | Sprint 2 | Completed |
| **PB-07** | Persistence | Implement `DBConnection` with connection probing and password fallback logic | High | 3 pts | Sprint 2 | Completed |
| **PB-08** | Data Access | Implement `UserDAO` for role authentication, password verification, and updates | High | 5 pts | Sprint 2 | Completed |
| **PB-09** | Presentation | Implement `ManageAlumniPanel` with form controls, search bar, and interactive `JTable` | High | 8 pts | Sprint 2 | Completed |
| **PB-10** | Data Access | Implement `AlumniDAO` for full CRUD operations and metric calculations | High | 8 pts | Sprint 2 | Completed |
| **PB-11** | Directory | Implement `AlumniDirectoryPanel` with multi-field search (Name, Dept, Org, City) | High | 8 pts | Sprint 3 | Completed |
| **PB-12** | Networking | Implement detailed Alumni Networking Profile Card with formatted contact info | High | 5 pts | Sprint 3 | Completed |
| **PB-13** | Metrics | Implement live metric counters for Total Alumni, Departments, and Cities in Admin view | Medium | 5 pts | Sprint 3 | Completed |
| **PB-14** | Filtering | Implement synchronized multi-criteria dynamic filtering in `AlumniDAO.searchByCriteria` | High | 5 pts | Sprint 3 | Completed |
| **PB-15** | UX | Implement single-click filter reset restoring complete catalog in directory table | Medium | 3 pts | Sprint 3 | Completed |
| **PB-16** | Validation | Implement form validation for numeric graduation years, valid emails, phone digits | High | 5 pts | Sprint 4 | Completed |
| **PB-17** | Security | Implement `ChangePasswordDialog` modal with old password verification | Medium | 3 pts | Sprint 4 | Completed |
| **PB-18** | Reliability | Implement comprehensive exception handling for SQL and connection disconnects | High | 5 pts | Sprint 4 | Completed |
| **PB-19** | Verification | Build `TestRunner` automating all 22 PIET mandatory test scenarios | High | 5 pts | Sprint 4 | Completed |
| **PB-20** | Delivery | Generate screenshots via `ScreenshotGenerator` and finalize academic documentation | High | 5 pts | Sprint 4 | Completed |

---

## 2. Sprint Breakdown & Execution

### Sprint 1: Project Planning, Authentication & Core GUI
- **Sprint Goal:** Establish development environment, define project boundaries, implement UI styling standards, and construct authentication screens.
- **Backlog Items Included:** PB-01, PB-02, PB-03, PB-04, PB-05
- **Deliverables:**
  - Standard IntelliJ project layout (`src`, `lib`, `sql`, `bin`).
  - `UIStyle.java` ensuring high-contrast button styling and custom `DefaultTableCellRenderer` for table headers.
  - Functional `LoginFrame.java` with role routing and demo credentials guidance.
  - Skeleton frames for `AdminDashboardFrame.java` and `UserDashboardFrame.java`.
- **Review & Retrospective:** Confirmed that Swing components render legibly across different Windows display settings.

---

### Sprint 2: Relational Database, JDBC & Alumni Record Management
- **Sprint Goal:** Create MySQL relational schema (`alumni_db`), configure JDBC connectivity, and implement full Alumni CRUD for Administrators.
- **Backlog Items Included:** PB-06, PB-07, PB-08, PB-09, PB-10
- **Deliverables:**
  - `sql/schema.sql` defining `users` and `alumni` tables with seed records.
  - `DBConnection.java` with automatic candidate password fallback and health checks.
  - `UserDAO.java` and `AlumniDAO.java` with parameterized queries.
  - `ManageAlumniPanel.java` with form inputs on the left, interactive `JTable` on the right, and selection synchronization.
- **Review & Retrospective:** Confirmed that adding, updating, and deleting alumni records directly reflect in MySQL rows, with delete protected by a confirmation dialog.

---

### Sprint 3: Directory Search, Networking Profiles & Metrics
- **Sprint Goal:** Enable student directory browsing, multi-criteria search (Name, Dept, Org, City), detailed networking profile card, and admin dashboard metrics.
- **Backlog Items Included:** PB-11, PB-12, PB-13, PB-14, PB-15
- **Deliverables:**
  - `AlumniDirectoryPanel.java` featuring a four-field search bar and directory table.
  - Right-hand "Alumni Networking Profile" card with formatted company, role, email, and phone display.
  - Dynamic SQL building in `AlumniDAO.searchByCriteria` supporting partial matches.
  - Summary metric cards on the Admin dashboard for Total Alumni, Departments, and Cities.
- **Review & Retrospective:** Verified search query responsiveness (< 50ms) and confirmed that student portal restricts editing actions.

---

### Sprint 4: Validation, Testing, Live Capture & Project Delivery
- **Sprint Goal:** Enforce client-side input validation, robust exception handling, complete automated test execution, and generate academic reports.
- **Backlog Items Included:** PB-16, PB-17, PB-18, PB-19, PB-20
- **Deliverables:**
  - Strict input validation rejecting empty fields, non-numeric or unrealistic graduation years (1970–2030), invalid emails, and malformed phone numbers.
  - `ChangePasswordDialog.java` and clean `Logout` session termination returning to login.
  - `TestRunner.java` executing and passing all 22 PIET test cases (100% pass rate).
  - `ScreenshotGenerator.java` creating 8 high-resolution UI PNGs.
  - Comprehensive 8-chapter academic documentation in `docs/` and root `README.md`.
- **Review & Retrospective:** Final execution confirmed zero compiler warnings, seamless database integration, and compliance with all PIET guidelines.
