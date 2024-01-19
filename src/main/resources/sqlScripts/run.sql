-- Ejemplos para la tabla Estaciones
INSERT INTO Estaciones (id, name, coordinateX, coordinateY) VALUES
(1, 'chaco', 10.0, 20.0),
(2, 'corrientes', 15.0, 25.0),
(3, 'C', 8.0, 18.0),
(4, 'D', 12.0, 22.0),
(5, 'E', 14.0, 28.0);
(6, 'Z', -14.0, -28.0);

-- Ejemplos para la tabla Conexiones
INSERT INTO Conexiones (id, routeName) VALUES
(1, 'Route1'),
(2, 'Route2'),
(3, 'Route3');

-- Ejemplos para la tabla Estaciones_Conexiones
INSERT INTO Estaciones_Conexiones (id, id_estacion, id_conexion) VALUES
(1, 1, 1),
(2, 2, 1),
(2, 6, 1),
(3, 3, 2),
(4, 4, 2),
(5, 5, 3);

-- Ejemplos para la tabla Autobuses
INSERT INTO Autobuses (id, lineName) VALUES
(1, 'BusLine1'),
(2, 'BusLine2'),
(3, 'BusLine3');

-- Ejemplos para la tabla Estaciones_Autobuses
INSERT INTO Estaciones_Autobuses (id, id_estacion, id_autobus) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 2),
(4, 4, 2),
(5, 5, 3);
