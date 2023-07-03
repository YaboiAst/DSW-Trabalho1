DROP DATABASE if exists Trab1;
CREATE DATABASE Trab1;

USE Trab1;

CREATE TABLE LOGIN(
	id bigint not null auto_increment,
	email varchar(256),
	senha varchar(50),
	papel char(3),
	PRIMARY KEY(id)
);
	
-- Precisa dessa tabela mesmo?  
CREATE TABLE ADMIN(
	admid bigint not null auto_increment,
	userid bigint not null,
	PRIMARY KEY(admid),
	FOREIGN KEY(userid) REFERENCES LOGIN(id)
);

INSERT INTO LOGIN(email, senha, papel) 
VALUES(
	"admin@gmail.com",
	"admin123",
	"ADM"
);

CREATE TABLE HOTEL(
	userid bigint not null, 
	cnpj char(18), 
	nomeHotel varchar(256), 
	cidade varchar(256),
	PRIMARY KEY(cnpj),
	FOREIGN KEY(userid) REFERENCES LOGIN(id)
);

CREATE TABLE SITE(
	userid bigint not null,
	url varchar(256), 
	nomeSite varchar(256), 
	telefone char(10),
	PRIMARY KEY(url),
	FOREIGN KEY(userid) REFERENCES LOGIN(id)
);

CREATE TABLE PROMO(
	preco float not null,
	dataInicio date,
	dataFim date,
	urlPromo varchar(256),
	cnpjPromo char(18),
	FOREIGN KEY(urlPromo) REFERENCES SITE(url),
	FOREIGN KEY(cnpjPromo) REFERENCES HOTEL(cnpj),
	PRIMARY KEY(urlPromo, cnpjPromo)
);
