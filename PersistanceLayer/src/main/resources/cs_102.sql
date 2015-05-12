-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cs_102
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cs_102` ;

-- -----------------------------------------------------
-- Schema cs_102
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cs_102` DEFAULT CHARACTER SET utf8 ;
USE `cs_102` ;

-- -----------------------------------------------------
-- Table `cs_102`.`ParentType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`ParentType` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`ParentType` (
  `ParentTypeId` INT NOT NULL AUTO_INCREMENT,
  `ParentName` VARCHAR(45) NOT NULL,
  `Active` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`ParentTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`SubType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`SubType` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`SubType` (
  `SubTypeId` INT NOT NULL AUTO_INCREMENT,
  `SubName` VARCHAR(45) NOT NULL,
  `ParentType` INT NOT NULL,
  PRIMARY KEY (`SubTypeId`),
  INDEX `fk_SubType_ParentType1_idx` (`ParentType` ASC),
  CONSTRAINT `fk_SubType_ParentType1`
    FOREIGN KEY (`ParentType`)
    REFERENCES `cs_102`.`ParentType` (`ParentTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`Status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`Status` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`Status` (
  `Statusid` INT NOT NULL AUTO_INCREMENT,
  `StatusName` VARCHAR(45) NOT NULL,
  `SubTypeId` INT NOT NULL,
  PRIMARY KEY (`Statusid`),
  INDEX `fk_Status_SubType1_idx` (`SubTypeId` ASC),
  CONSTRAINT `fk_Status_SubType1`
    FOREIGN KEY (`SubTypeId`)
    REFERENCES `cs_102`.`SubType` (`SubTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`Members`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`Members` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`Members` (
  `MemberId` INT NOT NULL AUTO_INCREMENT,
  `StatusId` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `CellNumber` VARCHAR(45) NULL,
  `Register` DATE NOT NULL,
  PRIMARY KEY (`MemberId`),
  INDEX `fk_member_status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_member_status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `cs_102`.`Status` (`Statusid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`Login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`Login` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`Login` (
  `LoginId` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(45) NULL,
  `Password` VARCHAR(145) NOT NULL,
  `MemberId` INT NOT NULL,
  PRIMARY KEY (`LoginId`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC),
  INDEX `fk_Login_Members1_idx` (`MemberId` ASC),
  CONSTRAINT `fk_Login_Members1`
    FOREIGN KEY (`MemberId`)
    REFERENCES `cs_102`.`Members` (`MemberId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`Address` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`Address` (
  `AddressId` INT NOT NULL AUTO_INCREMENT,
  `MemberId` INT NOT NULL,
  `AddressLine1` VARCHAR(145) NULL,
  `AddressLine2` VARCHAR(145) NULL,
  `SubType_SubTypeId` INT NOT NULL,
  PRIMARY KEY (`AddressId`),
  INDEX `fk_address_member_idx` (`MemberId` ASC),
  INDEX `fk_Address_SubType1_idx` (`SubType_SubTypeId` ASC),
  CONSTRAINT `fk_address_member`
    FOREIGN KEY (`MemberId`)
    REFERENCES `cs_102`.`Members` (`MemberId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Address_SubType1`
    FOREIGN KEY (`SubType_SubTypeId`)
    REFERENCES `cs_102`.`SubType` (`SubTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cs_102`.`Notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cs_102`.`Notification` ;

CREATE TABLE IF NOT EXISTS `cs_102`.`Notification` (
  `NotificationId` INT NOT NULL AUTO_INCREMENT,
  `StatusId` INT NOT NULL,
  `Message` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`NotificationId`),
  INDEX `fk_notification_status1_idx` (`StatusId` ASC),
  CONSTRAINT `fk_notification_status1`
    FOREIGN KEY (`StatusId`)
    REFERENCES `cs_102`.`Status` (`Statusid`)
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
    REFERENCES `cs_102`.`Notification` (`NotificationId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_has_type_type1`
    FOREIGN KEY (`type_id`)
    REFERENCES `cs_102`.`SubType` (`SubTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
