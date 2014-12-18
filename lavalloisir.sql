-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 18 Décembre 2014 à 15:38
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
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Titre` varchar(50) NOT NULL,
  `Description` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Titre` (`Titre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`Id`, `Titre`, `Description`) VALUES
(1, 'Sport', 'Activités sportives, clubs, centres sportifs, ...'),
(2, 'Audiovisuel', 'Cinémas, musique, théâtres, spectacles, ...'),
(3, 'Danse', 'Tout type de danse, moderne, classique, hip hop, tango, ...'),
(4, 'Restaurant', 'Restaurant, Fast-Food, Pizzeria ... Tout pour se restaurer'),
(5, 'Bar & Cafés', 'Pour se détendre autour d''un verre.'),
(6, 'Pleine Air', 'Activité à faire en pleine air, seul ou en groupe. '),
(7, 'Activité d''intérieur', 'Activité à pratiquer en intérieur');

-- --------------------------------------------------------

--
-- Structure de la table `evaluation`
--

CREATE TABLE IF NOT EXISTS `evaluation` (
  `Id_Utilisateur` bigint(20) NOT NULL,
  `Id_Loisir` int(11) NOT NULL,
  `Note` tinyint(11) NOT NULL,
  PRIMARY KEY (`Id_Utilisateur`,`Id_Loisir`),
  KEY `Id_Utilisateur` (`Id_Utilisateur`),
  KEY `Id_Categorie` (`Id_Loisir`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `evaluation`
--

INSERT INTO `evaluation` (`Id_Utilisateur`, `Id_Loisir`, `Note`) VALUES
(10, 3, 8),
(10, 4, 4),
(11, 3, 6),
(11, 4, 7),
(12, 3, 9),
(12, 4, 9);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Contenu de la table `loisir`
--

INSERT INTO `loisir` (`Id`, `Nom`, `Adresse`, `Description`, `Telephone`, `Email`, `Id_Categorie`) VALUES
(3, 'Mc Donald''s', 'Avenue de lattre-de-Tassigny 53000 LAVAL', 'Fast Food', '0243669866', 'McDonnard@Food.fr', 4),
(4, 'Allo-chinois', '17, rue des Orfevres 53000 LAVAL', 'Restaurant chinois ', '0243536565', 'bondour@AlloChinois-laval.fr', 4),
(5, 'La Boite a Pizza', '47, Avenue Robert-Buron', 'Pizzeria et livraison à domicile', '0243560102', 'Boite@pizza.fr', 4),
(6, 'Valkris', 'Centre commercial E.Leclerc, Parc des Loges 53940 Saint-Berthevin', 'Cafétéria ', '0243029697', 'Valkris@Cafettte.fr', 4),
(7, 'La galette de Couesnon', '31, place Jean-Moulin', 'Restaurant Creperie', '0243560209', 'contact@creperie.fr', 4),
(8, 'Cineville', '25, quai Gambetta 53000 LAVAL', 'Cinema, 9 salles. www.laval.cineville.fr', '0243987425', 'laval@cineville.fr', 2),
(9, 'Le Théatre', 'Rue de la Paix 53000 LAVAL', 'Théatre. www.letheatre.laval.fr', '0243491955', 'laville@laval.fr', 2),
(10, '6PAR4', '177, rue du Vieux-Saint-Louis 53000 LAVAL', 'salle de spectacle, concert, représentation. www.6par4.fr', '0243597780', '6par4@scene.fr', 2),
(11, 'Café-Sucré', '(Auccun adresse)', 'Chorale de jeune (15-25 ans). Le mardi : 18H30-20H15', '0243693168', 'Café-sucré@gmail.fr', 2),
(12, 'Tango Bueno', '23, rue Andre-Bellesort 53000 LAVAL', 'Ecole de danse ', '0243581090', 'tangobueno@hotmail.fr', 3),
(13, 'Centre de danse - La Bougeotte', '15 rue du Britais 53000 LAVAL', 'Cours de Salsa, Hip-Hop, flamenco, danse Orientale, West cost ... www.labougeotte.fr', '0243538014', 'centrededanse-labougeotte@orange.fr', 3),
(14, 'Le palais de la Bière', '62, bd Louis-Armand, 53940 Saint-Berthevin', 'Dégustation de bierre', '0243682527', 'Biere@Lepalais.fr', 5),
(17, 'Le Rond-Point', '2 rue de Val-de-Mayenne', 'Pub-bar', '0243537166', 'LeRond-Point@Bar.fr', 5),
(20, 'Laser Game', '25; bd de l''Industrie 53000 LAVAL', 'Jeu de laser en salle', '0243560285', 'espacegame@hotmail.fr', 7),
(22, 'Laval Loisir KArt', 'Circuit du BeauSoleil, Route d''Angers', 'Mardi-Vendredi : 14-19H / Week-End : 15H30-19H30. www.karting-laval.fr', '0243491595', 'kart-laval@hotmail.fr', 7),
(23, 'CAP FORM Bowling', '64, rue Henri-Batard 53000 LAVAL', 'Bowling, billard, karting, bar. ', '0243028989', 'capform-53@orange.fr', 7),
(25, 'Aquabulle', 'Rue du Commandant-Cousteau', 'Complexe aquatique : piscine balneo, jacuzzi, sauna toboggan ...', '0243592999', 'laval@aquabulle.fr', 7),
(30, 'Golf LAVAL Change', ' route de Niafles, CHANGE', 'Parcours de golf', '0243531603', 'Golf@change.fr', 6),
(31, 'Paintball Aventure', 'Zone artisanale nord , Louverné', '20e : 100 billes / 25e : 200 billes / 30e : 300 billes 37e; 500 billes. Peinture biodégradable ', '0243643153', 'Paintball@Aventure.fr', 6),
(32, 'Canoe-Kayak', '181, rue de la Filature 53000 LAVAL', 'Canoe-Kayak sur la mayenne', '0243670327', 'kayak@laval-laville.fr', 6),
(33, 'ULM LAVAL', 'Aerodrome de laval BeauSoleil', 'Club d''aviation', '0609717427', 'ULM@Laval.fr', 6),
(34, 'Orientation 53', 'Cref, 53000 LAVAL', 'Course d''orientation pédestre, en raid ou en VTT', '0661462359', 'orientation53@laval.fr', 6),
(36, 'L''acropole', '11, Rue du dome 53000 LAVAL', 'Fitness Cardio-Trainning, musculation ...', '0243670302', 'acropole@laval.fr', 1),
(37, 'Parachutisme LAVAL', '(Aucune Adresse indiqué)', 'Centre-Ecole de parachutisme. http://parachutismelaval.fr', '0243535302', 'parachutisme@laval.fr', 1),
(38, 'Laval tennis club', '32, rue Hebert 53000 LAVAL', 'Loisir et competition', '0243670704', 'TennisLaval@gmail.com', 1),
(39, 'Laval cyclisme 53', '27, rue PiedNoir 53000 LAVAL', 'www.lavalcyclisme53.fr', '0674949217', 'cyclisme@laval-laville.fr', 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=27 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`Id`, `Nom`, `Prenom`, `Email`, `Login`, `MotDePasse`, `PhotoProfil`, `DateInscription`) VALUES
(10, 'LANDEAU', 'Julien', 'j.landeau@iia-laval.fr', 'Jyké', 'p5FCqu94rgUqdW+qpbP2/8jJqSB4zJeLzM47r6x/SelyPIR8+mq+JA==', 'WebContent/img/profil_picture.jpeg', '2014-12-18 14:28:58'),
(11, 'MARCHAL', 'Pierre', 'p.marchal@iia-laval.fr', 'Kayyow', '4TYry6tIzrCE8qRqptys0RBLp2F1URD5ayT3e1KTaxljcDXtLJ5b9g==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:31:30'),
(12, 'VAN NOORT', 'Lucas', 'l.vannoort@iia-laval.fr', 'Loucasse', 'xBszhRbnkwT/V4xZYi7jlF1dCdcYcz/QlUq10l5zaEyY488vz5pxEQ==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:32:27'),
(13, 'LEPAGE', 'Alexis', 'a.lepage@iia-laval.fr', 'Alexey', 'GdvlWhhA6UBJv/3VRkTIEqvPidXuEQIVyqBrzwa20fB70ZimCwxAPw==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:32:56'),
(14, 'SUPIOT', 'Theodore', 't.supiot@iia-laval.fr', 'T-Hodor', 'Qj13LxelBfR3OuOQnbIOmIKCVC5G7QbaeWHkV2DBJ/2erh3e/58zgA==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:34:00'),
(15, 'GEOFFROY', 'Maxime', 'm.geoffroy@iia-laval.fr', 'Remet-nous-des-Glaçons', 'TJHL1JfkWa9KuW9Q2wgpHrU4o4/GYMUBpoyXvQ4zlXiQSmTp1bXWKA==', 'WebContent/img/profil_picture.jpeg', '2014-12-18 14:29:10'),
(16, 'HAMELIN', 'Ianis', 'i.hamelin@iia-laval.fr', 'Hiamel', 'wGjnDjF4oCNEIyDkavGrb/lBWWmwkdBvBi29kpysil/7Ns8xcrU8Fw==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:37:09'),
(17, 'PEREIRA', 'Damien', 'd.pereira@iia-laval.fr', 'Dam-Dam-Deo', 'j8Qp8HrbY1qPB+zkjkoPdHeQ6IXvDSg2rrvhlJK5Vb/UJvCvaQn33Q==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:38:10'),
(18, 'BOUSSARD', 'Kevin', 'k.boussard@iia-laval.fr', 'Kbouss', '8xtc6iPbyrl5Xk8hVN6/UTSg3Rk5reWua7EFNS5/u9E5Wj7GI73n/w==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:38:42'),
(19, 'PLANCHAIS', 'Elodie', 'e.planchais@iia-laval.fr', 'Bouff-Tout', 'vOuipEEYP+p4K68f404ifQp8+FCarkSnJ+ibiR2THzFqv3qCmrmPcg==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:42:12'),
(20, 'SOUCHU', 'Alan', 'a.souchu@iia-laval.fr', 'Allan-anas', 'w6oorgVqZsuarS7zuWarcvkW4CjJC0/cJ+z1kELwCy+M5kDlI4n5rQ==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:43:18'),
(21, 'COURVASIER', 'Armand', 'a.courvasier@iia-laval.fr', 'Cours-vas-y', 'PsVczZp+uSIoXBel9cXnUzfWHGfRF/Zd5Qb7GQJBE58Mi+d9qZ0z2A==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:44:33'),
(22, 'DEUDON', 'Pierre', 'p.deudon@iia-laval.fr', 'A-un-dons', 'hattgcG9GIZn+U9VYpEFobrN0d7KZtrFyizIHkHKyoUQfgRszgMqfw==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:46:45'),
(23, 'MILLOT', 'Maxime', 'm.millot@iia-laval.fr', 'Mayonnaise', '25HqjNhpURIYid55B1e0MZ89F+V/zB1u9uVtGxBbm3z4OWreHexp8Q==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:47:54'),
(24, 'FOURNIER', 'Kevin', 'k.fournier@iia-laval.fr', 'Ortis-Damier', 'O1SMi27tVYkbK8+3Y7dxEIV/hm0S3WBZ3DVY8aP5OcXrpRlqyqfubg==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:48:53'),
(25, 'HEYRAUD', 'Paul', 'p.heyraud@iia-laval.fr', 'Polux', 'izNKzL9nCS/Vu+RvxjrTmzZ5MZ1ayxyYF46rf0Z7NWIROBR2QYuDCg==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:50:02'),
(26, 'FAURE', 'Cyrille', 'c.faure@iia-laval.fr', 'C''est-fort-en-Chocolat', '8eeN+NbGgDZcKKEnQeg/Xb30gUxE/RkwLWuVam3V/WuRUKiIBqfHkw==', 'WebContent/img/profil_picture.jpeg', '2014-12-16 19:50:45');

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
