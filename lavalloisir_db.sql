-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mer 06 Mai 2015 à 20:41
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `lavalloisir`
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `address`
--

INSERT INTO `address` (`id`, `number`, `street`, `zip_code`, `city`) VALUES
(1, 1, 'rue du Commandant-Cousteau ', '53000', 'Laval'),
(3, 11, 'rue du KFC', '53000', 'Laval'),
(10, 24, 'allée des Bretons', '53940', 'Saint-Berthevin'),
(11, 13, 'boulevard du 1er août', '49000', 'Angers'),
(12, 76, 'boulevard Henry Dupond', '53500', 'Ernée'),
(13, 7, 'allée du lard fumé', '53100', 'Mayenne');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(128) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`id`, `label`, `description`) VALUES
(1, 'Loisirs d''intérieur', 'Activités ou club non-sportif se déroulant en salle. (Clubs littéraires, cinémas, jeux de sociétés, ...)'),
(2, 'Loisirs d''extérieur', 'Activité ou club non-sportif que l''on pratique en extérieur. (Randonnée, accrobranche, balade en bateau, ...)'),
(3, 'Activitées sportives', 'Club ou complexe sportif. (Club de basketball, piscine, stade, ...)');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `leisure`
--

INSERT INTO `leisure` (`id`, `title`, `description`, `email`, `phone`, `website`, `id_address`, `id_category`) VALUES
(1, 'Aquabulle', 'Un centre aquatique de Laval avec un espace dédié de 1440 m² pour la détente, les jeux et les sports aquatiques.', 'aquabulle@laval.fr', '0243592999', 'http://www.aquabulle.fr/', 1, 1),
(8, 'Golf Club du Pays de Laval', 'Idéalement situé au bord de La Mayenne, dans un site vallonné, le Golf de Laval s''étend sur 82 hectares. Il propose 27 trous répartis sur 2 parcours de qualité : le Jariel (9 trous) et la Chabossière (18 trous).', 'golfclub@villelaval.fr', '0243493515', 'http://www.laval53-golf.com/', 10, 2),
(9, 'Gourmets en Cuisine', 'Idéalement situé en plein coeur de Laval, Gourmets en Cuisine vous accueille du mardi au samedi.\r\nEmmanuelle Greco vous attend dans son atelier pour vous faire partager sa passion pour la cuisine\r\net vous apprendre les tours de main qui feront le succès de vos recettes.', 'contact@gourmetsencuisine.fr', '0243561140', 'http://www.gourmetsencuisine.fr/', 11, 1),
(10, 'Centre Equestre de Laval', 'En plein coeur du Bois de l''Huisserie, dans un cadre exceptionnel, découvrez l''équitation sur un poney ou perfectionnez-vous à cheval grâce à nos deux moniteurs.\r\nDisciplines proposées : initiation / CSO / handi-cheval / handisport / bébé-cavalier.\r\nLe centre équestre propose aux enfants une balade en poney tous les dimanches à la belle saison pour la modique somme de 6 euros la 1/2h et sans réservation.', 'centreequestre@villelaval.fr', '0243029013', 'http://celaval.free.fr/', 13, 3),
(11, 'EcHOlogia', 'EcHOlogia est un projet de réhabilitation écologique et touristique de l’ancien village chaufournier et des carrières de Louverné. \r\nIl comprend des hébergements insolites, circuits pédagogiques (sentier patrimoine, sentier nature), animations (rallyes, brocantes et marchés, concerts), sport nature et plein air (tir à l''arc, canoë) ... sur les 70 ha d''un site naturel exceptionnel.', 'contact@echologia.fr', '0982476031', 'http://www.echologia.fr/', 12, 2);

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
  `phone` varchar(10) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `registration` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_connection` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `name`, `given_name`, `phone`, `picture`, `registration`, `last_connection`) VALUES
(1, 'julien.landeau@iia-laval.fr', 'g+6AQuAGKjCQT2SKVUKACIx2rEwMeY7WkMj8fSjHiqi9bPs59PYLLg==', 'LANDEAU', 'Julien', '0612345678', NULL, '2015-04-28 12:55:44', '2015-04-28 12:53:35'),
(5, 'mhp@iia-laval.fr', 'nKnY+TRQgSlxJwzc3D5GfJY4psGH3ys1eQlRkizKpUHPDNLSkiXrsw==', 'PAMISEUX', 'Marc-Henri', NULL, '/Lavalloisir/img/user/user_img.png', '2015-05-06 13:28:28', '2015-05-06 13:28:28');

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
