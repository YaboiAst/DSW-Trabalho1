DROP DATABASE if exists Trab1;
CREATE DATABASE Trab1;

USE Trab1;

CREATE TABLE LOGIN(
	email varchar(256) not null,
	senha varchar(50),
	nome varchar(256),
	papel char(3), -- ADM, HOT ou SIT
	PRIMARY KEY(email)
);

INSERT INTO LOGIN(email, senha, papel) 
VALUES(
	"admin@gmail.com",
	"admin123",
	"ADM"
);

CREATE TABLE HOTEL(
	userid varchar(256) not null, 
	cnpj char(18) not null, 
	cidade varchar(256),
	PRIMARY KEY(cnpj),
	FOREIGN KEY(userid) REFERENCES LOGIN(email) ON DELETE CASCADE
);

CREATE TABLE SITE(
	userid varchar(256) not null,
	url varchar(256) not null, 
	telefone char(10),
	PRIMARY KEY(url),
	FOREIGN KEY(userid) REFERENCES LOGIN(email) ON DELETE CASCADE
);

CREATE TABLE PROMO(
	preco float not null,
	dataInicio date,
	dataFim date,
	urlPromo varchar(256) not null,
	cnpjPromo char(18) not null,
	FOREIGN KEY(urlPromo) REFERENCES SITE(url),
	FOREIGN KEY(cnpjPromo) REFERENCES HOTEL(cnpj),
	PRIMARY KEY(urlPromo, cnpjPromo)
);
