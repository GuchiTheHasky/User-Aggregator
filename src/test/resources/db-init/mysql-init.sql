CREATE TABLE IF NOT EXISTS users_mysql (
    id INT PRIMARY KEY,
    username VARCHAR(100),
    name VARCHAR(100),
    surname VARCHAR(100)
);

INSERT INTO users_mysql (id, username, name, surname) VALUES
(1, 'mysql_user1', 'Anna', 'Adams'),
(2, 'mysql_user2', 'Benjamin', 'Baker'),
(3, 'mysql_user3', 'Catherine', 'Campbell'),
(4, 'mysql_user4', 'Daniel', 'Dixon'),
(5, 'mysql_user5', 'Emily', 'Edwards');
