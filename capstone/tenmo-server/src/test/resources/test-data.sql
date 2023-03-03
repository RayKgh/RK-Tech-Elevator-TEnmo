BEGIN TRANSACTION;

DROP TABLE IF EXISTS tenmo_user;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE;

CREATE TABLE tenmo_user (
	user_id int NOT NULL DEFAULT nextval('seq_user_id'),
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(20),
	CONSTRAINT PK_tenmo_user PRIMARY KEY (user_id),
	CONSTRAINT UQ_username UNIQUE (username)
);

INSERT INTO tenmo_user (username,password_hash,role) VALUES ('user1','user1','ROLE_USER'); -- 1001
INSERT INTO tenmo_user (username,password_hash,role) VALUES ('user2','user2','ROLE_USER'); -- 1002
INSERT INTO tenmo_user (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

COMMIT TRANSACTION;
