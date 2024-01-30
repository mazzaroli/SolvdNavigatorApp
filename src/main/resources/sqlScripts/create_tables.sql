-- Create navigator database
CREATE DATABASE IF NOT EXISTS navigator;
USE navigator;

-- Stations Table
CREATE TABLE Stations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    station_name VARCHAR(255) NOT NULL,
    coordinate_x DOUBLE NOT NULL,
    coordinate_y DOUBLE NOT NULL
);

-- Connections Table
CREATE TABLE Connections (
    id INT PRIMARY KEY AUTO_INCREMENT,
    origin_station_id INT,
    destination_station_id INT,
    FOREIGN KEY (origin_station_id) REFERENCES Stations(id),
    FOREIGN KEY (destination_station_id) REFERENCES Stations(id)
);

-- Buses Table
CREATE TABLE Buses (
    id INT PRIMARY KEY AUTO_INCREMENT,
    line_name VARCHAR(255) NOT NULL
);

-- Routes Table with CASCADE DELETE
CREATE TABLE Routes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    bus_id INT,
    route_order INT NOT NULL,
    station_id INT,
    FOREIGN KEY (bus_id) REFERENCES Buses(id) ON DELETE CASCADE,
    FOREIGN KEY (station_id) REFERENCES Stations(id)
);
