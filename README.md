# User Aggregator

Simple service that collects users from different databases (PostgreSQL and MySQL) and shows them together.

## Run server

```bash
docker compose up -d --build
```

## Stop server

```bash
docker compose down
```

## API

API will be at `http://localhost:8080/users`

- `GET /users` - returns all users from both databases

## Configuration

You can change database settings with environment variables in `docker-compose.yml`.

### Database 1 (PostgreSQL)

| Variable | What it does | Example |
|----------|--------------|---------|
| `DATA_SOURCES_DATABASES_0_DB_NAME` | Database name | `data-base-1` |
| `DATA_SOURCES_DATABASES_0_STRATEGY` | Database type | `postgres` |
| `DATA_SOURCES_DATABASES_0_URL` | Connection url | `jdbc:postgresql://postgres:5432/postgres` |
| `DATA_SOURCES_DATABASES_0_USER` | Username | `user_postgre` |
| `DATA_SOURCES_DATABASES_0_PASSWORD` | Password | `pass` |
| `DATA_SOURCES_DATABASES_0_TABLE` | Table name | `users_pg` |
| `DATA_SOURCES_DATABASES_0_COLUMN_MAPPING_ID` | Which column is user ID | `user_id` |
| `DATA_SOURCES_DATABASES_0_COLUMN_MAPPING_USERNAME` | Which column is username | `login` |
| `DATA_SOURCES_DATABASES_0_COLUMN_MAPPING_NAME` | Which column is first name | `first_name` |
| `DATA_SOURCES_DATABASES_0_COLUMN_MAPPING_SURNAME` | Which column is last name | `last_name` |

### Database 2 (MySQL)

| Variable | What it does | Example |
|----------|--------------|---------|
| `DATA_SOURCES_DATABASES_1_DB_NAME` | Database name | `data-base-2` |
| `DATA_SOURCES_DATABASES_1_STRATEGY` | Database type | `mysql` |
| `DATA_SOURCES_DATABASES_1_URL` | Connection url | `jdbc:mysql://mysql:3306/mysql_db` |
| `DATA_SOURCES_DATABASES_1_USER` | Username | `user_mysql` |
| `DATA_SOURCES_DATABASES_1_PASSWORD` | Password | `pass` |
| `DATA_SOURCES_DATABASES_1_TABLE` | Table name | `users_mysql` |
| `DATA_SOURCES_DATABASES_1_COLUMN_MAPPING_ID` | Which column is user ID | `id` |
| `DATA_SOURCES_DATABASES_1_COLUMN_MAPPING_USERNAME` | Which column is username | `username` |
| `DATA_SOURCES_DATABASES_1_COLUMN_MAPPING_NAME` | Which column is first name | `name` |
| `DATA_SOURCES_DATABASES_1_COLUMN_MAPPING_SURNAME` | Which column is last name | `surname` |

## Tech stack

- Spring Boot 3.1.5
- PostgreSQL 15
- MySQL 8.0
- Docker
