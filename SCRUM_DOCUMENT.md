# SCRUM & AGILE DOCUMENTATION
## Project Title: Simple Currency Converter

---

## 1. Product Vision
* This simple service is aimed at providing backend API’s that will be used by users to convert different currencies with their up to date rates
* This service will be used by the end user whose primary role is to convert between different currencies and the admin who will be responsible to add and modify the currency details


---

## 2. Sprint 0(Planning)

### Product Backlog

---

| ID | User Story | Story Points | Priority | Sprint | Status | Acceptance Criteria |
| :--- | :--- | :---: | :---: | :---: | :---: | :--- |
| **US-01** | As a user, I want to convert an amount from one currency to another, so that I know the target currency. | 2 | High | 1 | Done | 1. The conversion returns a valid converted value according to the daily exchange rate.<br>2. The converted value should return to only 3 decimal places.<br>3. Handle invalid inputs  where a message like “Invalid input” should be displayed to the end user. |
| **US-02** | As a user, I want to fetch all supported currencies, so that I know the currencies available for conversion. | 2 | High | 1 | Done | 1. A get endpoint returns a list of currencies available.<br>2. Should return status 200 OK upon successful fetching.<br>3. Should return Status 500 incase of a failed fetch. |
| **US-03** | As an admin, I want to add a new currency and exchange rate, so that users can convert with it. | 3 | High | 2 | To Do | 1. PA successful POST of a new currency with return status of 201 Created.<br>2. A return status 409 Confilct if currency already exists |
| **US-04** | As an admin, I want to update the currency exchange rate, so that real-time conversions are up to date. | 2 | Medium | 2 | To Do | 1. A successful Put endpoint with status 200 OK  upon successful update<br>2. A status of 400 Bad request incase of a violation in the input validation |
| **US-05** | As a developer, I want to create tests for conversion logic, so that I ensure no wrong conversion calculations. | 5 | High | 1 | To do | 1. Successful JUnit tests check for all endpoints.<br>2. Successful tests checks for invalid inputs. |
| **US-06** | As a developer, I want to set up a CI pipeline, so that tests and builds run automatically. | 5 | High | 1 | To Do | 1. Use github actions.<br>2. The Github actions runs a  maven clean test on every push.<br>3. Pipeline should show a green indicator upon a successful test pass |
| **US-07** | As a developer, I want to implement basic monitoring, so that I check API health status and track issues. | 3 | High | 2 | To Do | 1.The health status endpoint returns a STATUS OK |

---

### Definition of Done
---
* Code compiles with zero errors and warnings
* All endpoints and functionalities are working and  have Unit tests
* Invalid inputs are dones using assertions
* Code is pushed automatically to the repo and the Github actions pipeline is triggered automatically
* Maven build passes 
* Health status returned is OK and UP
* Users can access their corresponding endpoints
* Proper documentation in a README file of all the steps
* All acceptance criteria are met 



### Sprint 1 Planning
* **Goals**:
  1. Set up the whole environment and pipelines
  2. Enable users to convert between different currencies 
  3. Users are able to view available currencies
  4. Ensure proper Unit and integration tests are done

* **Capacity Planning**: 14 Story Points
* **User stories to implement**
  1. US-01
  2. US-02
  3. US-04
  4. US-05


---

## 3. Sprint 1 Execution

### Sprint Date 
---
* 03/07/2026

### Sprint Goals
---

  1. Set up the whole environment and pipelines
  2. Enable users to convert between different currencies 
  3. Users are able to view available currencies
  4. Ensure proper Unit and integration tests are done

### Sprint Backlog
---
| Backlog Item ID | User Story | Story Points | Priority | Sprint | Status | Acceptance Criteria |
| :--- | :--- | :---: | :---: | :---: | :---: | :--- |
| **US-01** | **As a** user, **I want to** convert an amount from one currency to another, **so that** I know the target currency. | 2 | High | 1 | Done | 1. The conversion returns a valid converted value according to the daily exchange rate.<br>2. The converted value should return to only 3 decimal places.<br>3. Handle invalid inputs where a message like "Invalid input" should be displayed to the end user. |
| **US-02** | **As a** user, **I want to** be able to fetch all supported currencies **so that** I know the currencies available for conversion. | 2 | High | 1 | Done | 1. A GET endpoint returns a list of currencies available.<br>2. Should return status 200 OK upon successful fetching.<br>3. Should return Status 500 in case of a failed fetch. |
| **US-05** | **As a** developer, **I want to** create tests for conversion logic, **so that** I ensure no wrong conversion calculations. | 5 | High | 1 | Done | 1. Successful JUnit tests check for all endpoints.<br>2. Successful tests check for invalid inputs. |
| **US-06** | **As a** developer, **I want to** set up a CI pipeline **so that** tests and other operations run automatically. | 5 | High | 1 | Done | 1. Use GitHub Actions.<br>2. The GitHub Actions runs a `mvn clean test` on every push.<br>3. Pipeline should show a green indicator upon a successful test pass. |

---

### Sprint 1 Review 
- **Delivered Work**:
  - Completed **US-01** (Currency Conversion API `GET /convert`).
  - Completed **US-02** (Fetch Currencies API `GET /currencies`).
  - Completed **US-05** (JUnit 5 unit tests for service layer math and MockMvc controller integration tests).
  - Completed **US-06** (GitHub Actions CI pipeline executing Maven test runs on all pushed commits).
- **Demo Notes**:
  - Demonstrated fetching available currencies from PostgreSQL database via `GET /currencies`.
  - Demonstrated successful USD to EUR conversion at 100.0 amount, returning 92.000 (rounded to exactly 3 decimal places).
  - Demonstrated custom error handler returning 400 Bad Request and `"Invalid input"` payload when requesting a negative amount.
  - Verified on GitHub that the CI pipeline successfully compiled the project and ran all tests automatically with a green status.

---

### Sprint 1 Retrospective 
- **What Went Well**:
  - In-memory H2 database worked extremely fast for local tests.
  - Custom Exception Handler is clean and centralized API errors nicely.
- **What Could Be Improved (Hurdles Faced)**:
  - **IDE Autocomplete & Spring Versioning**: Encountered deprecated `@MockBean` errors in Spring Boot 3.4+ which required using `@MockitoBean`.
  - **YAML Schema Errors**: Typos in workflow configuration (`step:` instead of `steps:` and `setup-jave`) caused GitHub Actions to silently ignore the workflow initially.
- **Improvements to apply in Sprint 2 (At least 2)**:
  1. **Strict YAML Validation**: Double-check GitHub Action schemas using a YAML formatter or validator before committing to prevent silent syntax failures.


---
### Sprint 2 Planning 
* **Goals**:
  1. Implement Admin features (US-03: Add Currency, US-04: Update Rates).
  2. Integrate logging and monitoring (US-07).
  3. Apply improvements from Sprint 1 Retrospective.

* **Capacity Planning**: 8 Story Points
* **User stories to implement
  1. US-03
  2. US-04 
  3. US-07

## 5. Sprint 2 Execution & Improvement

### Sprint Date 
---
* 04/07/2026

### Sprint Goals
---

  1. Implement Admin features (US-03: Add Currency, US-04: Update Rates).
  2. Integrate logging and monitoring (US-07).
  3. Apply improvements from Sprint 1 Retrospective.

### Sprint Backlog
---
| Backlog Item ID | User Story | Story Points | Priority | Sprint | Status | Acceptance Criteria |
| :--- | :--- | :---: | :---: | :---: | :---: | :--- |
| **US-03** | **As an admin, I want to be able to add a new currency and exchange rate, so that a user can be able to use it. | 3 | High | 2 | Done | 1. A successful POST of a new currency with return status of 201 Created.<br>2. A return status 409 Conflict if currency already exists. |
| **US-04** | As an admin, I want to be able to update the currency exchange rate, so that realtime conversions are up to date. | 2 | Medium | 2 | Done | 1. A successful PUT endpoint with status 200 OK upon successful update.<br>2. A status of 400 Bad Request in case of a violation in the input validation. |
| **US-07** | As a developer, I want to implement basic monitoring, so that I check the health status of the APIs and also track issues. | 3 | High | 2 | Done | 1. The health status endpoint returns a STATUS OK (status UP). |

---

### Sprint 2 Review
- **Delivered Work**:
  - Completed **US-03** (Admin API `POST /currencies` and `POST /rates` returning `201 Created` on success and `409 Conflict` on duplicates).
  - Completed **US-04** (Admin API `PUT /rates` to update rates, returning `200 OK` on success and `400 Bad Request` on validation failures).
  - Completed **US-07** (Monitoring using `/actuator/health` and logging using SLF4J).
  - Completed **Release & Deploy (CD Setup)**: Containerized application with a multi-stage `Dockerfile`, automated image release to Docker Hub via GitHub Actions, provisioned an AWS RDS PostgreSQL database, and successfully deployed to an AWS EC2 instance.

### Sprint 2 Retrospective (Inspect & Adapt)
- **What Went Well**:
  - Automating the image release process to Docker Hub on successful build was seamless.
  - Multi-stage Docker builds kept our final runtime container size very lightweight.
  - Deploying the app on an EC2 instance and connecting it to a public RDS instance worked smoothly over the internet.
- **What Could Be Improved (Hurdles Faced)**:
  - **Docker WSL2 File System Lock**: Encountered a Windows Docker Desktop filesystem lock (`failed to solve: read-only file system`) which required running `wsl --shutdown` in PowerShell to clear the WSL2 mount.
  - **Lombok/Jackson Serialization mismatch**: Found that the field `private String ID;` in `ExchangeRate.java` was serialized to lowercase `"id"` by Jackson, which initially broke the PUT test assertions (resolved by adjusting the test assertions).
- **Improvements to apply in future releases**:
  1. **Align Casing Conventions**: Stick strictly to lowercase `id` fields in entities to avoid Jackson serialization discrepancies.
  2. **Co-locate Cloud Resources**: Ensure database (RDS) and application servers (EC2) are created in the same AWS region in the future to minimize query latency.



