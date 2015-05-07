-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cs_102
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cs_102
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cs_102` DEFAULT CHARACTER SET utf8 ;
USE `cs_102` ;

-- -----------------------------------------------------
-- Table `cs_102`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`status` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`status_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`members` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`members` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `status_id` INT NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cellnumber` VARCHAR(45) NULL,
  `register` DATE NOT NULL,
  PRIMARY KEY (`member_id`),
  INDEX `fk_member_status1_idx` (`status_id` ASC),
  CONSTRAINT `fk_member_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `cs_102`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`login` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`login` (
  `login_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `member_id` INT NOT NULL,
  `password` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`login_id`, `member_id`),
  INDEX `fk_login_member1_idx` (`member_id` ASC),
  CONSTRAINT `fk_login_member1`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs_102`.`members` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`address` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL,
  `addressline1` VARCHAR(45) NULL,
  `addressline2` VARCHAR(45) NULL,
  PRIMARY KEY (`address_id`),
  INDEX `fk_address_member_idx` (`member_id` ASC),
  CONSTRAINT `fk_address_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `cs_102`.`members` (`member_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`notification` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`notification` (
  `notification_id` INT NOT NULL AUTO_INCREMENT,
  `status_id` INT NOT NULL,
  `message` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`notification_id`),
  INDEX `fk_notification_status1_idx` (`status_id` ASC),
  CONSTRAINT `fk_notification_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `cs_102`.`status` (`status_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`type` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`type` (
  `type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`address_has_type`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `cs_102`.`address_has_type` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`address_has_type` (
  `address_id` INT NOT NULL,
  `type_id` INT NOT NULL,
  PRIMARY KEY (`address_id`, `type_id`),
  INDEX `fk_address_has_type_type1_idx` (`type_id` ASC),
  INDEX `fk_address_has_type_address1_idx` (`address_id` ASC),
  CONSTRAINT `fk_address_has_type_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `cs_102`.`address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_address_has_type_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `cs_102`.`type` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`notification_has_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`notification_has_type` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`notification_has_type` (
  `notification_id` INT NOT NULL,
  `type_id` INT NOT NULL,
  PRIMARY KEY (`notification_id`, `type_id`),
  INDEX `fk_notification_has_type_type1_idx` (`type_id` ASC),
  INDEX `fk_notification_has_type_notification1_idx` (`notification_id` ASC),
  CONSTRAINT `fk_notification_has_type_notification1`
    FOREIGN KEY (`notification_id`)
    REFERENCES `cs_102`.`notification` (`notification_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_has_type_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `cs_102`.`type` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
