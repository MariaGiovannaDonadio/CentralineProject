CREATE DATABASE DB_Centraline;

CREATE TABLE `Centralina` (
  `idC` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(254) NOT NULL,
  `xCoordinate` decimal(65,15) NOT NULL,
  `yCoordinate` decimal(65,15) NOT NULL,
  `Note` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idC`),
  UNIQUE KEY `id_UNIQUE` (`idC`)
);
CREATE TABLE `Sensori` (
  `idS` int NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(254) NOT NULL,
  `Coeff.x1` decimal(65,10) DEFAULT NULL,
  `Coeff.x2` decimal(65,10) DEFAULT NULL,
  `Descrizione` varchar(254) DEFAULT NULL,
  `idCentralina` int NOT NULL,
  PRIMARY KEY (`idS`),
  UNIQUE KEY `id_UNIQUE` (`idS`),
  KEY `id_idx` (`idCentralina`),
  CONSTRAINT `idC` FOREIGN KEY (`idCentralina`) REFERENCES `Centralina` (`idC`)
);
CREATE TABLE `Osservazioni` (
  `idO` int NOT NULL AUTO_INCREMENT,
  `Valore` decimal(65,15) NOT NULL,
  `Datastate` datetime NOT NULL,
  `idSensore` int NOT NULL,
  PRIMARY KEY (`idO`),
  UNIQUE KEY `id_UNIQUE` (`idO`),
  KEY `id_idx` (`idSensore`),
  CONSTRAINT `idS` FOREIGN KEY (`idSensore`) REFERENCES `Sensori` (`idS`)
);