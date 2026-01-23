CREATE TABLE IF NOT EXISTS user_tbl (
    user_key INT PRIMARY KEY,
    user_login VARCHAR(100),
    given_name VARCHAR(100),
    family_name VARCHAR(100)
);

INSERT INTO user_tbl (user_key, user_login, given_name, family_name) VALUES
(1, 'mdb_user1', 'Aria', 'Adams'),
(2, 'mdb_user2', 'Brooke', 'Baker'),
(3, 'mdb_user3', 'Cameron', 'Campbell'),
(4, 'mdb_user4', 'Drew', 'Dixon'),
(5, 'mdb_user5', 'Ellis', 'Edwards');
