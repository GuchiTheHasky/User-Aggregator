# Users Aggregator Service

A Spring Boot application that aggregates users from 4 different relational databases (PostgreSQL, MySQL, Oracle, MariaDB) and exposes them via a single REST endpoint.

## Features

- Aggregates users from 4 different relational databases sequentially
- Dynamic data source configuration via YAML
- Column mapping support for different database schemas
- OpenAPI/Swagger UI documentation
- Docker Compose setup with automatic database initialization via Liquibase
- Production-ready architecture with connection pooling (HikariCP)

## Prerequisites

- **Java 17** (JDK 17)
- **Maven 3.6+**
- **Docker** and **Docker Compose**

## Quick Start

### Step 1: Start Databases

Start all 4 databases and initialize them with Liquibase:

```bash
docker compose up -d
```

This command will:

- Start PostgreSQL on port 5432
- Start MySQL on port 3306
- Start Oracle on port 1521
- Start MariaDB on port 3307
- Automatically initialize all databases with tables and seed data (25 users each)

**Note:** Oracle may take 1-2 minutes to fully start. The Liquibase init container will wait for all databases to be healthy before running migrations.

### Step 2: Build the Application

```bash
mvn clean package
```

### Step 3: Run the Application

```bash
java -jar target/user-aggregator-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

### Step 4: Test the Endpoint

```bash
curl http://localhost:8080/users
```

You should receive a JSON array with approximately 100 users (25 from each database).

## API Documentation

Once the application is running, access the Swagger UI at:

**http://localhost:8080/swagger-ui.html**

The OpenAPI specification is available at:

**http://localhost:8080/v3/api-docs**

## API Endpoint

### GET /users

Returns a JSON array of user objects aggregated from all configured databases.

**Response Format:**

```json
[
  {
    "id": "1",
    "username": "pg_user1",
    "name": "Alice",
    "surname": "Anderson"
  },
  ...
]
```

**Fields:**

- `id`: User ID (String)
- `username`: Username (String)
- `name`: First name (String)
- `surname`: Last name (String)

## Configuration

The application uses `application.yml` to configure data sources. Each data source includes:

- `name`: Unique identifier for the data source
- `strategy`: Database type (postgres, mysql, oracle, mariadb)
- `url`: JDBC connection URL
- `user`: Database username
- `password`: Database password
- `table`: Table name in the database
- `mapping`: Column mapping from database columns to canonical fields

### Example Configuration

```yaml
data-sources:
  sources:
    - name: data-base-1
      strategy: postgres
      url: jdbc:postgresql://localhost:5432/postgres
      user: postgres
      password: postgres
      table: users_pg
      mapping:
        id: user_id
        username: login
        name: first_name
        surname: last_name
```

## Database Schemas

Each database uses different table and column names to demonstrate the mapping functionality:

| Database   | Table Name    | ID Column  | Username Column | Name Column  | Surname Column |
| ---------- | ------------- | ---------- | --------------- | ------------ | -------------- |
| PostgreSQL | `users_pg`    | `user_id`  | `login`         | `first_name` | `last_name`    |
| MySQL      | `users_mysql` | `id`       | `username`      | `name`       | `surname`      |
| Oracle     | `USERS_ORA`   | `UID`      | `UNAME`         | `FNAME`      | `LNAME`        |
| MariaDB    | `user_tbl`    | `user_key` | `user_login`    | `given_name` | `family_name`  |

## Adding a 5th Data Source

To add a new data source:

1. Add the database service to `docker-compose.yml`
2. Create Liquibase changelogs in `liquibase/<db-name>/`
3. Add the data source configuration to `application.yml`:

```yaml
data-sources:
  sources:
    # ... existing sources ...
    - name: data-base-5
      strategy: <database-type>
      url: jdbc:<driver>://localhost:<port>/<database>
      user: <username>
      password: <password>
      table: <table-name>
      mapping:
        id: <id-column>
        username: <username-column>
        name: <name-column>
        surname: <surname-column>
```

The application will automatically:

- Create a DataSource and connection pool at startup
- Include the new source in sequential aggregation
- Use the column mapping to generate the correct SQL query

## Architecture

### Key Components

- **UserDto**: Data transfer object with canonical fields (id, username, name, surname)
- **DataSourceProperties**: Configuration model for a single data source
- **DataSourceConfigProperties**: Root configuration binding from YAML
- **DataSourceRegistry**: Manages DataSource and JdbcTemplate instances
- **DataSourceConfiguration**: Initializes all data sources at startup
- **UsersAggregationService**: Aggregates users sequentially from all sources
- **UsersController**: REST endpoint with OpenAPI documentation

### Design Decisions

- **Sequential Aggregation**: Users are read from databases in configured order (DB1 → DB2 → DB3 → DB4) to avoid collisions and maintain deterministic ordering
- **Dynamic SQL Generation**: SQL queries are built dynamically based on column mappings, supporting different database schemas
- **Connection Pooling**: Each data source uses HikariCP for efficient connection management
- **No JPA**: Uses JdbcTemplate for direct SQL control and flexibility
- **String IDs**: All IDs are converted to String in the DTO to handle different numeric types uniformly

## Troubleshooting

### Databases not starting

- Ensure ports 5432, 3306, 1521, and 3307 are not in use
- Check Docker logs: `docker compose logs`

### Liquibase initialization fails

- Wait for all databases to be healthy (especially Oracle, which takes longer)
- Check Liquibase logs: `docker compose logs liquibase-init`
- Re-run initialization: `docker compose restart liquibase-init`

### Application cannot connect to databases

- Verify databases are running: `docker compose ps`
- Check connection URLs in `application.yml` match Docker service names
- For local runs, use `localhost`; for Docker runs, use service names

### Oracle connection issues

- Oracle Express Edition may take 1-2 minutes to fully initialize
- Ensure the Oracle container is healthy before starting the application
- Check Oracle logs: `docker compose logs oracle`

## Stopping the Application

```bash
# Stop the application (Ctrl+C or kill process)

# Stop and remove all containers
docker compose down

# Stop and remove containers with volumes (removes all data)
docker compose down -v
```

## Project Structure

```
user-aggregator/
├── src/
│   └── main/
│       ├── java/
│       │   └── the/husky/user_aggregator/
│       │       ├── config/          # Data source configuration
│       │       ├── controller/      # REST controllers
│       │       ├── dto/             # Data transfer objects
│       │       └── service/         # Business logic
│       └── resources/
│           └── application.yml      # Application configuration
├── liquibase/                       # Database migration scripts
│   ├── postgres/
│   ├── mysql/
│   ├── oracle/
│   └── mariadb/
├── docker-compose.yml               # Docker services definition
├── pom.xml                         # Maven dependencies
└── README.md                       # This file
```

## License

This is a demo project for educational purposes.
