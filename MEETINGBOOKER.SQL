DROP DATABASE IF EXISTS MLR MeetingBooker;

CREATE DATABASE IF NOT EXISTS MLR MeetingBooker;
USE MLR MeetingBooker;
# -----------------------------------------------------------------------------
#       TABLE : ADMIN
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ADMIN
 (
   LOGIN CHAR(255) NOT NULL  ,
   PASSWORD VARCHAR(255) NULL  ,
   NOM CHAR(255) NULL  ,
   PRENOM CHAR(32) NULL  ,
   ADRESSE CHAR(32) NULL  ,
   TELEPHONE CHAR(32) NULL  ,
   MAIL CHAR(32) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : UTILISATEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS UTILISATEUR
 (
   LOGIN CHAR(255) NOT NULL  ,
   PASSWORD VARCHAR(255) NULL  ,
   NOM CHAR(255) NULL  ,
   PRENOM CHAR(32) NULL  ,
   ADRESSE CHAR(32) NULL  ,
   TELEPHONE CHAR(32) NULL  ,
   MAIL CHAR(32) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : LIEU
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LIEU
 (
   IDLIEU SMALLINT NOT NULL  ,
   IDVILLE INTEGER NOT NULL  ,
   IDENT BIGINT(9) NOT NULL  ,
   LIBELLELIEU CHAR(255) NULL  ,
   ADRESSELIEU CHAR(255) NULL  ,
   COORDX BIGINT(10) NULL  ,
   COORDY BIGINT(10) NULL  ,
   ANNULATIONGRATUITE CHAR(255) NULL  ,
   NBETOILES INTEGER NULL  ,
   DESCRIPTIF CHAR(255) NULL  
   , PRIMARY KEY (IDLIEU) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LIEU
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LIEU_LOUEUR
     ON LIEU (IDENT ASC);

CREATE  INDEX I_FK_LIEU_VILLE
     ON LIEU (IDVILLE ASC);

# -----------------------------------------------------------------------------
#       TABLE : PAYS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PAYS
 (
   IDPAYS INTEGER NOT NULL  ,
   NOMPAYS CHAR(32) NULL  
   , PRIMARY KEY (IDPAYS) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : RESPONSABLE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS RESPONSABLE
 (
   LOGIN CHAR(255) NOT NULL  ,
   PASSWORD VARCHAR(255) NULL  ,
   NOM CHAR(255) NULL  ,
   PRENOM CHAR(32) NULL  ,
   ADRESSE CHAR(32) NULL  ,
   TELEPHONE CHAR(32) NULL  ,
   MAIL CHAR(32) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : DATE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS DATE
 (
   DATE DATETIME NOT NULL  
   , PRIMARY KEY (DATE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : DURE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS DURE
 (
   CODEDUREE BIGINT(4) NOT NULL  ,
   LIBELLEDUREE CHAR(255) NULL  
   , PRIMARY KEY (CODEDUREE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : VILLE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS VILLE
 (
   IDVILLE INTEGER NOT NULL  ,
   IDPAYS INTEGER NOT NULL  ,
   NOMVILLE CHAR(255) NULL  ,
   CODEPOSTAL INTEGER NULL  
   , PRIMARY KEY (IDVILLE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE VILLE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_VILLE_PAYS
     ON VILLE (IDPAYS ASC);

# -----------------------------------------------------------------------------
#       TABLE : COMMERCIAL
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS COMMERCIAL
 (
   LOGIN CHAR(255) NOT NULL  ,
   POURCENTAGECOMMERCIAL DECIMAL(10,2) NULL  ,
   PASSWORD VARCHAR(255) NULL  ,
   NOM CHAR(255) NULL  ,
   PRENOM CHAR(32) NULL  ,
   ADRESSE CHAR(32) NULL  ,
   TELEPHONE CHAR(32) NULL  ,
   MAIL CHAR(32) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : ENTREPRISE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS ENTREPRISE
 (
   IDENT BIGINT(9) NOT NULL  ,
   IDVILLE INTEGER NOT NULL  ,
   NOMENT CHAR(255) NULL  ,
   ADRESSEENT CHAR(255) NULL  ,
   TELENT CHAR(255) NULL  ,
   EMAIL CHAR(255) NULL  
   , PRIMARY KEY (IDENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE ENTREPRISE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_ENTREPRISE_VILLE
     ON ENTREPRISE (IDVILLE ASC);

# -----------------------------------------------------------------------------
#       TABLE : INSCRIT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS INSCRIT
 (
   LOGIN CHAR(255) NOT NULL  ,
   IDENT BIGINT(9) NOT NULL  ,
   PASSWORD VARCHAR(255) NULL  ,
   NOM CHAR(255) NULL  ,
   PRENOM CHAR(32) NULL  ,
   ADRESSE CHAR(32) NULL  ,
   TELEPHONE CHAR(32) NULL  ,
   MAIL CHAR(32) NULL  
   , PRIMARY KEY (LOGIN) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE INSCRIT
# -----------------------------------------------------------------------------


CREATE UNIQUE INDEX I_FK_INSCRIT_ENTREPRISE
     ON INSCRIT (IDENT ASC);

# -----------------------------------------------------------------------------
#       TABLE : SALLE
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS SALLE
 (
   IDSALLE BIGINT(4) NOT NULL  ,
   IDLIEU SMALLINT NOT NULL  ,
   NOMSALLE CHAR(255) NULL  ,
   LARGEUR DECIMAL(10,2) NULL  ,
   LONGUEUR DECIMAL(10,2) NULL  ,
   SURFACE BIGINT(4) NULL  ,
   HAUTEUR DECIMAL(10,2) NULL  ,
   CAPACITÉ DECIMAL(10,2) NULL  
   , PRIMARY KEY (IDSALLE) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE SALLE
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_SALLE_LIEU
     ON SALLE (IDLIEU ASC);

# -----------------------------------------------------------------------------
#       TABLE : RESERVATION
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS RESERVATION
 (
   NUMRESA BIGINT(4) NOT NULL  ,
   IDSALLE BIGINT(4) NOT NULL  ,
   CODEDUREE BIGINT(4) NOT NULL  ,
   IDENT BIGINT(9) NOT NULL  ,
   NBPERSONNES BIGINT(4) NULL  ,
   DATERESA DATE NULL  ,
   DATEDEBUT DATE NULL  
   , PRIMARY KEY (NUMRESA) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE RESERVATION
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_RESERVATION_SALLE
     ON RESERVATION (IDSALLE ASC);

CREATE  INDEX I_FK_RESERVATION_RESERVANT
     ON RESERVATION (IDENT ASC);

CREATE  INDEX I_FK_RESERVATION_DURE
     ON RESERVATION (CODEDUREE ASC);

# -----------------------------------------------------------------------------
#       TABLE : RESERVANT
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS RESERVANT
 (
   IDENT BIGINT(9) NOT NULL  ,
   NOMENT CHAR(255) NULL  ,
   ADRESSEENT CHAR(255) NULL  ,
   TELENT CHAR(255) NULL  ,
   EMAIL CHAR(255) NULL  
   , PRIMARY KEY (IDENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       TABLE : PHOTOS
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS PHOTOS
 (
   IDPHOTOS BIGINT(4) NOT NULL  ,
   IDSALLE BIGINT(4) NOT NULL  ,
   CHEMINPHOTOS CHAR(255) NULL  ,
   ?PRINCIPAL BOOL NULL  
   , PRIMARY KEY (IDPHOTOS) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE PHOTOS
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_PHOTOS_SALLE
     ON PHOTOS (IDSALLE ASC);

# -----------------------------------------------------------------------------
#       TABLE : LOUEUR
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS LOUEUR
 (
   IDENT BIGINT(9) NOT NULL  ,
   LOGIN CHAR(255) NOT NULL  ,
   NOM CHAR(255) NULL  ,
   PRENOM CHAR(255) NULL  ,
   CONTACTEO_N CHAR(255) NULL  ,
   TYPEINSCRIPTION CHAR(32) NULL  ,
   MAILCONTACT CHAR(32) NULL  ,
   TELCONTACT CHAR(32) NULL  ,
   NOMENT CHAR(255) NULL  ,
   ADRESSEENT CHAR(255) NULL  ,
   TELENT CHAR(255) NULL  ,
   EMAIL CHAR(255) NULL  
   , PRIMARY KEY (IDENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE LOUEUR
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_LOUEUR_COMMERCIAL
     ON LOUEUR (LOGIN ASC);

# -----------------------------------------------------------------------------
#       TABLE : CONTACTER
# -----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS CONTACTER
 (
   LOGIN CHAR(255) NOT NULL  ,
   DATE DATETIME NOT NULL  ,
   IDENT BIGINT(9) NOT NULL  
   , PRIMARY KEY (LOGIN,DATE,IDENT) 
 ) 
 comment = "";

# -----------------------------------------------------------------------------
#       INDEX DE LA TABLE CONTACTER
# -----------------------------------------------------------------------------


CREATE  INDEX I_FK_CONTACTER_COMMERCIAL
     ON CONTACTER (LOGIN ASC);

CREATE  INDEX I_FK_CONTACTER_DATE
     ON CONTACTER (DATE ASC);

CREATE  INDEX I_FK_CONTACTER_LOUEUR
     ON CONTACTER (IDENT ASC);


# -----------------------------------------------------------------------------
#       CREATION DES REFERENCES DE TABLE
# -----------------------------------------------------------------------------


ALTER TABLE ADMIN 
  ADD FOREIGN KEY FK_ADMIN_UTILISATEUR (LOGIN)
      REFERENCES UTILISATEUR (LOGIN) ;


ALTER TABLE LIEU 
  ADD FOREIGN KEY FK_LIEU_LOUEUR (IDENT)
      REFERENCES LOUEUR (IDENT) ;


ALTER TABLE LIEU 
  ADD FOREIGN KEY FK_LIEU_VILLE (IDVILLE)
      REFERENCES VILLE (IDVILLE) ;


ALTER TABLE RESPONSABLE 
  ADD FOREIGN KEY FK_RESPONSABLE_UTILISATEUR (LOGIN)
      REFERENCES UTILISATEUR (LOGIN) ;


ALTER TABLE VILLE 
  ADD FOREIGN KEY FK_VILLE_PAYS (IDPAYS)
      REFERENCES PAYS (IDPAYS) ;


ALTER TABLE COMMERCIAL 
  ADD FOREIGN KEY FK_COMMERCIAL_UTILISATEUR (LOGIN)
      REFERENCES UTILISATEUR (LOGIN) ;


ALTER TABLE ENTREPRISE 
  ADD FOREIGN KEY FK_ENTREPRISE_VILLE (IDVILLE)
      REFERENCES VILLE (IDVILLE) ;


ALTER TABLE INSCRIT 
  ADD FOREIGN KEY FK_INSCRIT_ENTREPRISE (IDENT)
      REFERENCES ENTREPRISE (IDENT) ;


ALTER TABLE INSCRIT 
  ADD FOREIGN KEY FK_INSCRIT_UTILISATEUR (LOGIN)
      REFERENCES UTILISATEUR (LOGIN) ;


ALTER TABLE SALLE 
  ADD FOREIGN KEY FK_SALLE_LIEU (IDLIEU)
      REFERENCES LIEU (IDLIEU) ;


ALTER TABLE RESERVATION 
  ADD FOREIGN KEY FK_RESERVATION_SALLE (IDSALLE)
      REFERENCES SALLE (IDSALLE) ;


ALTER TABLE RESERVATION 
  ADD FOREIGN KEY FK_RESERVATION_RESERVANT (IDENT)
      REFERENCES RESERVANT (IDENT) ;


ALTER TABLE RESERVATION 
  ADD FOREIGN KEY FK_RESERVATION_DURE (CODEDUREE)
      REFERENCES DURE (CODEDUREE) ;


ALTER TABLE RESERVANT 
  ADD FOREIGN KEY FK_RESERVANT_ENTREPRISE (IDENT)
      REFERENCES ENTREPRISE (IDENT) ;


ALTER TABLE PHOTOS 
  ADD FOREIGN KEY FK_PHOTOS_SALLE (IDSALLE)
      REFERENCES SALLE (IDSALLE) ;


ALTER TABLE LOUEUR 
  ADD FOREIGN KEY FK_LOUEUR_COMMERCIAL (LOGIN)
      REFERENCES COMMERCIAL (LOGIN) ;


ALTER TABLE LOUEUR 
  ADD FOREIGN KEY FK_LOUEUR_ENTREPRISE (IDENT)
      REFERENCES ENTREPRISE (IDENT) ;


ALTER TABLE CONTACTER 
  ADD FOREIGN KEY FK_CONTACTER_COMMERCIAL (LOGIN)
      REFERENCES COMMERCIAL (LOGIN) ;


ALTER TABLE CONTACTER 
  ADD FOREIGN KEY FK_CONTACTER_DATE (DATE)
      REFERENCES DATE (DATE) ;


ALTER TABLE CONTACTER 
  ADD FOREIGN KEY FK_CONTACTER_LOUEUR (IDENT)
      REFERENCES LOUEUR (IDENT) ;

