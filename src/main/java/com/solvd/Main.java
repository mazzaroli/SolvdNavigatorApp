package com.solvd;

import com.solvd.database.dao.mybatis.ConnectionDAOImpl;
import com.solvd.database.dao.mybatis.StationDAOImpl;
import com.solvd.database.model.Connection;
import com.solvd.database.model.Station;
import com.solvd.database.util.algorithm.FloydWarshall;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StationDAOImpl stationDAO = new StationDAOImpl();
        ConnectionDAOImpl connectionDAO = new ConnectionDAOImpl();

        // Obtener todas las estaciones desde la base de datos
        List<Station> stations = stationDAO.getEntities();

        // Obtener todas las conexiones desde la base de datos
        List<Connection> connections = connectionDAO.getEntities();

        // Crear instancia de FloydWarshall
        FloydWarshall floydWarshall = new FloydWarshall(stations, connections);

        // Mostrar todas las estaciones disponibles
        System.out.println("Estaciones disponibles:");
        for (Station station : stations) {
            System.out.println(station.getId() + ". " + station.getName());
        }

        // Leer la estación de inicio desde el usuario
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el número de la estación de inicio: ");
        int startStationId = scanner.nextInt();

        // Leer la estación de destino desde el usuario
        System.out.print("Ingrese el número de la estación de destino: ");
        int endStationId = scanner.nextInt();

        // Ejecutar el algoritmo Floyd-Warshall
        floydWarshall.run(startStationId, endStationId);
    }
}
