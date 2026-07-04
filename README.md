# Currency Converter REST API (Agile & DevOps Assignment)

A production-ready, containerized Java Spring Boot REST API for currency conversions, built using **Scrum (Agile) project ceremonies** and a **Continuous Integration & Continuous Delivery (CI/CD)** pipeline.

---

## Live Demo URL
* **API base URL**: `http://63.177.108.9:8080`
* **Health Check**: `http://63.177.108.9:8080/actuator/health` (Returns `{"status": "UP"}`)
* **Supported Currencies**: `http://63.177.108.9:8080/currencies`

---

## Repository Deliverables Mapping
To make it easy for evaluators to review this project, here is how the course requirements map to this repository:
1. **Backlog & Sprint Plans**: Tracked in [SCRUM_DOCUMENT.md](SCRUM_DOCUMENT.md).
2. **Codebase**: Spring Boot REST API code under [src/main/java/com/example/currency_converter](src/main/java/com/example/currency_converter).
3. **CI/CD Evidence**: GitHub Actions workflow file in [.github/workflows/deploy.yaml](.github/workflows/deploy.yaml).
4. **Testing Evidence**: Service and Integration tests under [src/test/java/com/example/currency_converter](src/test/java/com/example/currency_converter).

### Tech Stack
* **Java 21** & **Spring Boot 3.x** (Web, JPA, Actuator, Validation)
* **PostgreSQL** (Production/Cloud database)
* **H2 Database** (Isolated in-memory test database)
* **Docker & Docker Compose** (Containerization platform)
* **GitHub Actions** (CI/CD pipeline orchestrator)
* **AWS EC2 & AWS RDS** (Cloud deployment infrastructure)



---

## Sprint 0: Backlog Planning & Estimates

During the initial planning stage, we defined our backlog, estimated stories using **Story Points, and aligned on the **Definition of Done (DoD)**.

### Product Backlog
Our Product Backlog consists of 7 User Stories (estimated based on complexity and risk):
* **US-01**: Currency Conversion API (2 SP - High)
* **US-02**: Fetch Supported Currencies API (2 SP - High)
* **US-03**: Add Currency & Exchange Rate (3 SP - High)
* **US-04**: Update Exchange Rate (2 SP - Medium)
* **US-05**: Create JUnit Test Suite (5 SP - High)
* **US-06**: Set up CI Pipeline (5 SP - High)
* **US-07**: Basic Health Monitoring & Logging (3 SP - High)

### Definition of Done (DoD)
Before any user story can be considered "Done" and ready for staging/production, it must satisfy:
1. Code compiles with zero errors and warnings
2. All endpoints and functionalities are working and  have Unit tests
3. Invalid inputs are dones using assertions
4. ode is pushed automatically to the repo and the Github actions pipeline is triggered automatically
5. Maven build passes 
6. Health status returned is OK and UP


