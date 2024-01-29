
-- Insert data into Stations table
INSERT INTO navigator.Stations (id, name, coordinateX, coordinateY)
VALUES
    (1, 'Shell Station Buenos Aires', -34.615852, -58.417758),
    (2, 'A3C M6, Voložin', 53.899161, 27.566893),
    (3, 'YPF Station Cordoba', -31.420083, -64.188776),
    (4, 'A3C M10 Vobrub', 53.915647, 27.532477),
    (5, 'YPF Station Buenos Aires', -34.607568, -58.384572),
    (6, 'A3C Station Minsk', 53.870503, 27.586374),
    (7, 'Shell Station Cordoba', -31.431256, -64.193020);

-- Insert data into Connections table
INSERT INTO navigator.Connections (id, id_origin_station, id_destination_station)
VALUES
    (1, 1, 2),
    (2, 2, 3),
    (3, 3, 1),
    (4, 1, 3),
    (5, 3, 2),
    (6, 2, 1),
    (7, 4, 5),
    (8, 5, 6),
    (9, 6, 7),
    (10, 7, 4);

    
    
-- Insert data into Buses table
INSERT INTO navigator.Buses (id, lineName)
VALUES
(1, 'BusLine1'),
(2, 'BusLine2'),
(3, 'BusLine3'),
(4, 'Busline4');


-- Assuming navigator is the name of your database
USE navigator;

-- Insert into Routes
INSERT INTO Routes (id, id_bus, route_order, id_station)
VALUES
    (1, 1, 1, 1),
    (2, 1, 2, 2),
    (3, 1, 3, 3),
    (4, 2, 1, 5),
    (5, 2, 2, 1),
    (6, 3, 1, 6),
    (7, 3, 2, 7),
    (8, 4, 3, 4),
    (9, 4, 4, 1),
    (10, 1, 6, 2),
    (11, 2, 5, 7),
    (12, 3, 5, 1),
    (13, 4, 6, 2),
    (14, 1, 4, 3),
    (15, 2, 2, 5),
    (16, 3, 4, 7),
    (17, 4, 3, 6),
    (18, 1, 5, 3),
    (19, 2, 4, 2),
    (20, 3, 3, 7);
