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
(5, 'mysql_user5', 'Emily', 'Edwards'),
(6, 'mysql_user6', 'Frank', 'Fisher'),
(7, 'mysql_user7', 'Grace', 'Garcia'),
(8, 'mysql_user8', 'Henry', 'Hughes'),
(9, 'mysql_user9', 'Isabella', 'Ingram'),
(10, 'mysql_user10', 'Jack', 'Jackson'),
(11, 'mysql_user11', 'Katherine', 'Kelly'),
(12, 'mysql_user12', 'Liam', 'Lewis'),
(13, 'mysql_user13', 'Mia', 'Mitchell'),
(14, 'mysql_user14', 'Noah', 'Norton'),
(15, 'mysql_user15', 'Olivia', 'Owen'),
(16, 'mysql_user16', 'Paul', 'Patterson'),
(17, 'mysql_user17', 'Quinn', 'Quinn'),
(18, 'mysql_user18', 'Ruby', 'Reed'),
(19, 'mysql_user19', 'Sophia', 'Stewart'),
(20, 'mysql_user20', 'Thomas', 'Turner'),
(21, 'mysql_user21', 'Uma', 'Underwood'),
(22, 'mysql_user22', 'Victoria', 'Vaughn'),
(23, 'mysql_user23', 'William', 'Watson'),
(24, 'mysql_user24', 'Xara', 'Xavier'),
(25, 'mysql_user25', 'Yara', 'Yates');