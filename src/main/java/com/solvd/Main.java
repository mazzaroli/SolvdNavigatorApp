package com.solvd;

import com.solvd.database.dao.mybatis.ConnectionDAOImpl;
import com.solvd.database.dao.mybatis.StationDAOImpl;
import com.solvd.database.dao.mybatis.BusDAOImpl;
import com.solvd.database.model.Connection;
import com.solvd.database.model.Station;
import com.solvd.database.model.Bus;
import com.solvd.database.util.algorithm.FloydWarshall;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StationDAOImpl stationDAO = new StationDAOImpl();
        ConnectionDAOImpl connectionDAO = new ConnectionDAOImpl();
        BusDAOImpl busDAO = new BusDAOImpl(); // Assuming you have a BusDAOImpl

        // Get all stations from the database
        List<Station> stations = stationDAO.getEntities();

        // Get all connections from the database
        List<Connection> connections = connectionDAO.getEntities();

        // Get all buses from the database
        List<Bus> buses = busDAO.getEntities();

        // Create an instance of FloydWarshall
        FloydWarshall floydWarshall = new FloydWarshall(stations, connections, buses);

        // Display all available stations
        System.out.println("Available stations:");
        for (Station station : stations) {
            System.out.println(station.getId() + ". " + station.getName());
        }

        // Read the starting station from the user
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the number of the starting station: ");
            int startStationId = scanner.nextInt();

            // Read the destination station from the user
            System.out.print("Enter the number of the destination station: ");
            int endStationId = scanner.nextInt();

            // Execute the Floyd-Warshall algorithm
            floydWarshall.run(startStationId, endStationId);
        }
    }
}