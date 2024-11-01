CREATE DATABASE bdApiClientes;

USE bdApiClientes;

CREATE TABLE cliente(
	id                  CHAR(36)            PRIMARY KEY,
	nome                VARCHAR(150)        NOT NULL,
	email               VARCHAR(100)        NOT NULL,
	telefone            VARCHAR(15)         NOT NULL,
	datacadastro        TIMESTAMP           DEFAULT CURRENT_TIMESTAMP
);

DESC cliente;