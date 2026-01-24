# Users Aggregator Service

## Prerequisites

- Docker and Docker Compose
- (Optional) Java 17 and Maven 3.6+ for local development

## Quick Start (Docker)

**Використовуйте цей варіант, якщо хочете запустити все в Docker (бази даних + Spring Boot додаток):**

```bash
docker compose up -d --build
```

Application will be available at `http://localhost:8080`

## Local Development

**Використовуйте цей варіант, якщо хочете запустити Spring Boot додаток локально (для дебагу, hot reload тощо), а бази даних - в Docker:**

```bash
# Start only database containers
docker compose up -d postgres mysql

# Wait for databases to be ready
sleep 5

# Build project
mvn clean package -DskipTests

# Run application locally
java -jar target/user-aggregator.jar
```

Application will be available at `http://localhost:8080`
