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
  identifiant VARCHAR(50),
  nameAgency VARCHAR(50) NOT NULL,
  capacity INT,
  gps VARCHAR(50),
  PRIMARY KEY(identifiant)
);
CREATE TABLE Model(
  modelName VARCHAR(25),
  price INT,
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
  No_Client INT,
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
  manual BOOLEAN,
  ac BOOLEAN,
  fuel VARCHAR(50),
  inlocation BOOLEAN NOT NULL,
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
  category BOOLEAN NOT NULL,
  identifiant VARCHAR(50) NOT NULL,
  contact_id INT NOT NULL,
  PRIMARY KEY(Employe_id),
  UNIQUE(contact_id),
  FOREIGN KEY(identifiant) REFERENCES Agency(identifiant),
  FOREIGN KEY(contact_id) REFERENCES Contact_information(contact_id)
);
CREATE TABLE quote(
  quote_id INT,
  insurance BOOLEAN,
  takingDate DATE,
  returnDate DATE,
  subscribingDate DATE,
  announcePrice INT,
  registrationNumber VARCHAR(9) NOT NULL,
  No_Client INT NOT NULL,
  PRIMARY KEY(quote_id),
  FOREIGN KEY(registrationNumber) REFERENCES V é hicule(registrationNumber),
  FOREIGN KEY(No_Client) REFERENCES Clients(No_Client)
);
CREATE TABLE invoice(
  idInvoice INT,
  fuelConsommation Int NOT NULL,
  vehiculeState int,
  returnDate DATE,
  quote_id INT NOT NULL,
  identifiant VARCHAR(50) NOT NULL,
  No_Client INT NOT NULL,
  PRIMARY KEY(idInvoice),
  UNIQUE(quote_id),
  FOREIGN KEY(quote_id) REFERENCES quote(quote_id),
  FOREIGN KEY(identifiant) REFERENCES Agency(identifiant),
  FOREIGN KEY(No_Client) REFERENCES Clients(No_Client)
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
USE `rentcar`;
DROP procedure IF EXISTS `new_client`;
USE `rentcar`;
DROP procedure IF EXISTS `rentcar`.`new_client`;;
DELIMITER $ $ USE `rentcar` $ $ CREATE DEFINER = `root` @`localhost` PROCEDURE `new_client`(
  name Varchar(50),
  surname varchar(50),
  mailAddress varchar(50),
  street char(50),
  city char(50),
  postalCode Varchar(5),
  phoneNumber varchar(13)
) BEGIN DECLARE i,
l INT;
SELECT
  max(contact_id)
from
  rentcar.contact_information into i;
if i is null then
SELECT
  count(contact_id)
from
  rentcar.contact_information into i;
END IF;
insert into
  contact_information (
    contact_id,
    name,
    surname,
    mailAddress,
    street,
    city,
    postalCode,
    phonenumber
  )
Values
  (
    i + 1,
    name,
    surname,
    mailAddress,
    street,
    city,
    postalCode,
    phoneNumber
  );
Select
  max(No_Client)
from
  rentcar.clients into l;
if l is null then
SELECT
  count(No_Client)
from
  rentcar.clients into l;
END IF;
insert into
  clients (contact_id, No_client)
values
  (i + 1, l + 1);
END $ $ DELIMITER;;
USE `rentcar`;
DROP procedure IF EXISTS `new_vehicule`;
USE `rentcar`;
DROP procedure IF EXISTS `rentcar`.`new_vehicule`;;
DELIMITER $ $ USE `rentcar` $ $ CREATE DEFINER = `root` @`localhost` PROCEDURE `new_vehicule`(
  registrationNumber VARCHAR(9),
  kilometers INT,
  manual boolean,
  ac boolean,
  fuel VARCHAR(50),
  inlocation boolean,
  identifiant VARCHAR(50),
  modelName VARCHAR(50),
  price int(3),
  category Varchar(10),
  brand Varchar(50)
) BEGIN DECLARE l,
i INT;
select
  count(modelName)
from
  Model
where
  Model.modelName = modelName into l;
if l = 0 then
insert into
  Model(ModelName, price, category, brand)
values(modelName, price, category, brand);
end if;
insert into
  v é hicule(
    registrationNumber,
    kilometers,
    manual,
    ac,
    fuel,
    inlocation,
    identifiant,
    modelName
  )
values(
    registrationNumber,
    kilometers,
    manual,
    ac,
    fuel,
    inlocation,
    identifiant,
    modelName
  );
END $ $ USE `rentcar`;
DROP procedure IF EXISTS `supp_client`;
USE `rentcar`;
DROP procedure IF EXISTS `rentcar`.`supp_client`;;
DELIMITER $ $ USE `rentcar` $ $ CREATE DEFINER = `root` @`localhost` PROCEDURE `supp_client`(id int) BEGIN DECLARE i int;
select
  contact_id
from
  clients
where
  clients.No_Client = id into i;
DELETE from
  clients
where
  No_client = id;
DELETE from
  contact_information
where
  contact_id = i;
END $ $ DELIMITER;;
USE `rentcar`;
DROP procedure IF EXISTS `supp_vehicule`;
USE `rentcar`;
DROP procedure IF EXISTS `rentcar`.`supp_vehicule`;;
DELIMITER $ $ USE `rentcar` $ $ CREATE DEFINER = `root` @`localhost` PROCEDURE `supp_vehicule`(immat Varchar(9)) BEGIN
DELETE from
  V é hicule
where
  registrationNumber = immat;
END $ $ DELIMITER;;
USE `rentcar`;
DROP procedure IF EXISTS `modif_vehicule`;
USE `rentcar`;
DROP procedure IF EXISTS `rentcar`.`modif_vehicule`;;
DELIMITER $ $ USE `rentcar` $ $ CREATE DEFINER = `root` @`localhost` PROCEDURE `modif_vehicule`(
  immat varchar(50),
  kilometers int,
  inlocation boolean
) BEGIN DECLARE i int;
select
  kilometers
from
  V é hicule
where
  registrationNumber = immat into i;
update
  V é hicule
set
  registrationNumber = immat,
  kilometers = kilometers,
  inlocation = inlocation
where
  registrationNumber = immat;
END $ $ DELIMITER;;
USE `rentcar`;
DROP procedure IF EXISTS `modif_model`;
DELIMITER $ $ USE `rentcar` $ $ CREATE PROCEDURE `modif_model` (modelName varchar(50), price Int) BEGIN
update
  model
set
  price = price
where
  modelName = model;
END $ $ DELIMITER;
USE `rentcar`;
DROP procedure IF EXISTS `modif_client`;
DELIMITER $ $ USE `rentcar` $ $ CREATE PROCEDURE `modif_client` (
  id int,
  name varchar(50),
  surname varchar(50),
  mailAddress varchar(50),
  street varchar(50),
  city varchar(50),
  postalcode varchar(50),
  phoneNumber varchar(13)
) BEGIN declare i int;
select
  contact_id
from
  clients
where
  No_client = id into i;
update
  contact_information
set
  name = name,
  surname = surname,
  mailAddress = mailAddress,
  street = street,
  city = city,
  postalcode = postalcode,
  phoneNumber = phoneNumber
where
  contact_id = i;
END $ $ DELIMITER;
USE `rentcar`;
DROP procedure IF EXISTS `new_quote`;
USE `rentcar`;
DROP procedure IF EXISTS `new_quote`;
USE `rentcar`;
DROP procedure IF EXISTS `rentcar`.`new_quote`;
DELIMITER $ $ USE `rentcar` $ $ CREATE DEFINER = `root` @`localhost` PROCEDURE `new_quote`(
  insurance boolean,
  takingDate Date,
  returnDate Date,
  subscribingDate Date,
  days int,
  nClient int,
  model Varchar(50),
  identification VARCHAR(50)
) BEGIN DECLARE i,
l INT;
DECLARE g VARCHAR(9);
SELECT
  count(registrationNumber)
from
  rentcar.V é hicule
where
  identifiant = identification
  and inlocation is false
  and modelName = model into i;
if i = 0 then
SELECT
  registrationNumber
from
  rentcar.V é hicule
where
  inlocation = false
  and modelName = model
limit
  1 into g;
  ELSE
SELECT
  registrationNumber
from
  rentcar.V é hicule
where
  identifiant = identification
  and inlocation is false
  and modelName = model
limit
  1 into g;
end if;
SELECT
  price
from
  model
where
  modelName = model into l;
insert into
  quote (
    quote_id,
    insurance,
    takingDate,
    returnDate,
    subscribingDate,
    announceprice,
    registrationNumber,
    No_Client
  )
values
  (
    i + 1,
    insurance,
    takingDate,
    returnDate,
    subscribingDate,
    l * days,
    g,
    nClient
  );
update
  V é hicule
set
  inlocation = false
where
  registrationNumber = g;
END $ $ DELIMITER;;
USE `rentcar`;
DROP procedure IF EXISTS `new_facture`;
DELIMITER $ $ USE `rentcar` $ $ CREATE PROCEDURE `new_facture` (
  fuelconso int,
  vehiculestate int,
  returnDate Date,
  identifiant VARCHAR(50),
  nClient INT
) BEGIN DECLARE i,
l INT;
SELECT
  max(idInvoice)
from
  rentcar.invoice into i;
if i is null then
SELECT
  count(idInvoice)
from
  rentcar.invoice into i;
END IF;
SELECT
  quote_id
from
  quote
where
  quote_id not in (
    select
      quote_id
    from
      invoice
    where
      No_Client = nClient
  )
  and No_Client = nClient
limit
  1 into l;
Insert into
  Invoice(
    idInvoice,
    fuelConsommation,
    vehiculeState,
    returnDate,
    quote_id,
    identifiant,
    No_Client
  )
values(
    i,
    fuelconso,
    vehiculestate,
    returnDate,
    l,
    identifiant,
    nClient
  );
END $ $ DELIMITER;
commit;
insert into
  agency(identifiant, nameAgency, capacity, gps)
values("Paris", "Peugeot", 40, "1010");
select
  *
from
  invoice;
select
  *
from
  V é hicule
  inner join Model on Model.modelName = V é hicule.modelname;
select
  *
from
  contact_information natural
  join clients
order by
  contact_information.name;
SELECT
  registrationNumber
from
  rentcar.V é hicule
where
  identifiant = "Paris"
  and inlocation = false
  and modelName = "208"
limit
  1;