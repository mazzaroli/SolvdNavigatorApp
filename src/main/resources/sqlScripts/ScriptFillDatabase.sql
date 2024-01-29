-- Insert data into models
INSERT INTO myNavigator.models (model_name, wheels, axles, tireLoad, tirePressure, max_fuel_capacity, maximum_payload_capacity, max_passengers) VALUES
('Mercedes-Benz Sprinter', 4, 2, 5000, 35, 80, 10000, 15), 
('Volvo XC90', 4, 2, 5500, 37, 90, 11000, 7),             
('Toyota Prius', 4, 2, 4000, 33, 45, 8000, 5),             
('MAN Lions City', 6, 3, 8000, 40, 120, 15000, 30),       
('Volvo 9700', 6, 3, 2800, 36, 140, 1600, 50),
('Mercedes-Benz Citaro', 6, 2, 2000, 34, 100, 1200, 40),
('New Flyer Xcelsior', 8, 3, 3200, 38, 160, 1800, 60),
('MAN Lions City', 6, 3, 2700, 35, 130, 1500, 55);

-- Insert data into engine
INSERT INTO myNavigator.engine (engine_model, propulsion) VALUES
('Mercedes OM642', 'Diesel'),    -- Example bus engine
('Volvo B4204T9', 'Gasoline'),   -- Example SUV engine
('Toyota Hybrid Synergy Drive', 'Hybrid'),   -- Example hybrid car engine
('MAN D1556 LOH', 'Diesel');      -- Another example bus engine

-- Insert data into vehicle
INSERT INTO myNavigator.vehicle (color, Models_id, registration_id, category, Engine_id) VALUES
('Red', 5, 'ABC123', 'Bus', 1),
('Blue', 2, 'XYZ789', 'SUV', 2),
('Green', 3, 'DEF456', 'Hybrid Car', 3),
('White', 6, 'GHI789', 'Bus', 4),
('Black', 2, 'JKL012', 'SUV', 2),
('Silver', 3, 'MNO345', 'Hatchback', 3),
('Yellow', 8, 'PQR678', 'Bus', 1),
('Red', 7, 'HER458', 'Bus', 2);


-- Insert data into service_stations
INSERT INTO myNavigator.service_stations (name, Company_id, X_coordinate, Y_coordinate, countries_id) VALUES
('Shell Station Buenos Aires', 1, -34.615852, -58.417758, 1),
('A3C M6, Voložin', 3, 53.899161, 27.566893, 2),
('YPF Station Cordoba', 2, -31.420083, -64.188776, 1),
('A3C M10 Vobrub', 3, 53.915647, 27.532477, 2),
('YPF Station Buenos Aires', 2, -34.607568, -58.384572, 1),
('A3C Station Minsk', 3, 53.870503, 27.586374, 2),
('Shell Station Cordoba', 1, -31.431256, -64.193020, 1);

-- Insert data into fuels
INSERT INTO myNavigator.fuels (name) VALUES
('Gasoline'),
('Diesel'),
('Natural Gas');

-- Insert data into company
INSERT INTO myNavigator.company (name) VALUES
('Shell'),
('YPF'),
('A3C');

-- Insert data into continent
INSERT INTO myNavigator.continent (name) VALUES
('America'),
('Europe');

-- Insert data into countries
INSERT INTO myNavigator.countries (name, Continent_id) VALUES
('Argentina', 1),
('Belarus', 2);

-- Insert data into service_stations_has_fuels
INSERT INTO myNavigator.service_stations_has_fuels (service_stations_id, service_stations_Company_id, fuels_id) VALUES
(1, 1, 1),
(1, 1, 2),  -- Diesel
(2, 2, 2),  -- Diesel
(2, 2, 1),  -- Gasoline
(3, 3, 1),  -- Gasoline
(3, 3, 2),  -- Diesel
(4, 1, 1),
(4, 1, 2),  -- Diesel
(5, 2, 2),  -- Diesel
(5, 2, 1),  -- Gasoline
(6, 3, 2),  -- Diesel
(6, 3, 1),  -- Gasoline
(7, 1, 1);

-- Insert data into Connections
INSERT INTO myNavigator.Connections (id_origin_station, id_destination_station, id_service_route) VALUES
(1, 2, 1), -- Connection between Shell Station Buenos Aires and A3C M6, Voložin
(2, 3, 2), -- Connection between A3C M6, Voložin and YPF Station Cordoba
(3, 4, 3), -- Connection between YPF Station Cordoba and A3C M10 Vobrub
(4, 5, 4), -- Connection between A3C M10 Vobrub and YPF Station Buenos Aires
(5, 6, 5), -- Connection between YPF Station Buenos Aires and A3C Station Minsk
(6, 7, 6), -- Connection between A3C Station Minsk and Shell Station Cordoba
(7, 1, 7), -- Connection between Shell Station Cordoba and Shell Station Buenos Aires
(1, 3, 8), -- Connection between Shell Station Buenos Aires and YPF Station Cordoba
(2, 4, 9), -- Connection between A3C M6, Voložin and A3C M10 Vobrub
(3, 5, 10), -- Connection between YPF Station Cordoba and YPF Station Buenos Aires
(4, 6, 11), -- Connection between A3C M10 Vobrub and A3C Station Minsk
(5, 7, 12), -- Connection between YPF Station Buenos Aires and Shell Station Cordoba
(6, 1, 13), -- Connection between A3C Station Minsk and Shell Station Buenos Aires
(7, 2, 14); -- Connection between Shell Station Cordoba and A3C M6, Voložin




-- Insert data into steps_in_bus_route for Bus Route 1 Step
INSERT INTO myNavigator.steps_in_bus_route (bus_routes_idbus_routes, id_conections, nro_step, step_is_final) VALUES
(1, 1, 1, 1);

-- Insert data into steps_in_bus_route for Bus Route 2 Steps
INSERT INTO myNavigator.steps_in_bus_route (bus_routes_idbus_routes, id_conections, nro_step, step_is_final) VALUES
(2, 2, 1, 0),
(2, 3, 2, 1);

-- Insert data into steps_in_bus_route for Bus Route 3 Steps
INSERT INTO myNavigator.steps_in_bus_route (bus_routes_idbus_routes, id_conections, nro_step, step_is_final) VALUES
(3, 4, 1, 0),
(3, 5, 2, 0),
(3, 6, 3, 1);

-- Insert data into steps_in_bus_route for Bus Route 4 Steps
INSERT INTO myNavigator.steps_in_bus_route (bus_routes_idbus_routes, id_conections, nro_step, step_is_final) VALUES
(4, 7, 1, 0),
(4, 1, 2, 0),
(4, 2, 3, 0),
(4, 3, 4, 1);


-- Insert data into buses
INSERT INTO myNavigator.buses (internal_number, Vehicle_id, bus_routes_id, lineName) VALUES
(45, 1, 1, 'Line 1'),
(25, 4, 2, 'Line 2'),
(52, 7, 3, 'Line 3'),
(84, 8, 4, 'Line 4');


-- Insert data into bus_routes
INSERT INTO myNavigator.bus_routes (idbus_routes, route_name, buses_id) VALUES
(1, 'Bus Route 1 Step', 1),
(2, 'Bus Route 2 Steps', 2),
(3, 'Bus Route 3 Steps', 3),
(4, 'Bus Route 4 Steps', 4);


-- Insert data into service_stations
INSERT INTO myNavigator.service_stations (name, Company_id, X_coordinate, Y_coordinate, countries_id) VALUES
('Shell Station Buenos Aires', 1, -34.615852, -58.417758, 1),
('A3C M6, Voložin', 3, 53.899161, 27.566893, 2),
('YPF Station Cordoba', 2, -31.420083, -64.188776, 1),
('A3C M10 Vobrub', 3, 53.915647, 27.532477, 2),
('YPF Station Buenos Aires', 2, -34.607568, -58.384572, 1),
('A3C Station Minsk', 3, 53.870503, 27.586374, 2),
('Shell Station Cordoba', 1, -31.431256, -64.193020, 1);


-- Insert data into service_stations_has_fuels
INSERT INTO myNavigator.service_stations_has_fuels (service_stations_id, service_stations_Company_id, fuels_id) VALUES
(1, 1, 1),
(1, 1, 2),  -- Diesel
(2, 2, 2),  -- Diesel
(2, 2, 1),  -- Gasoline
(3, 3, 1),  -- Gasoline
(3, 3, 2),  -- Diesel
(4, 1, 1),
(4, 1, 2),  -- Diesel
(5, 2, 2),  -- Diesel
(5, 2, 1),  -- Gasoline
(6, 3, 2),  -- Diesel
(6, 3, 1),  -- Gasoline
(7, 1, 1);


-- Insert data into engine_uses_fuels
INSERT INTO myNavigator.engine_uses_fuels (Engine_id, Fuels_id) VALUES
(1, 2), -- Mercedes OM642 uses Diesel
(2, 1), -- Volvo B4204T9 uses Gasoline
(3, 3), -- Toyota Hybrid Synergy Drive uses Natural Gas
(4, 2); -- MAN D1556 LOH uses Diesel


-- Insert data into models_has_engine
INSERT INTO myNavigator.models_has_engine (Models_id, Engine_id) VALUES
(1, 1), -- Mercedes-Benz Sprinter with engine Mercedes OM642
(2, 2), -- Volvo XC90 with engine Volvo B4204T9
(3, 3), -- Toyota Prius with engine Toyota Hybrid Synergy Drive
(4, 4); -- MAN Lion's City with engine MAN D1556 LOH