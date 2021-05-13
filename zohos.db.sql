--
-- File generated with SQLiteStudio v3.3.3 on Thu May 13 22:01:49 2021
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: revoke
CREATE TABLE revoke(id integer primary key autoincrement,name text,token text);
INSERT INTO revoke (id, name, token) VALUES (5, 'meenatchi', '1000.664203131cd2bee3ef9b79788fecba02.e8c55031bec9addd5d4fb2a1fd17f196');
INSERT INTO revoke (id, name, token) VALUES (6, 'MEENATCHI SUNDARAM U', '1000.a85ad22aa4cd5e50f8ada7d7bc54fd8e.ca2fe8427a48fa6bd75e86dcaecb9a8d');
INSERT INTO revoke (id, name, token) VALUES (7, 'Sri', '1000.9232c2416704255326eed22798ed1804.45cb1216b7ac43a5ec64e803ef02a6d0');
INSERT INTO revoke (id, name, token) VALUES (8, 'SRI CHARAN.K', '1000.61f0fa2fb9d28f52133c1635643ea4f8.502d51a6d9f341caa900fd2613df44ec');
INSERT INTO revoke (id, name, token) VALUES (9, 'Meenatchi', '1000.c8da5f0142ad8735de6debb27512e1f0.271d060174a4c7392bfa97d5bdb3a105');

-- Table: users
CREATE TABLE users(id integer primary key autoincrement,name text,email text,totalproducts integer);
INSERT INTO users (id, name, email, totalproducts) VALUES (4, 'Reshma', 'reshmarajiu290501@gmail.com', 0);
INSERT INTO users (id, name, email, totalproducts) VALUES (5, 'meenatchi', 'meenatchisundaram63@gmail.com', 0);
INSERT INTO users (id, name, email, totalproducts) VALUES (6, 'MEENATCHI SUNDARAM U', 'meenatchisundaramu.18eee@kongu.edu', 0);
INSERT INTO users (id, name, email, totalproducts) VALUES (7, 'Sri', 'sricharan1207@gmail.com', 0);
INSERT INTO users (id, name, email, totalproducts) VALUES (8, 'SRI CHARAN.K', 'sricharank.18eee@kongu.edu', 0);
INSERT INTO users (id, name, email, totalproducts) VALUES (9, 'Meenatchi', 'mano.us2001@gmail.com', 0);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
