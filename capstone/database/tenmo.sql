BEGIN TRANSACTION;

DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS account;
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

CREATE TABLE account ( 
	account_id serial PRIMARY KEY,
	user_id int UNIQUE NOT NULL,
	balance decimal (11,2) DEFAULT 1000.00 ,
	CONSTRAINT FK_account_user FOREIGN KEY(user_id) REFERENCES tenmo_user(user_id)
);

CREATE TABLE transaction (
	transaction_id serial PRIMARY KEY,
	sender_id int NOT NULL,
	recipient_id int NOT NULL,
	transfer_amt decimal NOT NULL,
	timestamp timestamp NOT NULL,
	is_requested boolean NOT NULL,
	status int NOT NULL,
	CONSTRAINT FK_account_sender FOREIGN KEY(sender_id) REFERENCES account(user_id),
	CONSTRAINT FK_account_recipient FOREIGN KEY(recipient_id) REFERENCES account(user_id)
);



COMMIT;
