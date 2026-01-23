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
(5, 'pg_user5', 'Edward', 'Evans'),
(6, 'pg_user6', 'Fiona', 'Foster'),
(7, 'pg_user7', 'George', 'Green'),
(8, 'pg_user8', 'Helen', 'Harris'),
(9, 'pg_user9', 'Ian', 'Irwin'),
(10, 'pg_user10', 'Julia', 'Jones'),
(11, 'pg_user11', 'Kevin', 'King'),
(12, 'pg_user12', 'Laura', 'Lee'),
(13, 'pg_user13', 'Michael', 'Moore'),
(14, 'pg_user14', 'Nancy', 'Nelson'),
(15, 'pg_user15', 'Oliver', 'Owens'),
(16, 'pg_user16', 'Patricia', 'Parker'),
(17, 'pg_user17', 'Quinn', 'Quinn'),
(18, 'pg_user18', 'Rachel', 'Roberts'),
(19, 'pg_user19', 'Samuel', 'Smith'),
(20, 'pg_user20', 'Tina', 'Taylor'),
(21, 'pg_user21', 'Victor', 'Vance'),
(22, 'pg_user22', 'Wendy', 'White'),
(23, 'pg_user23', 'Xavier', 'Xu'),
(24, 'pg_user24', 'Yvonne', 'Young'),
(25, 'pg_user25', 'Zachary', 'Zimmerman');