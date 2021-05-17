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
DROP DATABASE IF EXISTS Clients;
DROP DATABASE IF EXISTS Programme;
DROP DATABASE IF EXISTS Vehicule;
DROP DATABASE IF EXISTS Employe;
DROP DATABASE IF EXISTS Agence;
create table Clients (
  No_Client NUMERIC(10) NOT NULL,
  Nom_Client VARCHAR(25) NOT NULL,
  Prenom_Client VARCHAR(25) NOT NULL,
  Email_Client VARCHAR(65) NOT NULL,
  Rue_Client VARCHAR(85) NOT NULL,
  Ville_Client VARCHAR(45) NOT NULL,
  Code_Postal_Client NUMERIC(5) NOT NULL,
  Numero_Telephone_Client VARCHAR(15) NOT NULL,
  CONSTRAINT PK_Clients PRIMARY KEY (No_Client)
) engine = InnoDB;
create table Programme (
  Dur é e NUMERIC(2) NOT NULL,
  Descr VARCHAR(300),
  Prix NUMERIC(5, 2) NOT NULL,
  Taux_Reduc NUMERIC(3),
  Date_Souscription DATE,
  No_Client NUMERIC(10) NOT NULL,
  CONSTRAINT PK_Programme PRIMARY KEY (No_Client, Date_Souscription),
  CONSTRAINT FK_Programme_Clients FOREIGN KEY (No_Client) REFERENCES Clients(No_Client)
) engine = InnoDB;
create table Vehicule (
  Matricule VARCHAR(9) NOT NULL,
  Marque VARCHAR(25) NOT NULL,
  Modele VARCHAR(25) NOT NULL,
  Kilometrage NUMERIC(7),
  Boite_a_vitesse VARCHAR(11) NOT NULL,
  -- Automatique ou Manuelle
  Climatisation VARCHAR(13) NOT NULL,
  -- Climatisé ou Non climatisé
  Type_Carburant VARCHAR(20) NOT NULL,
  Categorie VARCHAR(10) NOT NULL,
  -- Economique, confort ou Luxe
  No_Client NUMERIC(10) NOT NULL,
  Date_Souscription DATE,
  CONSTRAINT PK_Vehicule PRIMARY KEY (Matricule),
  CONSTRAINT FK_Vehicule_Clients FOREIGN KEY (No_Client) REFERENCES Clients(No_Client)
) engine = InnoDB;
create table Employe (
  Nom_Employe VARCHAR(25) NOT NULL,
  Prenom_Employe VARCHAR(25) NOT NULL,
  Email_Employe VARCHAR(65) NOT NULL,
  Rue_Employe VARCHAR(85) NOT NULL,
  Ville_Employe VARCHAR(45) NOT NULL,
  Code_Postal_Employe NUMERIC(5) NOT NULL,
  Numero_Telephone_Employe VARCHAR(15) NOT NULL,
  Login VARCHAR(15) NOT NULL,
  Mot_de_passe VARCHAR(55) NOT NULL,
  Categorie_Employe VARCHAR(9) NOT NULL,
  -- Agenceur ou Chauffeur
  CONSTRAINT PK_Employe PRIMARY KEY (login)
) engine = InnoDB;
create table Agence (
  Identifiant VARCHAR(35) NOT NULL,
  Nom_Agence VARCHAR(35) NOT NULL,
  Telephone_Agence VARCHAR(15) NOT NULL,
  Rue_Agence VARCHAR(85) NOT NULL,
  Ville_Agence VARCHAR(45) NOT NULL,
  Code_Postal_Agence NUMERIC(5) NOT NULL,
  Latitude DOUBLE(8, 6),
  -- coordonnées GPS
  Longitude DOUBLE(8, 6),
  -- coordonnées GPS
  CONSTRAINT PK_Agence PRIMARY KEY (Identifiant)
) engine = InnoDB;
INSERT INTO
  Clients (
    No_Client,
    Nom_Client,
    Prenom_Client,
    Email_Client,
    Rue_Client,
    Ville_Client,
    Code_Postal_Client,
    Numero_Telephone_Client
  )
VALUES
  (
    5571,
    'PICHON',
    'Gérard',
    'gerard.pichon@yahoo.fr',
    'Avenue Jean jaurès',
    'Paris',
    75004,
    '0145454545'
  );
INSERT INTO
  Clients (
    No_Client,
    Nom_Client,
    Prenom_Client,
    Email_Client,
    Rue_Client,
    Ville_Client,
    Code_Postal_Client,
    Numero_Telephone_Client
  )
VALUES
  (
    420,
    'DOGG',
    'Snoop',
    'snoop.dogg@wanadoo.com',
    'Rue de La Fumée',
    'Condrieu',
    69420,
    '0420420420'
  );
COMMIT;
SELECT
  *
FROM
  Clients;
  