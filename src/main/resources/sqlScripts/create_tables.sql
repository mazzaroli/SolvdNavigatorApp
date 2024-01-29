-- Create mavigator database
CREATE DATABASE IF NOT EXISTS navigator;
USE navigator;

-- Stations Table
CREATE TABLE Stations (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    coordinateX DOUBLE NOT NULL,
    coordinateY DOUBLE NOT NULL
);

-- Connections Table
CREATE TABLE Connections (
    id INT PRIMARY KEY,
    id_origin_station INT,
    id_destination_station INT,
    FOREIGN KEY (id_origin_station) REFERENCES Stations(id),
    FOREIGN KEY (id_destination_station) REFERENCES Stations(id)
);


-- Buses Table
CREATE TABLE Buses (
    id INT PRIMARY KEY,
    lineName VARCHAR(255) NOT NULL
);


-- Routes Table
CREATE TABLE Routes (
    id INT PRIMARY KEY,
    id_bus INT,
    route_order INT NOT NULL,
    id_station INT,
    FOREIGN KEY (id_bus) REFERENCES Buses(id),
    FOREIGN KEY (id_station) REFERENCES Stations(id)
);

