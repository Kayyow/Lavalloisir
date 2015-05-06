-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 06 Mai 2015 à 07:37
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
-- Structure de la table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(4) NOT NULL,
  `street` varchar(255) NOT NULL,
  `zip_code` varchar(5) NOT NULL,
  `city` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `address`
--

INSERT INTO `address` (`id`, `number`, `street`, `zip_code`, `city`) VALUES
(1, 1, 'Rue du Commandant-Cousteau ', '53000', 'LAVAL'),
(3, 11, 'rue du kfc', '53000', 'Laval'),
(4, 11, 'rue du kfc', '53000', 'Laval'),
(5, 11, 'rue du kfc', '53000', 'Laval'),
(6, 11, 'rue du kfc', '53000', 'Laval'),
(7, 0, '', '', ''),
(8, 0, '', '', ''),
(9, 0, '', '', '');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(128) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`id`, `label`, `description`) VALUES
(1, 'Loisirs d''intérieur', 'Description à venir \r\n');

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE IF NOT EXISTS `evaluation` (
  `note` int(11) NOT NULL,
  `opinion` text NOT NULL,
  `id_leisure` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_leisure`,`id_user`),
  KEY `id_leisure` (`id_leisure`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `leisure`
--

CREATE TABLE IF NOT EXISTS `leisure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `website` varchar(255) DEFAULT NULL,
  `id_address` int(11) NOT NULL,
  `id_category` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_address` (`id_address`),
  KEY `id_category` (`id_category`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `leisure`
--

INSERT INTO `leisure` (`id`, `title`, `description`, `email`, `phone`, `website`, `id_address`, `id_category`) VALUES
(1, 'Aquabulle', 'Description à venir \r\n', 'aquabulle@laval.fr', '243980000', NULL, 1, 1),
(3, 'mcdo', 'mcdo', 'mcdo@laval.fr', '212345678', NULL, 3, 1),
(4, 'mcdo', 'mcdo', 'mcdo@laval.fr', '212345678', NULL, 4, 1),
(5, 'mcdo', 'mcdo', 'mcdo@laval.fr', '212345678', NULL, 5, 1),
(6, 'mcdo', 'mcdo', 'mcdo@laval.fr', '212345678', NULL, 6, 1),
(7, '', 'System.Windows.Documents.TextSelection', '', '', '', 7, 1),
(8, '', 'System.Windows.Documents.TextSelection', '', '', '', 8, 1),
(9, '', 'System.Windows.Documents.TextSelection', '', '', '', 9, 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `name` varchar(255) NOT NULL,
  `given_name` varchar(255) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `registration` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_connection` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `name`, `given_name`, `phone`, `picture`, `registration`, `last_connection`) VALUES
(1, 'julien.landeau@iia-laval.fr', 'g+6AQuAGKjCQT2SKVUKACIx2rEwMeY7WkMj8fSjHiqi9bPs59PYLLg==', 'LANDEAU', 'Julien', '0612345678', NULL, '2015-04-28 12:55:44', '2015-04-28 12:53:35');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `evaluation`
--
ALTER TABLE `evaluation`
  ADD CONSTRAINT `evaluation_ibfk_1` FOREIGN KEY (`id_leisure`) REFERENCES `leisure` (`id`),
  ADD CONSTRAINT `evaluation_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `leisure`
--
ALTER TABLE `leisure`
  ADD CONSTRAINT `leisure_ibfk_1` FOREIGN KEY (`id_address`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `leisure_ibfk_2` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
