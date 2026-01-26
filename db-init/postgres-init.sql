CREATE TABLE IF NOT EXISTS users_pg (
    user_id INTEGER PRIMARY KEY,
    login VARCHAR(100),
    first_name VARCHAR(100),
    last_name VARCHAR(100)
);

INSERT INTO users_pg (user_id, login, first_name, last_name) VALUES
(1, 'pg_user1', 'Alice', 'Anderson'),
(2, 'pg_user2', 'Bob', 'Brown'),
(3, 'pg_user3', 'Charlie', 'Clark'),
(4, 'pg_user4', 'Diana', 'Davis'),
(5, 'pg_user5', 'Edward', 'Evans');
