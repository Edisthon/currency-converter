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