-- =============================================================
--
-- Nom du cas : RENTCAR
-- Nom de SGBD : mySQL
-- Date de création : Mai 2021
-- Auteurs : Merwan BELKHIRI-GUEGUEN, Felix PILLOT, Benjamin ROECKER
--
-- =============================================================
DROP DATABASE IF EXISTS rentcar;
CREATE DATABASE rentcar;
USE rentcar;
DROP DATABASE IF EXISTS agency;
DROP DATABASE IF EXISTS clients;
DROP DATABASE IF EXISTS concern;
DROP DATABASE IF EXISTS contact_information;
DROP DATABASE IF EXISTS drive;
DROP DATABASE IF EXISTS employee;
DROP DATABASE IF EXISTS invoice;
DROP DATABASE IF EXISTS loyalty_program;
DROP DATABASE IF EXISTS manage;
DROP DATABASE IF EXISTS model;
DROP DATABASE IF EXISTS quote;
DROP DATABASE IF EXISTS v é hicule;
CREATE TABLE Agency(
  identifiant CHAR(50),
  nameAgency CHAR(50) NOT NULL,
  capacity INT,
  gps VARCHAR(50),
  PRIMARY KEY(identifiant)
);
CREATE TABLE Model(
  modelName VARCHAR(25),
  price DECIMAL(3, 2),
  category VARCHAR(10) NOT NULL,
  brand VARCHAR(50) NOT NULL,
  PRIMARY KEY(modelName)
);
CREATE TABLE Loyalty_Program(
  programName VARCHAR(50),
  subscriptionDate DATE,
  expirationDate DATE,
  reduction INT,
  price DECIMAL(6, 2),
  PRIMARY KEY(programName)
);
CREATE TABLE Contact_information(
  contact_id INT,
  name VARCHAR(50),
  surname VARCHAR(50),
  mailAddress VARCHAR(50),
  street CHAR(50) NOT NULL,
  city CHAR(50) NOT NULL,
  postalCode VARCHAR(5) NOT NULL,
  phoneNumber VARCHAR(13),
  PRIMARY KEY(contact_id)
);
CREATE TABLE Clients(
  No_Client DECIMAL(10, 0),
  programName VARCHAR(50),
  contact_id INT NOT NULL,
  PRIMARY KEY(No_Client),
  UNIQUE(contact_id),
  FOREIGN KEY(programName) REFERENCES Loyalty_Program(programName),
  FOREIGN KEY(contact_id) REFERENCES Contact_information(contact_id)
);
CREATE TABLE V é hicule(
  registrationNumber VARCHAR(9),
  kilometers INT,
  manual boolean,
  ac boolean,
  fuel CHAR(50),
  inlocaton boolean NOT NULL,
  identifiant CHAR(50) NOT NULL,
  modelName VARCHAR(25) NOT NULL,
  PRIMARY KEY(registrationNumber),
  FOREIGN KEY(identifiant) REFERENCES Agency(identifiant),
  FOREIGN KEY(modelName) REFERENCES Model(modelName)
);
CREATE TABLE Employee(
  Employe_id VARCHAR(50),
  login VARCHAR(15) NOT NULL,
  password VARCHAR(55) NOT NULL,
  category VARCHAR(55) NOT NULL,
  identifiant CHAR(50) NOT NULL,
  contact_id INT NOT NULL,
  PRIMARY KEY(Employe_id),
  UNIQUE(contact_id),
  FOREIGN KEY(identifiant) REFERENCES Agency(identifiant),
  FOREIGN KEY(contact_id) REFERENCES Contact_information(contact_id)
);
CREATE TABLE quote(
  quote_id INT,
  insurance boolean,
  takingDate DATE,
  returnDate DATE,
  subscribingDate DATE,
  No_Client DECIMAL(10, 0) NOT NULL,
  PRIMARY KEY(quote_id),
  FOREIGN KEY(No_Client) REFERENCES Clients(No_Client)
);
CREATE TABLE invoice(
  idInvoice INT,
  fuelConsommation CHAR(50) NOT NULL,
  vehiculeState VARCHAR(50),
  returnDate DATE,
  quote_id INT NOT NULL,
  identifiant CHAR(50) NOT NULL,
  No_Client DECIMAL(10, 0) NOT NULL,
  PRIMARY KEY(idInvoice),
  UNIQUE(quote_id),
  FOREIGN KEY(quote_id) REFERENCES quote(quote_id),
  FOREIGN KEY(identifiant) REFERENCES Agency(identifiant),
  FOREIGN KEY(No_Client) REFERENCES Clients(No_Client)
);
CREATE TABLE Concern(
  registrationNumber VARCHAR(9),
  quote_id INT,
  PRIMARY KEY(registrationNumber, quote_id),
  FOREIGN KEY(registrationNumber) REFERENCES V é hicule(registrationNumber),
  FOREIGN KEY(quote_id) REFERENCES quote(quote_id)
);
CREATE TABLE Drive(
  registrationNumber VARCHAR(9),
  Employe_id VARCHAR(50),
  PRIMARY KEY(registrationNumber, Employe_id),
  FOREIGN KEY(registrationNumber) REFERENCES V é hicule(registrationNumber),
  FOREIGN KEY(Employe_id) REFERENCES Employee(Employe_id)
);
CREATE TABLE Manage(
  Employe_id VARCHAR(50),
  quote_id INT,
  PRIMARY KEY(Employe_id, quote_id),
  FOREIGN KEY(Employe_id) REFERENCES Employee(Employe_id),
  FOREIGN KEY(quote_id) REFERENCES quote(quote_id)
);
drop user if exists 'Administrateur' @'localhost';
CREATE USER 'Administrateur' @'localhost' IDENTIFIED BY 'Administrateur';
GRANT ALL PRIVILEGES ON * TO 'Administrateur' @'localhost';
COMMIT;