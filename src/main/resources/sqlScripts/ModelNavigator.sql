-- MySQL Script generated by MySQL Workbench
-- Sun Jan 28 22:47:39 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema myNavigator
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myNavigator` DEFAULT CHARACTER SET utf8mb3 ;
USE `myNavigator` ;

-- -----------------------------------------------------
-- Table `myNavigator`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `myNavigator`.`service_stations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`service_stations` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `Company_id` INT NOT NULL,
  `X_coordinate` DOUBLE NOT NULL,
  `Y_coordinate` DOUBLE NOT NULL,
  `countries_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Company_id`, `countries_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_Brands_Group1_idx` (`Company_id` ASC),
  INDEX `fk_service_stations_countries1_idx` (`countries_id` ASC),
  CONSTRAINT `fk_Brands_Group1`
    FOREIGN KEY (`Company_id`)
    REFERENCES `myNavigator`.`company` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_service_stations_countries1`
    FOREIGN KEY (`countries_id`)
    REFERENCES `myNavigator`.`countries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `myNavigator`.`continent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`continent` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`countries` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `Continent_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Continent_id`),
  INDEX `fk_Contries_Continent1_idx` (`Continent_id` ASC) ,
  CONSTRAINT `fk_Contries_Continent1`
    FOREIGN KEY (`Continent_id`)
    REFERENCES `myNavigator`.`continent` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `myNavigator`.`models`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`models` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `model_name` VARCHAR(45) NOT NULL,
  `wheels` INT NOT NULL,
  `axles` INT NOT NULL,
  `tireLoad` DECIMAL(10,0) NOT NULL,
  `tirePressure` DECIMAL(10,0) NOT NULL,
  `max_fuel_capacity` DECIMAL(10,0) NOT NULL,
  `maximum_payload_capacity` DECIMAL(10,0) NOT NULL,
  `max_passengers` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`engine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`engine` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `engine_model` VARCHAR(45) NOT NULL,
  `propulsion` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(45) NOT NULL,
  `Models_id` INT NOT NULL,
  `registration_id` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `Engine_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Models_id`, `Engine_id`),
  INDEX `fk_Cars_Models1_idx` (`Models_id` ASC) ,
  INDEX `fk_Vehicle_Engine1_idx` (`Engine_id` ASC) ,
  CONSTRAINT `fk_Cars_Models1`
    FOREIGN KEY (`Models_id`)
    REFERENCES `myNavigator`.`models` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Vehicle_Engine1`
    FOREIGN KEY (`Engine_id`)
    REFERENCES `myNavigator`.`engine` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`bus_routes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`bus_routes` (
  `idbus_routes` INT NOT NULL,
  `route_name` VARCHAR(45) NOT NULL,
  `buses_id` INT NOT NULL,
  PRIMARY KEY (`idbus_routes`, `buses_id`),
  UNIQUE INDEX `idbus_routes_UNIQUE` (`idbus_routes` ASC) VISIBLE,
  INDEX `fk_bus_routes_buses1_idx` (`buses_id` ASC) VISIBLE,
  CONSTRAINT `fk_bus_routes_buses1`
    FOREIGN KEY (`buses_id`)
    REFERENCES `myNavigator`.`buses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
-- -----------------------------------------------------
-- Table `myNavigator`.`buses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`buses` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `internal_number` INT NOT NULL,
  `Vehicle_id` INT NOT NULL,
  `bus_routes_id` INT NOT NULL,
  `lineName` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`, `Vehicle_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  INDEX `fk_Buses_Vehicle1_idx` (`Vehicle_id` ASC) ,
  INDEX `fk_buses_bus_routes1_idx` (`bus_routes_id` ASC) ,
  CONSTRAINT `fk_Buses_Vehicle1`
    FOREIGN KEY (`Vehicle_id`)
    REFERENCES `myNavigator`.`vehicle` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_buses_bus_routes1`
    FOREIGN KEY (`bus_routes_id`)
    REFERENCES `myNavigator`.`bus_routes` (`idbus_routes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`fuels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`fuels` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) )
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `myNavigator`.`models_has_engine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`models_has_engine` (
  `Models_id` INT NOT NULL,
  `Engine_id` INT NOT NULL,
  PRIMARY KEY (`Models_id`, `Engine_id`),
  INDEX `fk_Models_has_Engine_Engine1_idx` (`Engine_id` ASC) ,
  INDEX `fk_Models_has_Engine_Models1_idx` (`Models_id` ASC) ,
  CONSTRAINT `fk_Models_has_Engine_Engine1`
    FOREIGN KEY (`Engine_id`)
    REFERENCES `myNavigator`.`engine` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Models_has_Engine_Models1`
    FOREIGN KEY (`Models_id`)
    REFERENCES `myNavigator`.`models` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`Connections`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`Connections` (
  `id_origin_station` INT NOT NULL,
  `id_destination_station` INT NOT NULL,
  `id_service_route` INT NOT NULL,
  PRIMARY KEY (`id_service_route`),
  INDEX `fk_service_stations_has_service_stations_service_stations2_idx` (`id_destination_station` ASC) ,
  INDEX `fk_service_stations_has_service_stations_service_stations1_idx` (`id_origin_station` ASC) ,
  CONSTRAINT `fk_service_stations_has_service_stations_service_stations1`
    FOREIGN KEY (`id_origin_station`)
    REFERENCES `myNavigator`.`service_stations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_stations_has_service_stations_service_stations2`
    FOREIGN KEY (`id_destination_station`)
    REFERENCES `myNavigator`.`service_stations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`steps_in_bus_route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`steps_in_bus_route` (
  `bus_routes_idbus_routes` INT NOT NULL,
  `id_conections` INT NOT NULL,
  `nro_step` INT NOT NULL,
  `step_is_final` TINYINT NOT NULL,
  PRIMARY KEY (`bus_routes_idbus_routes`, `id_conections`),
  INDEX `fk_bus_routes_has_service_stations_has_route_to_service_sta_idx` (`id_conections` ASC) ,
  INDEX `fk_bus_routes_has_service_stations_has_route_to_service_sta_idx1` (`bus_routes_idbus_routes` ASC) ,
  CONSTRAINT `fk_bus_routes_has_service_stations_has_route_to_service_stati1`
    FOREIGN KEY (`bus_routes_idbus_routes`)
    REFERENCES `myNavigator`.`bus_routes` (`idbus_routes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bus_routes_has_service_stations_has_route_to_service_stati2`
    FOREIGN KEY (`id_conections`)
    REFERENCES `myNavigator`.`Connections` (`id_service_route`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `myNavigator`.`service_stations_has_fuels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`service_stations_has_fuels` (
  `service_stations_id` INT NOT NULL,
  `service_stations_Company_id` INT NOT NULL,
  `fuels_id` INT NOT NULL,
  `id` INT NOT NULL auto_increment,
  INDEX `fk_service_stations_has_fuels_fuels1_idx` (`fuels_id` ASC) VISIBLE,
  INDEX `fk_service_stations_has_fuels_service_stations1_idx` (`service_stations_id` ASC, `service_stations_Company_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_service_stations_has_fuels_service_stations1`
    FOREIGN KEY (`service_stations_id` , `service_stations_Company_id`)
    REFERENCES `myNavigator`.`service_stations` (`id` , `Company_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_stations_has_fuels_fuels1`
    FOREIGN KEY (`fuels_id`)
    REFERENCES `myNavigator`.`fuels` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myNavigator`.`engine_uses_fuels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myNavigator`.`engine_uses_fuels` (
  `Engine_id` INT NOT NULL,
  `Fuels_id` INT NOT NULL,
  PRIMARY KEY (`Engine_id`, `Fuels_id`),
  INDEX `fk_Engine_has_Fuels_Fuels1_idx` (`Fuels_id` ASC),
  INDEX `fk_Engine_has_Fuels_Engine1_idx` (`Engine_id` ASC),
  CONSTRAINT `fk_Engine_has_Fuels_Engine1`
    FOREIGN KEY (`Engine_id`)
    REFERENCES `myNavigator`.`engine` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Engine_has_Fuels_Fuels1`
    FOREIGN KEY (`Fuels_id`)
    REFERENCES `myNavigator`.`fuels` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
