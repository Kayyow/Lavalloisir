-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 07 Décembre 2014 à 14:56
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `lavalloisir`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  `Num` int(10) unsigned NOT NULL,
  `Rue` varchar(50) NOT NULL,
  `CodePostale` int(5) unsigned NOT NULL,
  `Ville` varchar(50) NOT NULL,
  `Description` varchar(256) NOT NULL,
  `Telephone` varchar(12) DEFAULT NULL,
  `Email` int(50) DEFAULT NULL,
  `Id_Categorie` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `Id_Categorie` (`Id_Categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(30) NOT NULL,
  `Prenom` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Login` varchar(30) NOT NULL,
  `MotDePasse` varchar(128) NOT NULL,
  `PhotoProfil` varchar(128) NOT NULL,
  `DateInscription` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Login` (`Login`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
