# Sample Backend - WAR

A small Spring Boot WAR-based backend for practicing Jenkins CI/CD.

## Technology

- Java 21
- Spring Boot 3.5.6
- Maven
- JUnit 5
- WAR packaging

## API endpoints

- GET /api/health
- GET /api/message

## Build locally

Run tests:

```bash
mvn clean test
```

Build the WAR:

```bash
mvn clean package
```

The generated artifact is:

```text
target/sample-backend.war
```

## Run locally

For quick local testing, the Spring Boot Maven plugin can run the application:

```bash
mvn spring-boot:run
```

The application uses port 8081.

Test:

```bash
curl http://localhost:8081/api/health
curl http://localhost:8081/api/message
```

## CI/CD practice

This project is intended for:

GitHub -> Jenkins -> Maven -> Tests -> SonarQube -> Quality Gate -> Trivy -> WAR artifact -> Deployment

The SCM can later be changed from GitHub to Bitbucket without changing the application build structure.
