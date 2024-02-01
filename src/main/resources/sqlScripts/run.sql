SET FOREIGN_KEY_CHECKS=0;

-- Insert stations
INSERT INTO Stations (station_name, coordinate_x, coordinate_y) VALUES
('Plaza 25 de Mayo', -27.451132, -58.986606),
('Av Sarmiento 500', -27.447226, -58.982173),
('Plaza 12 de Octubre', -27.443479, -58.986483),
('Av 25 de mayo 500', -27.447240, -58.990845),
('Plaza 9 de Julio', -27.451174, -58.995239),
('Plaza Espana', -27.458902, -58.986494),
('Av 9 de Julio 500', -27.455005, -58.982158),
('Av Italia 500', -27.451271, -58.977913),
('Av Hernandariaz 0', -27.444982, -58.993654);


-- Insert connections between stations
INSERT INTO Connections (origin_station_id, destination_station_id) VALUES
(1, 4),
(1, 2),
(1, 7),
(2, 1),
(2, 3),
(2, 8),
(3, 2),
(3, 4),
(4, 1),
(4, 9),
(4, 3),
(5, 6),
(6, 5),
(6, 7),
(7, 8),
(7, 6),
(7, 1),
(8, 7),
(8, 2),
(9, 4);

-- Insert buses for each line
INSERT INTO Buses (line_name, conexion_id) VALUES
('Linea A', 10),
('Linea A', 20),
('Linea A', 1),
('Linea A', 9),
('Linea A', 3),
('Linea A', 17),
('Linea A', 15),
('Linea A', 18),
('Linea B', 2),
('Linea B', 4),
('Linea B', 6),
('Linea B', 19),
('Linea C', 8),
('Linea C', 11),
('Linea C', 7),
('Linea C', 5),
('Linea D', 16),
('Linea D', 14),
('Linea D', 12),
('Linea D', 13);