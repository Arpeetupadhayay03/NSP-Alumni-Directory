# Testing Documentation & Test Cases

**Project Title:** Alumni Directory & Networking Management System  
**Test Suite:** PIET Mandatory 22 Minor Project Verification Test Cases  
**Student Name:** ARPEET UPADHAYAY (PIET25CR007)  
**Session:** 2026–27 | **Section:** 3CSD  
**Institution:** Poornima Institute of Engineering & Technology (PIET), Jaipur  

---

## 1. Testing Strategy & Methodology

The testing phase follows a hybrid approach combining automated unit and integration test execution via `TestRunner.java` and manual functional GUI walkthrough.
- **Unit & Data Integrity Testing:** Validating DAO methods against live MySQL instances to ensure ACID compliance, referential constraints, and record updates.
- **Input & Form Validation Testing:** Verifying rejection of null, empty, non-numeric, or malformed inputs (graduation year, email format, phone numbers) with user-friendly alerts.
- **System Integration Testing:** Ensuring smooth end-to-end workflows: Login $\rightarrow$ Admin Record Maintenance $\rightarrow$ User Directory Browsing $\rightarrow$ Multi-Field Search $\rightarrow$ Networking Profile Inspection $\rightarrow$ Password Change $\rightarrow$ Logout.

---

## 2. Comprehensive Test Case Execution Matrix

All 22 test cases mandated by the PIET Minor Project guidelines have been verified and executed.

| Test ID | Test Scenario / Description | Test Input Data | Expected Output | Actual Output | Status |
|---|---|---|---|---|---|
| **TEST 01** | Admin Login Authentication | `admin` / `admin123` | Authentication succeeds; returns User with role 'Admin' | Admin authenticated successfully: Alumni Coordinator (Admin) | **PASS** |
| **TEST 02** | Student User Login Authentication | `user1` / `user123` | Authentication succeeds; returns User with role 'User' | Student authenticated successfully: Arpeet Upadhayay | **PASS** |
| **TEST 03** | Invalid Login Rejection | `admin` / `wrongpwd` | Authentication fails; returns null | Correctly rejected invalid credentials | **PASS** |
| **TEST 04** | Empty Login Fields Handling | `""` / `""` | Input rejected; warning prompt | Correctly rejected empty username/password | **PASS** |
| **TEST 05** | Add Alumni Record (CRUD - Create) | Rohit Verma, 2021, CSE, Oracle, Bengaluru | Record inserted; generated primary key returned | Added successfully with generated ID: 12 | **PASS** |
| **TEST 06** | View Alumni Records (CRUD - Read) | Query via `getAllAlumni()` | Returns list of all registered alumni ($\ge 10$) | Retrieved 11 alumni records from database | **PASS** |
| **TEST 07** | Search by Alumni Name | Query: 'Rohit' | Returns matching alumni containing search string | Found 1 alumni matching name 'Rohit' | **PASS** |
| **TEST 08** | Search by Department | Department: 'Computer Science' | Returns all alumni registered under Computer Science | Found 4 alumni in 'Computer Science' | **PASS** |
| **TEST 09** | Search by Organization | Organization: 'Google' | Returns alumni employed at Google | Found 1 alumni working at 'Google' | **PASS** |
| **TEST 10** | Search by City | City: 'Bengaluru' | Returns alumni residing/working in Bengaluru | Found 3 alumni located in 'Bengaluru' | **PASS** |
| **TEST 11** | Update Alumni Record (CRUD - Update) | Org: 'Oracle Cloud', City: 'Hyderabad' | Record updated in MySQL; verified by re-fetch | Updated organization and city, verified in MySQL | **PASS** |
| **TEST 12** | Delete Alumni Record (CRUD - Delete) | Alumni ID: 12 | Record removed from `alumni` table | Deleted ID 12 and verified absence in MySQL | **PASS** |
| **TEST 13** | Delete Confirmation Safeguard | User chooses 'Cancel' on confirm dialog | Deletion aborted; record remains untouched in DB | Action safely aborted when confirmation is cancelled | **PASS** |
| **TEST 14** | Invalid Graduation Year Validation | Year: 1985, Year: 2035, Year: 'abcd' | Input rejected; warning prompt shown | Rejected years outside 1990-2026 range | **PASS** |
| **TEST 15** | Invalid Email Validation | Email: 'rohitverma.com', 'user@' | Format rejected; warning dialog displayed | Format rejected for emails lacking '@' or '.' | **PASS** |
| **TEST 16** | Invalid Phone Validation | Phone: '12345', 'abcdefghij' | Input rejected; 10-digit requirement enforced | Non-numeric or short phone numbers correctly rejected | **PASS** |
| **TEST 17** | Empty Required Field Validation | Blank name, blank organization, blank city | Form submission blocked; error dialog shown | Rejection enforced when required fields are blank | **PASS** |
| **TEST 18** | Database Connection Health | `DBConnection.testConnection()` | JDBC connection established to `alumni_db` | Connected to MySQL alumni_db successfully | **PASS** |
| **TEST 19** | Change Password Functionality | Old: 'user123', New: 'newPass2026' | Password updated, verified, and safely restored | Password updated, verified, and restored to demo state | **PASS** |
| **TEST 20** | Logout Session Handling | Active user reference | Current frame disposed; reference cleared; Login shown | Session cleared and memory context de-referenced | **PASS** |
| **TEST 21** | Restart Application & Re-login | Clean JVM startup | Application re-authenticates both Admin & User | Clean connection obtained; both Admin and User authenticated | **PASS** |
| **TEST 22** | Verify Database Persistence | Query live metrics via `AlumniDAO` | Counts match persistent rows across sessions | Verified MySQL persistence: 10 alumni across 6 depts and 7 cities | **PASS** |

---

## 3. Test Runner Console Output Summary

```
================================================================================
     PIET MINOR PROJECT (NSP) - AUTOMATED VERIFICATION TEST RUNNER              
     Project: Alumni Directory & Networking Management System                   
     Student: ARPEET UPADHAYAY (PIET25CR007) | Section: 3CSD                    
================================================================================

[PASS] TEST 01: Admin login                                | Admin authenticated successfully: Alumni Coordinator (Admin)
[PASS] TEST 02: User login                                 | Student authenticated successfully: Arpeet Upadhayay
[PASS] TEST 03: Invalid login rejection                    | Correctly rejected invalid credentials
[PASS] TEST 04: Empty login fields handling                | Correctly rejected empty username/password
[PASS] TEST 05: Add alumni record (CRUD - Create)          | Added successfully with generated ID: 12
[PASS] TEST 06: View alumni records (CRUD - Read)          | Retrieved 11 alumni records from database
[PASS] TEST 07: Search by alumni name                      | Found 1 alumni matching name 'Rohit'
[PASS] TEST 08: Search by department                       | Found 4 alumni in 'Computer Science'
[PASS] TEST 09: Search by organization                     | Found 1 alumni working at 'Google'
[PASS] TEST 10: Search by city                             | Found 3 alumni located in 'Bengaluru'
[PASS] TEST 11: Update alumni record (CRUD - Update)       | Updated organization and city, verified in MySQL
[PASS] TEST 12: Delete alumni record (CRUD - Delete)       | Deleted ID 12 and verified absence in MySQL
[PASS] TEST 13: Delete confirmation safeguard              | Confirmed: Action safely aborted when confirmation is cancelled
[PASS] TEST 14: Invalid graduation year validation         | Validated: Rejected years outside 1990-2026 range
[PASS] TEST 15: Invalid email validation                   | Validated: Format rejected for emails lacking '@' or '.'
[PASS] TEST 16: Invalid phone validation                   | Validated: Non-numeric or short phone numbers correctly rejected
[PASS] TEST 17: Empty required field validation            | Validated: Rejection enforced when required fields are blank
[PASS] TEST 18: Database connection health                 | Connected to MySQL alumni_db successfully
[PASS] TEST 19: Change password functionality              | Password updated, verified, and safely restored to original demo state
[PASS] TEST 20: Logout session handling                    | Session cleared and memory context de-referenced
[PASS] TEST 21: Restart application and login again        | Clean connection obtained; both Admin and User authenticated
[PASS] TEST 22: Verify database persistence                | Verified MySQL persistence: 10 alumni across 6 depts and 7 cities

================================================================================
     TEST EXECUTION SUMMARY: 22 PASSED, 0 FAILED (TOTAL: 22)        
================================================================================
>> ALL 22 PIET MANDATORY TESTS PASSED SUCCESSFULLY! <<
```
