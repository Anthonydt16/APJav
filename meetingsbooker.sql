-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 06 jan. 2022 à 18:56
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
-- Base de données : `meetingsbooker`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `LOGIN` varchar(128) NOT NULL,
  `PASSWORD` varchar(250) DEFAULT NULL,
  `NOM` varchar(128) DEFAULT NULL,
  `PRENOM` varchar(128) DEFAULT NULL,
  `ADRESSE` varchar(128) DEFAULT NULL,
  `TELEPHONE` varchar(128) DEFAULT NULL,
  `MAIL` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `commercial`
--

DROP TABLE IF EXISTS `commercial`;
CREATE TABLE IF NOT EXISTS `commercial` (
  `LOGIN` varchar(128) NOT NULL,
  `POURCENTAGECOMMERCIAL` float(5,2) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NOM` char(255) DEFAULT NULL,
  `PRENOM` char(32) DEFAULT NULL,
  `ADRESSE` char(32) DEFAULT NULL,
  `TELEPHONE` char(32) DEFAULT NULL,
  `MAIL` char(32) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commercial`
--

INSERT INTO `commercial` (`LOGIN`, `POURCENTAGECOMMERCIAL`, `PASSWORD`, `NOM`, `PRENOM`, `ADRESSE`, `TELEPHONE`, `MAIL`) VALUES
('Pierre', 12.00, '098f6bcd4621d373cade4e832627b4f6', 'Campmas', 'Pierre', '12 rue ola', '090000000', '090000000'),
('Jean', 7.55, '098f6bcd4621d373cade4e832627b4f6', 'Yves', 'Jean', '13 rue de la cours', '123456789', 'jean.yves@contact.om');

-- --------------------------------------------------------

--
-- Structure de la table `contacter`
--

DROP TABLE IF EXISTS `contacter`;
CREATE TABLE IF NOT EXISTS `contacter` (
  `LOGIN` varchar(128) NOT NULL,
  `DATECONTACT` varchar(10) NOT NULL,
  `IDENT` bigint(9) NOT NULL,
  PRIMARY KEY (`LOGIN`,`DATECONTACT`,`IDENT`),
  KEY `I_FK_CONTACTER_COMMERCIAL` (`LOGIN`),
  KEY `I_FK_CONTACTER_LOUEUR` (`IDENT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `contacter`
--

INSERT INTO `contacter` (`LOGIN`, `DATECONTACT`, `IDENT`) VALUES
('Pierre', '21/12/2021', 1);

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

--
-- Déchargement des données de la table `dure`
--

INSERT INTO `dure` (`CODEDUREE`, `LIBELLEDUREE`) VALUES
(1, 'Demi-journée'),
(2, 'Journée entière');

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

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`IDENT`, `IDVILLE`, `NOMENT`, `ADRESSEENT`, `TELENT`, `EMAIL`) VALUES
(1, 3, 'tikisoft', '12 rue robert', '0000000000', 'tikisoft@contact.com'),
(3, 1, 'test', 'testt', '123456789', 'test@gmail.com'),
(2, 5, 'Microsoft', '13 cours de la marne', '123456789', 'Microsoft@contact.com');

-- --------------------------------------------------------

--
-- Structure de la table `inscrit`
--

DROP TABLE IF EXISTS `inscrit`;
CREATE TABLE IF NOT EXISTS `inscrit` (
  `LOGIN` varchar(128) NOT NULL,
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
  `IDLIEU` int(11) NOT NULL,
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

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`IDLIEU`, `IDVILLE`, `IDENT`, `LIBELLELIEU`, `ADRESSELIEU`, `COORDX`, `COORDY`, `ANNULATIONGRATUITE`, `NBETOILES`, `DESCRIPTIF`) VALUES
(1, 1, 1, 'Paname city', '12 cours de la marne', 123123, 123123, 'Oui', 4, 'Jolie salon avec vue sur la tour eiffel'),
(2, 7, 1, 'Himalaya', 'Mont everest à gauche', 123123, 123123, 'Non', 5, 'Magnifique vu sur la neige');

-- --------------------------------------------------------

--
-- Structure de la table `loueur`
--

DROP TABLE IF EXISTS `loueur`;
CREATE TABLE IF NOT EXISTS `loueur` (
  `IDENT` bigint(9) NOT NULL,
  `LOGIN` varchar(128) NOT NULL,
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

--
-- Déchargement des données de la table `loueur`
--

INSERT INTO `loueur` (`IDENT`, `LOGIN`, `NOM`, `PRENOM`, `CONTACTEO_N`, `TYPEINSCRIPTION`, `MAILCONTACT`, `TELCONTACT`, `NOMENT`, `ADRESSEENT`, `TELENT`, `EMAIL`) VALUES
(1, 'Tikisoft', 'Campmas', 'Pierre', '1', 'Commercial', 'pierre@gmail.com', '0400000000', 'tikisoft', '12 rue robert', '0000000000', 'tikisoft@contact.com'),
(3, 'test', 'test', 'test', '1', 'Commercial', 'test@gmail.com', '123456789', 'test', 'testt', '123456789', 'test@gmail.com');

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

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`IDPAYS`, `NOMPAYS`) VALUES
(236, 'United States'),
(235, 'United Kingdom'),
(234, 'United Arab Emirates'),
(233, 'Ukraine'),
(232, 'Uganda'),
(231, 'Tuvalu'),
(230, 'Turks and Caicos Islands'),
(229, 'Turkmenistan'),
(228, 'Turkey'),
(227, 'Tunisia'),
(226, 'Trinidad and Tobago'),
(225, 'Tonga'),
(224, 'Tokelau'),
(223, 'Togo'),
(222, 'Timor-Leste (East Timor)'),
(221, 'Thailand'),
(220, 'Tanzania'),
(219, 'Tajikistan'),
(218, 'Taiwan'),
(217, 'Syria'),
(216, 'Switzerland'),
(215, 'Sweden'),
(214, 'Swaziland'),
(213, 'Svalbard and Jan Mayen'),
(212, 'Suriname'),
(211, 'Sudan'),
(210, 'Sri Lanka'),
(209, 'Spain'),
(208, 'South Sudan'),
(207, 'South Korea'),
(206, 'South Georgia'),
(205, 'South Africa'),
(204, 'Somalia'),
(203, 'Solomon Islands'),
(202, 'Slovenia'),
(201, 'Slovakia'),
(200, 'Sint Maarten'),
(199, 'Singapore'),
(198, 'Sierra Leone'),
(197, 'Seychelles'),
(196, 'Serbia'),
(195, 'Senegal'),
(194, 'Saudi Arabia'),
(193, 'Sao Tome and Principe'),
(192, 'San Marino'),
(191, 'Samoa'),
(190, 'Saint Vincent and the Grenadines'),
(189, 'Saint Pierre and Miquelon'),
(188, 'Saint Martin'),
(187, 'Saint Lucia'),
(186, 'Saint Kitts and Nevis'),
(185, 'Saint Helena'),
(184, 'Saint Barthelemy'),
(183, 'Rwanda'),
(182, 'Russia'),
(181, 'Romania'),
(180, 'Reunion'),
(179, 'Qatar'),
(178, 'Puerto Rico'),
(177, 'Portugal'),
(176, 'Poland'),
(175, 'Pitcairn'),
(174, 'Phillipines'),
(173, 'Peru'),
(172, 'Paraguay'),
(171, 'Papua New Guinea'),
(170, 'Panama'),
(169, 'Palestine'),
(168, 'Palau'),
(167, 'Pakistan'),
(166, 'Oman'),
(165, 'Norway'),
(164, 'Northern Mariana Islands'),
(163, 'North Korea'),
(162, 'Norfolk Island'),
(161, 'Niue'),
(160, 'Nigeria'),
(159, 'Niger'),
(158, 'Nicaragua'),
(157, 'New Zealand'),
(156, 'New Caledonia'),
(155, 'Netherlands'),
(154, 'Nepal'),
(153, 'Nauru'),
(152, 'Namibia'),
(151, 'Myanmar (Burma)'),
(150, 'Mozambique'),
(149, 'Morocco'),
(148, 'Montserrat'),
(147, 'Montenegro'),
(146, 'Mongolia'),
(145, 'Monaco'),
(144, 'Moldava'),
(143, 'Micronesia'),
(142, 'Mexico'),
(141, 'Mayotte'),
(140, 'Mauritius'),
(139, 'Mauritania'),
(138, 'Martinique'),
(137, 'Marshall Islands'),
(136, 'Malta'),
(135, 'Mali'),
(134, 'Maldives'),
(133, 'Malaysia'),
(132, 'Malawi'),
(131, 'Madagascar'),
(130, 'Macedonia'),
(129, 'Macao'),
(128, 'Luxembourg'),
(127, 'Lithuania'),
(126, 'Liechtenstein'),
(125, 'Libya'),
(124, 'Liberia'),
(123, 'Lesotho'),
(122, 'Lebanon'),
(121, 'Latvia'),
(120, 'Laos'),
(119, 'Kyrgyzstan'),
(118, 'Kuwait'),
(117, 'Kosovo'),
(116, 'Kiribati'),
(115, 'Kenya'),
(114, 'Kazakhstan'),
(113, 'Jordan'),
(112, 'Jersey'),
(111, 'Japan'),
(110, 'Jamaica'),
(109, 'Italy'),
(108, 'Israel'),
(107, 'Isle of Man'),
(106, 'Ireland'),
(105, 'Iraq'),
(104, 'Iran'),
(103, 'Indonesia'),
(102, 'India'),
(101, 'Iceland'),
(100, 'Hungary'),
(99, 'Hong Kong'),
(98, 'Honduras'),
(97, 'Heard Island'),
(96, 'Haiti'),
(95, 'Guyana'),
(94, 'Guinea-Bissau'),
(93, 'Guinea'),
(92, 'Guernsey'),
(91, 'Guatemala'),
(90, 'Guam'),
(89, 'Guadaloupe'),
(88, 'Grenada'),
(87, 'Greenland'),
(86, 'Greece'),
(85, 'Gibraltar'),
(84, 'Ghana'),
(83, 'Germany'),
(82, 'Georgia'),
(81, 'Gambia'),
(80, 'Gabon'),
(79, 'French Southern Territories'),
(78, 'French Polynesia'),
(77, 'French Guiana'),
(76, 'France'),
(75, 'Finland'),
(74, 'Fiji'),
(73, 'Faroe Islands'),
(72, 'Falkland Islands (Malvinas)'),
(71, 'Ethiopia'),
(70, 'Estonia'),
(69, 'Eritrea'),
(68, 'Equatorial Guinea'),
(67, 'El Salvador'),
(66, 'Egypt'),
(65, 'Ecuador'),
(64, 'Dominican Republic'),
(63, 'Dominica'),
(62, 'Djibouti'),
(61, 'Denmark'),
(60, 'Democratic Republic of the Congo'),
(59, 'Czech Republic'),
(58, 'Cyprus'),
(57, 'Curacao'),
(56, 'Cuba'),
(55, 'Croatia'),
(54, 'Ivory Coast'),
(53, 'Costa Rica'),
(52, 'Cook Islands'),
(51, 'Congo'),
(50, 'Comoros'),
(49, 'Colombia'),
(48, 'Cocos (Keeling) Islands'),
(47, 'Christmas Island'),
(46, 'China'),
(45, 'Chile'),
(44, 'Chad'),
(43, 'Central African Republic'),
(42, 'Cayman Islands'),
(41, 'Cape Verde'),
(40, 'Canada'),
(39, 'Cameroon'),
(38, 'Cambodia'),
(37, 'Burundi'),
(36, 'Burkina Faso'),
(35, 'Bulgaria'),
(34, 'Brunei'),
(33, 'British Indian Ocean Territory'),
(32, 'Brazil'),
(31, 'Bouvet Island'),
(30, 'Botswana'),
(29, 'Bosnia and Herzegovina'),
(28, 'Bonaire, Sint Eustatius and Saba'),
(27, 'Bolivia'),
(26, 'Bhutan'),
(25, 'Bermuda'),
(24, 'Benin'),
(23, 'Belize'),
(22, 'Belgium'),
(21, 'Belarus'),
(20, 'Barbados'),
(19, 'Bangladesh'),
(18, 'Bahrain'),
(17, 'Bahamas'),
(16, 'Azerbaijan'),
(15, 'Austria'),
(14, 'Australia'),
(13, 'Aruba'),
(12, 'Armenia'),
(11, 'Argentina'),
(10, 'Antigua and Barbuda'),
(9, 'Antarctica'),
(8, 'Anguilla'),
(7, 'Angola'),
(6, 'Andorra'),
(5, 'American Samoa'),
(4, 'Algeria'),
(3, 'Albania'),
(2, 'Aland Islands'),
(1, 'Afghanistan'),
(237, 'Minor Outlying Islands'),
(238, 'Uruguay'),
(239, 'Uzbekistan'),
(240, 'Vanuatu'),
(241, 'Vatican City'),
(242, 'Venezuela'),
(243, 'Vietnam'),
(244, 'Virgin Islands, British'),
(245, 'Virgin Islands, US'),
(246, 'Wallis and Futuna'),
(247, 'Western Sahara'),
(248, 'Yemen'),
(249, 'Zambia'),
(250, 'Zimbabwe');

-- --------------------------------------------------------

--
-- Structure de la table `photos`
--

DROP TABLE IF EXISTS `photos`;
CREATE TABLE IF NOT EXISTS `photos` (
  `IDPHOTOS` bigint(4) NOT NULL,
  `IDSALLE` int(11) NOT NULL,
  `CHEMINPHOTOS` char(255) DEFAULT NULL,
  `PRINCIPALO_N` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IDPHOTOS`),
  KEY `I_FK_PHOTOS_SALLE` (`IDSALLE`)
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

--
-- Déchargement des données de la table `reservant`
--

INSERT INTO `reservant` (`IDENT`, `NOMENT`, `ADRESSEENT`, `TELENT`, `EMAIL`) VALUES
(2, 'Microsoft', '13 cours de la marne', '123456789', 'Microsoft@contact.com');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `NUMRESA` bigint(4) NOT NULL,
  `IDSALLE` int(11) NOT NULL,
  `CODEDUREE` bigint(4) NOT NULL,
  `IDENT` bigint(9) NOT NULL,
  `NBPERSONNES` bigint(4) DEFAULT NULL,
  `DATERESA` date DEFAULT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `MONTANTRESERVATION` varchar(100) NOT NULL,
  PRIMARY KEY (`NUMRESA`),
  KEY `I_FK_RESERVATION_SALLE` (`IDSALLE`),
  KEY `I_FK_RESERVATION_RESERVANT` (`IDENT`),
  KEY `I_FK_RESERVATION_DURE` (`CODEDUREE`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`NUMRESA`, `IDSALLE`, `CODEDUREE`, `IDENT`, `NBPERSONNES`, `DATERESA`, `DATEDEBUT`, `MONTANTRESERVATION`) VALUES
(1, 1, 1, 2, 4, '2021-12-21', '2021-12-22', '200'),
(2, 1, 2, 2, 7, '2022-01-06', '2022-01-07', '400'),
(3, 2, 1, 2, 4, '2021-12-22', '2021-12-23', '100');

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

DROP TABLE IF EXISTS `responsable`;
CREATE TABLE IF NOT EXISTS `responsable` (
  `LOGIN` varchar(128) NOT NULL,
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
  `IDSALLE` int(11) NOT NULL,
  `IDLIEU` int(11) NOT NULL,
  `NOMSALLE` varchar(128) DEFAULT NULL,
  `LARGEUR` double(13,2) DEFAULT NULL,
  `LONGUEUR` double(13,2) DEFAULT NULL,
  `SURFACE` int(11) DEFAULT NULL,
  `HAUTEUR` double(13,2) DEFAULT NULL,
  `CAPACITE` double(13,2) DEFAULT NULL,
  `prixDemiJournee` varchar(100) NOT NULL,
  PRIMARY KEY (`IDSALLE`),
  KEY `I_FK_SALLE_LIEU` (`IDLIEU`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`IDSALLE`, `IDLIEU`, `NOMSALLE`, `LARGEUR`, `LONGUEUR`, `SURFACE`, `HAUTEUR`, `CAPACITE`, `prixDemiJournee`) VALUES
(1, 1, 'Salle de réunion', 35.00, 54.00, 123, 7.00, 70.00, '200'),
(2, 2, 'BrainStorming', 10.00, 10.00, 15, 4.00, 6.00, '100');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `LOGIN` varchar(128) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NOM` varchar(128) DEFAULT NULL,
  `PRENOM` varchar(128) DEFAULT NULL,
  `ADRESSE` varchar(128) DEFAULT NULL,
  `TELEPHONE` char(32) DEFAULT NULL,
  `MAIL` char(32) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`LOGIN`, `PASSWORD`, `NOM`, `PRENOM`, `ADRESSE`, `TELEPHONE`, `MAIL`) VALUES
('Pierre', '098f6bcd4621d373cade4e832627b4f6', 'Campmas', 'Pierre', '12 rue ola', '090000000', '090000000'),
('Jean', '098f6bcd4621d373cade4e832627b4f6', 'Jean', 'Yves', '13 rue de la cours', '123456789', 'jean.yves@contact.com');

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

--
-- Déchargement des données de la table `ville`
--

INSERT INTO `ville` (`IDVILLE`, `IDPAYS`, `NOMVILLE`, `CODEPOSTAL`) VALUES
(1, 76, 'Paris', 75000),
(4, 55, 'Zagreb', NULL),
(3, 76, 'Tonneins', 47440),
(2, 76, 'Pessac', 33600),
(5, 83, 'Berlin', NULL),
(6, 46, 'Pekin', NULL),
(7, 46, 'Shanghai', NULL),
(8, 236, 'Los Angeles', NULL),
(9, 76, 'Bordeaux', 33000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
