-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema DB_Centraline
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DB_Centraline
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DB_Centraline` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `DB_Centraline` ;

-- -----------------------------------------------------
-- Table `DB_Centraline`.`Centralina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_Centraline`.`Centralina` (
  `idC` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(254) NOT NULL,
  `xCoordinate` DECIMAL(65,15) NOT NULL,
  `yCoordinate` DECIMAL(65,15) NOT NULL,
  `Note` VARCHAR(254) NULL DEFAULT NULL,
  PRIMARY KEY (`idC`),
  UNIQUE INDEX `id_UNIQUE` (`idC` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `DB_Centraline`.`Sensori`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_Centraline`.`Sensori` (
  `idS` INT NOT NULL AUTO_INCREMENT,
  `Tipo` VARCHAR(254) NOT NULL,
  `Coeff.x1` DECIMAL(65,10) NULL DEFAULT NULL,
  `Coeff.x2` DECIMAL(65,10) NULL DEFAULT NULL,
  `Descrizione` VARCHAR(254) NULL DEFAULT NULL,
  `idCentralina` INT NOT NULL,
  PRIMARY KEY (`idS`),
  UNIQUE INDEX `id_UNIQUE` (`idS` ASC) VISIBLE,
  INDEX `id_idx` (`idCentralina` ASC) VISIBLE,
  CONSTRAINT `idC`
    FOREIGN KEY (`idCentralina`)
    REFERENCES `DB_Centraline`.`Centralina` (`idC`))
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `DB_Centraline`.`Osservazioni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_Centraline`.`Osservazioni` (
  `idO` INT NOT NULL AUTO_INCREMENT,
  `Valore` DECIMAL(65,15) NOT NULL,
  `Datastate` DATETIME NOT NULL,
  `idSensore` INT NOT NULL,
  PRIMARY KEY (`idO`),
  UNIQUE INDEX `id_UNIQUE` (`idO` ASC) VISIBLE,
  INDEX `id_idx` (`idSensore` ASC) VISIBLE,
  CONSTRAINT `idS`
    FOREIGN KEY (`idSensore`)
    REFERENCES `DB_Centraline`.`Sensori` (`idS`))
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
