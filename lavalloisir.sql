-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 16 Décembre 2014 à 19:31
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `lavalloisir`
--
CREATE DATABASE IF NOT EXISTS `lavalloisir` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `lavalloisir`;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Titre` varchar(50) NOT NULL,
  `Description` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Titre` (`Titre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`Id`, `Titre`, `Description`) VALUES
(1, 'Sport', 'Activités sportives, clubs, centres sportifs, ...'),
(2, 'Audiovisuel', 'Cinémas, musique, théâtres, spectacles, ...'),
(3, 'Danse', 'Tout type de danse, moderne, classique, hip hop, tango, ...');

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE IF NOT EXISTS `evaluation` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Note` tinyint(3) unsigned NOT NULL,
  `Option_Courte` varchar(100) NOT NULL,
  `Commentaire` varchar(300) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Id_Utilisateur` bigint(20) NOT NULL,
  `Id_Loisir` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_Utilisateur` (`Id_Utilisateur`),
  KEY `Id_Categorie` (`Id_Loisir`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `loisir`
--

CREATE TABLE IF NOT EXISTS `loisir` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(30) NOT NULL,
  `Adresse` varchar(256) NOT NULL,
  `Description` varchar(256) NOT NULL,
  `Telephone` varchar(12) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Id_Categorie` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_Categorie` (`Id_Categorie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `loisir`
--

INSERT INTO `loisir` (`Id`, `Nom`, `Adresse`, `Description`, `Telephone`, `Email`, `Id_Categorie`) VALUES
(1, 'NomNomNom', 'Adresse address 123', 'Description DESCRIPTIOOOOON !!!!', '0607060706', 'address@domain.com', 2),
(2, 'AS la BaconniÃ¨re', '06 rue du Fleuve 53040 La BaconniÃ¨re', 'Club de football', '0612345678', 'as-baco@domain.com', 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Prenom` varchar(30) CHARACTER SET latin1 NOT NULL,
  `Email` varchar(50) CHARACTER SET latin1 NOT NULL,
  `Login` varchar(30) CHARACTER SET latin1 NOT NULL,
  `MotDePasse` varchar(128) CHARACTER SET latin1 NOT NULL,
  `PhotoProfil` varchar(128) CHARACTER SET latin1 NOT NULL,
  `DateInscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Login` (`Login`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=10 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`Id`, `Nom`, `Prenom`, `Email`, `Login`, `MotDePasse`, `PhotoProfil`, `DateInscription`) VALUES
(1, 'Marchal', 'Pierre', 'kayyow@hotmail.fr', 'Kayyow', '8ZXj+im7B1uJDQSrjynKltkdXq/GOy3rRukibsFoEpz8TRIe5k356g==', 'WebContent/img/profil_picture.jpeg', '2014-12-13 20:51:16'),
(2, 'Landeau', 'Julien', 'j.landeau@iia-laval.fr', 'Jykeon', 'QqDNfuyO+kpfYfMU6CD/BCLXqOlJS0sQBEVJXYTJEeoG3dakL0rUaA==', 'WebContent/img/profil_picture.jpeg', '2014-12-10 23:36:01'),
(3, 'Azerty', 'Ytreza', 'aerzrt@fdqfW.fr', 'rssfqg', 'oxQv+2I7Wx+qWRIypd0Mo+DApnOIJX1w2iEF7Ds9Eq1rKrrySM/WJg==', 'WebContent/img/profil_picture.jpeg', '2014-12-11 14:57:47'),
(4, 'Bababa', 'Rachid', 'rachid.3ba@toto.fr', 'fdfdfd', '9oOxo0HGGa2JLrScUjKiInF59R6I6fdsNPcAgW3ZYK20Ioxyda4YIQ==', 'WebContent/img/profil_picture.jpeg', '2014-12-13 19:14:46'),
(5, 'TEST', 'Test', 'test@test.test', 'test', 'YIKINcqc1UxHm2b8xj86JaEb0F7PrQQu+y+2IWP83E9HjSoX0rocQg==', 'WebContent/img/profil_picture.jpeg', '2014-12-13 20:19:28'),
(6, 'azeataze', 'azezeaze', 'eazeaz@fdsfdd.fr', 'fasada', 'fyfmRV6AovYnZmBiGuGlPi3n5SF8qBwXcxekjy3inRJSQWG5PbRUiw==', 'WebContent/img/profil_picture.jpeg', '2014-12-13 20:49:52'),
(7, 'TestName', 'TestFirstName', 'TestLogin@test.com', 'TestLogin', 'Q3DbV4HOVdFwIv4oKvi7iiPWfMJsDCe/LnnW6wKLl7RGtbZ3GjWa+A==', 'WebContent/img/profil_picture.jpeg', '2014-12-14 14:08:41'),
(8, 'LAND', 'Jul', 'jul.land@email.com', 'JykÃ©', 'lsPMVQ64VSdKBwCGRA0M3859V7D0UtNS96Uy21DLzQ1S0eh+ad158g==', 'WebContent/img/profil_picture.jpeg', '2014-12-14 14:10:32'),
(9, 'PAMI', 'MaHe', 'mh.pami@gmail.com', 'MHP', 'O2DCYwf+AMa6o2uPiUwLgPy08sHwa1QWGlH7c/saOX+AvZwjcmrnHA==', 'WebContent/img/profil_picture.jpeg', '2014-12-14 14:37:43');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `evaluation_ibfk_1` FOREIGN KEY (`Id_Utilisateur`) REFERENCES `utilisateur` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `evaluation_ibfk_2` FOREIGN KEY (`Id_Loisir`) REFERENCES `loisir` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `loisir`
--
ALTER TABLE `loisir`
  ADD CONSTRAINT `loisir_ibfk_1` FOREIGN KEY (`Id_Categorie`) REFERENCES `categorie` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
