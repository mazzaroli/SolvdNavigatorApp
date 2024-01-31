package com.solvd.database.util.algorithm;

import com.solvd.database.model.Connection;
import com.solvd.database.model.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class that implements the Floyd-Warshall algorithm to find the shortest path
 * between stations and calculate distances.
 */
public class FloydWarshall {

    private static final double INF = Double.POSITIVE_INFINITY;

    private double[][] distance;
    private int[][] next;
    private List<Station> stations;
    private List<Connection> connections;

    /**
     * Constructor that initializes the class with the given list of stations and connections.
     *
     * @param stations      List of available stations.
     * @param connections   List of connections between stations.
     */
    public FloydWarshall(List<Station> stations, List<Connection> connections) {
        this.stations = stations;
        this.connections = connections;
        initializeGraph();
    }

    /**
     * Initializes the graph with infinite distances and no connections between stations.
     * Then, assigns distances and connections based on the provided data.
     */
    private void initializeGraph() {
        int numStations = stations.size();
        distance = new double[numStations][numStations];
        next = new int[numStations][numStations];

        for (int i = 0; i < numStations; i++) {
            for (int j = 0; j < numStations; j++) {
                distance[i][j] = INF;
                next[i][j] = -1;
            }
        }

        for (Connection connection : connections) {
            int i = connection.getOriginStationId() - 1;
            int j = connection.getDestinationStationId() - 1;

            distance[i][j] = calculateDistance(stations.get(i), stations.get(j));
            next[i][j] = j;
        }

        for (int i = 0; i < numStations; i++) {
            distance[i][i] = 0;
            next[i][i] = i;
        }
    }

    /**
     * Calculates the distance in kilometers between two stations using the formula for
     * Euclidean distance on a spherical plane.
     *
     * @param station1 First station.
     * @param station2 Second station.
     * @return Distance in kilometers between the two stations.
     */
    private double calculateDistance(Station station1, Station station2) {
        double earthRadius = 6371.0; // Earth radius in kilometers
        double lat1 = Math.toRadians(station1.getCoordinateX());
        double lon1 = Math.toRadians(station1.getCoordinateY());
        double lat2 = Math.toRadians(station2.getCoordinateX());
        double lon2 = Math.toRadians(station2.getCoordinateY());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    /**
     * Executes the Floyd-Warshall algorithm to find the shortest path between
     * two stations and calculates the total distance.
     *
     * @param startId Identifier of the starting station.
     * @param endId   Identifier of the destination station.
     */
    public void run(int startId, int endId) {
        int startIdx = startId - 1;
        int endIdx = endId - 1;

        if (distance[startIdx][endIdx] == INF) {
            System.out.println("No direct connection between the stations.");

            // Find indirect paths
            List<List<Integer>> indirectPaths = findIndirectPaths(startIdx, endIdx);

            if (indirectPaths.isEmpty()) {
                System.out.println("No available paths.");
                return;
            }

            // Sort indirect paths by distance
            indirectPaths.sort(Comparator.comparingDouble(this::calculateTotalDistance));

            // Show only the two shortest paths
            int count = 0;
            for (List<Integer> path : indirectPaths) {
                if (count < 2) {
                    double totalDistance = calculateTotalDistance(path);
                    System.out.println("Path:");
                    for (int stationIdx : path) {
                        System.out.println("- " + stations.get(stationIdx).getName());
                    }
                    System.out.println("Total Distance: " + totalDistance);
                    System.out.println();
                    count++;
                } else {
                    break; // Exit the loop after showing the two shortest paths
                }
            }
        } else {
            // Direct path
            List<Integer> path = reconstructPath(startIdx, endIdx);
            double totalDistance = distance[startIdx][endIdx];

            System.out.println("Shortest path from " + stations.get(startIdx).getName() +
                    " to " + stations.get(endIdx).getName() + ":");
            for (int stationIdx : path) {
                System.out.println("- " + stations.get(stationIdx).getName());
            }

            System.out.println("Total Distance: " + totalDistance);
        }
    }

    /**
     * Finds indirect paths between two stations using recursion.
     *
     * @param startIdx       Index of the starting station.
     * @param endIdx         Index of the destination station.
     * @return List of indirect paths between the stations.
     */
    private List<List<Integer>> findIndirectPaths(int startIdx, int endIdx) {
        List<List<Integer>> indirectPaths = new ArrayList<>();
        boolean[] visited = new boolean[stations.size()];

        findIndirectPathsRecursive(startIdx, endIdx, new ArrayList<>(), visited, indirectPaths);

        return indirectPaths;
    }

    /**
     * Helper method to find indirect paths using recursion.
     *
     * @param currentIdx     Index of the current station.
     * @param endIdx         Index of the destination station.
     * @param currentPath    Current path.
     * @param visited        Visited array.
     * @param indirectPaths  List to store the indirect paths.
     */
    private void findIndirectPathsRecursive(int currentIdx, int endIdx, List<Integer> currentPath,
                                            boolean[] visited, List<List<Integer>> indirectPaths) {
        visited[currentIdx] = true;
        currentPath.add(currentIdx);

        if (currentIdx == endIdx) {
            indirectPaths.add(new ArrayList<>(currentPath));
        } else {
            for (int neighborIdx = 0; neighborIdx < stations.size(); neighborIdx++) {
                if (!visited[neighborIdx] && distance[currentIdx][neighborIdx] != INF) {
                    findIndirectPathsRecursive(neighborIdx, endIdx, currentPath, visited, indirectPaths);
                }
            }
        }

        visited[currentIdx] = false;
        currentPath.remove(currentPath.size() - 1);
    }

    /**
     * Calculates the total distance of a path.
     *
     * @param path List of station indices representing the path.
     * @return Total distance of the path.
     */
    private double calculateTotalDistance(List<Integer> path) {
        double totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int currentIdx = path.get(i);
            int nextIdx = path.get(i + 1);
            totalDistance += distance[currentIdx][nextIdx];
        }
        return totalDistance;
    }

    /**
     * Reconstructs the path between two stations.
     *
     * @param startIdx Index of the starting station.
     * @param endIdx   Index of the destination station.
     * @return List of station indices representing the path.
     */
    private List<Integer> reconstructPath(int startIdx, int endIdx) {
        List<Integer> path = new ArrayList<>();
        int current = startIdx;

        while (current != endIdx) {
            if (current == -1) {
                System.out.println("No path between the stations.");
                return null;
            }
            path.add(current);
            current = next[current][endIdx];
        }

        path.add(endIdx);
        return path;
    }
}