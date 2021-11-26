-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 19 nov. 2021 à 13:39
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `meetingbooking`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `LOGIN` char(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NOM` char(255) DEFAULT NULL,
  `PRENOM` char(32) DEFAULT NULL,
  `ADRESSE` char(32) DEFAULT NULL,
  `TELEPHONE` char(32) DEFAULT NULL,
  `MAIL` char(32) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commercial`
--

DROP TABLE IF EXISTS `commercial`;
CREATE TABLE IF NOT EXISTS `commercial` (
  `LOGIN` char(255) NOT NULL,
  `POURCENTAGECOMMERCIAL` decimal(10,2) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NOM` char(255) DEFAULT NULL,
  `PRENOM` char(32) DEFAULT NULL,
  `ADRESSE` char(32) DEFAULT NULL,
  `TELEPHONE` char(32) DEFAULT NULL,
  `MAIL` char(32) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `contacter`
--

DROP TABLE IF EXISTS `contacter`;
CREATE TABLE IF NOT EXISTS `contacter` (
  `LOGIN` char(255) NOT NULL,
  `DATE` datetime NOT NULL,
  `IDENT` bigint(9) NOT NULL,
  PRIMARY KEY (`LOGIN`,`DATE`,`IDENT`),
  KEY `I_FK_CONTACTER_COMMERCIAL` (`LOGIN`),
  KEY `I_FK_CONTACTER_DATE` (`DATE`),
  KEY `I_FK_CONTACTER_LOUEUR` (`IDENT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `date`
--

DROP TABLE IF EXISTS `date`;
CREATE TABLE IF NOT EXISTS `date` (
  `DATE` datetime NOT NULL,
  PRIMARY KEY (`DATE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `dure`
--

DROP TABLE IF EXISTS `dure`;
CREATE TABLE IF NOT EXISTS `dure` (
  `CODEDUREE` bigint(4) NOT NULL,
  `LIBELLEDUREE` char(255) DEFAULT NULL,
  PRIMARY KEY (`CODEDUREE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
CREATE TABLE IF NOT EXISTS `entreprise` (
  `IDENT` bigint(9) NOT NULL,
  `IDVILLE` int(11) NOT NULL,
  `NOMENT` char(255) DEFAULT NULL,
  `ADRESSEENT` char(255) DEFAULT NULL,
  `TELENT` char(255) DEFAULT NULL,
  `EMAIL` char(255) DEFAULT NULL,
  PRIMARY KEY (`IDENT`),
  KEY `I_FK_ENTREPRISE_VILLE` (`IDVILLE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `inscrit`
--

DROP TABLE IF EXISTS `inscrit`;
CREATE TABLE IF NOT EXISTS `inscrit` (
  `LOGIN` char(255) NOT NULL,
  `IDENT` bigint(9) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NOM` char(255) DEFAULT NULL,
  `PRENOM` char(32) DEFAULT NULL,
  `ADRESSE` char(32) DEFAULT NULL,
  `TELEPHONE` char(32) DEFAULT NULL,
  `MAIL` char(32) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`),
  UNIQUE KEY `I_FK_INSCRIT_ENTREPRISE` (`IDENT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `IDLIEU` smallint(6) NOT NULL,
  `IDVILLE` int(11) NOT NULL,
  `IDENT` bigint(9) NOT NULL,
  `LIBELLELIEU` char(255) DEFAULT NULL,
  `ADRESSELIEU` char(255) DEFAULT NULL,
  `COORDX` bigint(10) DEFAULT NULL,
  `COORDY` bigint(10) DEFAULT NULL,
  `ANNULATIONGRATUITE` char(255) DEFAULT NULL,
  `NBETOILES` int(11) DEFAULT NULL,
  `DESCRIPTIF` char(255) DEFAULT NULL,
  PRIMARY KEY (`IDLIEU`),
  KEY `I_FK_LIEU_LOUEUR` (`IDENT`),
  KEY `I_FK_LIEU_VILLE` (`IDVILLE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `loueur`
--

DROP TABLE IF EXISTS `loueur`;
CREATE TABLE IF NOT EXISTS `loueur` (
  `IDENT` bigint(9) NOT NULL,
  `LOGIN` char(255) NOT NULL,
  `NOM` char(255) DEFAULT NULL,
  `PRENOM` char(255) DEFAULT NULL,
  `CONTACTEO_N` char(255) DEFAULT NULL,
  `TYPEINSCRIPTION` char(32) DEFAULT NULL,
  `MAILCONTACT` char(32) DEFAULT NULL,
  `TELCONTACT` char(32) DEFAULT NULL,
  `NOMENT` char(255) DEFAULT NULL,
  `ADRESSEENT` char(255) DEFAULT NULL,
  `TELENT` char(255) DEFAULT NULL,
  `EMAIL` char(255) DEFAULT NULL,
  PRIMARY KEY (`IDENT`),
  KEY `I_FK_LOUEUR_COMMERCIAL` (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

DROP TABLE IF EXISTS `pays`;
CREATE TABLE IF NOT EXISTS `pays` (
  `IDPAYS` int(11) NOT NULL,
  `NOMPAYS` char(32) DEFAULT NULL,
  PRIMARY KEY (`IDPAYS`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reservant`
--

DROP TABLE IF EXISTS `reservant`;
CREATE TABLE IF NOT EXISTS `reservant` (
  `IDENT` bigint(9) NOT NULL,
  `NOMENT` char(255) DEFAULT NULL,
  `ADRESSEENT` char(255) DEFAULT NULL,
  `TELENT` char(255) DEFAULT NULL,
  `EMAIL` char(255) DEFAULT NULL,
  PRIMARY KEY (`IDENT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `NUMRESA` bigint(4) NOT NULL,
  `IDSALLE` bigint(4) NOT NULL,
  `CODEDUREE` bigint(4) NOT NULL,
  `IDENT` bigint(9) NOT NULL,
  `NBPERSONNES` bigint(4) DEFAULT NULL,
  `DATERESA` date DEFAULT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  PRIMARY KEY (`NUMRESA`),
  KEY `I_FK_RESERVATION_SALLE` (`IDSALLE`),
  KEY `I_FK_RESERVATION_RESERVANT` (`IDENT`),
  KEY `I_FK_RESERVATION_DURE` (`CODEDUREE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
CREATE TABLE IF NOT EXISTS `responsable` (
  `LOGIN` char(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NOM` char(255) DEFAULT NULL,
  `PRENOM` char(32) DEFAULT NULL,
  `ADRESSE` char(32) DEFAULT NULL,
  `TELEPHONE` char(32) DEFAULT NULL,
  `MAIL` char(32) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `IDSALLE` bigint(4) NOT NULL,
  `IDLIEU` smallint(6) NOT NULL,
  `NOMSALLE` char(255) DEFAULT NULL,
  `LARGEUR` decimal(10,2) DEFAULT NULL,
  `LONGUEUR` decimal(10,2) DEFAULT NULL,
  `SURFACE` bigint(4) DEFAULT NULL,
  `HAUTEUR` decimal(10,2) DEFAULT NULL,
  `CAPACIT` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`IDSALLE`),
  KEY `I_FK_SALLE_LIEU` (`IDLIEU`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `LOGIN` char(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NOM` char(255) DEFAULT NULL,
  `PRENOM` char(32) DEFAULT NULL,
  `ADRESSE` char(32) DEFAULT NULL,
  `TELEPHONE` char(32) DEFAULT NULL,
  `MAIL` char(32) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

DROP TABLE IF EXISTS `ville`;
CREATE TABLE IF NOT EXISTS `ville` (
  `IDVILLE` int(11) NOT NULL,
  `IDPAYS` int(11) NOT NULL,
  `NOMVILLE` char(255) DEFAULT NULL,
  `CODEPOSTAL` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDVILLE`),
  KEY `I_FK_VILLE_PAYS` (`IDPAYS`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
