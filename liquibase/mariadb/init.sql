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
(5, 'mdb_user5', 'Ellis', 'Edwards'),
(6, 'mdb_user6', 'Fallon', 'Fisher'),
(7, 'mdb_user7', 'Grayson', 'Garcia'),
(8, 'mdb_user8', 'Hayden', 'Hughes'),
(9, 'mdb_user9', 'Iris', 'Ingram'),
(10, 'mdb_user10', 'Jules', 'Jackson'),
(11, 'mdb_user11', 'Kendall', 'Kelly'),
(12, 'mdb_user12', 'Lane', 'Lewis'),
(13, 'mdb_user13', 'Marlowe', 'Mitchell'),
(14, 'mdb_user14', 'Nico', 'Norton'),
(15, 'mdb_user15', 'Oakley', 'Owen'),
(16, 'mdb_user16', 'Peyton', 'Patterson'),
(17, 'mdb_user17', 'Quincy', 'Quinn'),
(18, 'mdb_user18', 'Reese', 'Reed'),
(19, 'mdb_user19', 'Skylar', 'Stewart'),
(20, 'mdb_user20', 'Tatum', 'Turner'),
(21, 'mdb_user21', 'Urban', 'Underwood'),
(22, 'mdb_user22', 'Vesper', 'Vaughn'),
(23, 'mdb_user23', 'Winter', 'Watson'),
(24, 'mdb_user24', 'Xara', 'Xavier'),
(25, 'mdb_user25', 'Yuki', 'Yates');