package com.solvd.database.util.algorithm;

import com.solvd.database.model.Bus;
import com.solvd.database.model.Connection;
import com.solvd.database.model.Station;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class that implements the Floyd-Warshall algorithm to find the shortest path
 * between stations and calculate distances.
 */
public class FloydWarshall {

    private static final double INF = Double.POSITIVE_INFINITY;

    private double[][] distance;
    private int[][] next;
    private final List<Station> stations;
    private final List<Connection> connections;
    private final List<Bus> buses;

    /**
     * Constructor that initializes the class with the given list of stations, connections, and buses.
     *
     * @param stations      List of available stations.
     * @param connections   List of connections between stations.
     * @param buses         List of buses.
     */
    public FloydWarshall(List<Station> stations, List<Connection> connections, List<Bus> buses) {
        this.stations = stations;
        this.connections = connections;
        this.buses = buses; // Initialize the buses field
        initializeGraph();
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

        Scanner scanner = new Scanner(System.in);
        System.out.print("Traveling by car or bus? (car/bus): ");
        String travelMode = scanner.next();

        if ("car".equalsIgnoreCase(travelMode)) {
            runForCar(startIdx, endIdx);
        } else if ("bus".equalsIgnoreCase(travelMode)) {
            runForBus(startIdx, endIdx);
        } else {
            System.out.println("Invalid option.");
        }
    }

    /**
     * Runs the algorithm for car travel and prints the results.
     *
     * @param startIdx Index of the starting station.
     * @param endIdx   Index of the destination station.
     */
    private void runForCar(int startIdx, int endIdx) {
        if (distance[startIdx][endIdx] == INF) {
            System.out.println("No direct connection between the stations.");

            List<List<Integer>> indirectPaths = findIndirectPaths(startIdx, endIdx);

            if (indirectPaths.isEmpty()) {
                System.out.println("No available routes.");
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
                    System.out.println("Total distance: " + totalDistance);
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
            assert path != null;
            for (int stationIdx : path) {
                System.out.println("- " + stations.get(stationIdx).getName());
            }

            System.out.println("Total distance: " + totalDistance);
        }
    }

    /**
     * Runs the algorithm for bus travel and prints the results.
     *
     * @param startIdx Index of the starting station.
     * @param endIdx   Index of the destination station.
     */
    public void runForBus(int startIdx, int endIdx) {
        // Check for a direct bus connection
        double busDistance = distance[startIdx][endIdx];
        if (busDistance != INF) {
            System.out.println("You can take a direct bus to reach your destination.");
            System.out.println("Total bus distance: " + busDistance);
            printBusesForConnection(startIdx, endIdx);
        } else {
            // Find indirect routes
            List<List<Integer>> indirectPaths = findIndirectPaths(startIdx, endIdx);

            if (indirectPaths.isEmpty()) {
                System.out.println("No available bus routes.");
                return;
            }

            // Sort indirect routes by distance
            indirectPaths.sort(Comparator.comparingDouble(this::calculateTotalDistance));

            // Show only the shortest bus route
            List<Integer> shortestPath = indirectPaths.get(0);
            double totalDistance = calculateTotalDistance(shortestPath);

            System.out.println("Shortest bus route:");
            for (int stationIdx : shortestPath) {
                System.out.println("- " + stations.get(stationIdx).getName());
            }
            System.out.println("Total bus distance: " + totalDistance);
            printBusesForPath(shortestPath);
        }
    }

    /**
     * Initializes the graph with infinite distances and no connections between stations.
     * Then, assigns distances and connections based on the provided data.
     */
    private void initializeGraph() {
        // Get the number of stations
        int numStations = stations.size();

        // Initialize distance matrix with infinite distances and next matrix with -1 values
        distance = new double[numStations][numStations];
        next = new int[numStations][numStations];

        // Initialize matrices with default values
        for (int i = 0; i < numStations; i++) {
            for (int j = 0; j < numStations; j++) {
                // Set initial distance to infinity
                distance[i][j] = INF;
                // Set initial next node to -1
                next[i][j] = -1;
            }
        }

        // Assign distances and next nodes based on provided connections
        for (Connection connection : connections) {
            int i = connection.getOriginStationId() - 1;
            int j = connection.getDestinationStationId() - 1;

            // Calculate and set the distance between stations using Euclidean distance
            distance[i][j] = calculateDistance(stations.get(i), stations.get(j));
            // Set the next node for the current station to the destination station
            next[i][j] = j;
        }

        // Set distance from a station to itself to 0 and next node to itself
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
        // Earth radius in kilometers
        double earthRadius = 6371.0;

        // Convert latitude and longitude from degrees to radians
        double lat1 = Math.toRadians(station1.getCoordinateX());
        double lon1 = Math.toRadians(station1.getCoordinateY());
        double lat2 = Math.toRadians(station2.getCoordinateX());
        double lon2 = Math.toRadians(station2.getCoordinateY());

        // Calculate differences in latitude and longitude
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        // Haversine formula for calculating distance on a sphere
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        // Angular distance in radians
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate actual distance by multiplying with Earth's radius
        return earthRadius * c;
    }

    /**
     * Gets the bus ID based on the connection ID.
     *
     * @param busId ID of the connection.
     * @return Bus ID or -1 if not found.
     */
    private Bus getBusById(int busId) {
        // Iterate through the list of buses
        for (Bus bus : buses) {
            // Check if the current bus has the matching ID
            if (bus.getId() == busId) {
                // Return the Bus object with the specified ID
                return bus;
            }
        }
        // Return null if no matching Bus object is found
        return null;
    }



    /**
     * Prints the bus information for a direct connection.
     *
     * @param startIdx Index of the starting station.
     * @param endIdx   Index of the destination station.
     */
    private void printBusesForConnection(int startIdx, int endIdx) {
        // Find the direct bus connection between the specified stations
        Connection busConnection = findConnectionBetweenStations(startIdx, endIdx);

        // Check if a direct bus connection is found
        if (busConnection != null) {
            // Get the bus ID associated with the connection
            int busId = getBusIdByConnectionId(busConnection.getId());

            // Get the Bus object based on the bus ID
            Bus bus = getBusById(busId);

            // Check if the Bus object is found
            if (bus != null) {
                // Print the name of the bus line
                System.out.println("Bus name: " + bus.getLineName());
            } else {
                // Print a message if Bus information is not found
                System.out.println("Bus information not found.");
            }
        } else {
            // Print a message if no direct bus connection is found
            System.out.println("No direct bus connection.");
        }
    }

    /**
     * Prints the bus information for a bus route.
     *
     * @param path List of station indices representing the bus route.
     */
    private void printBusesForPath(List<Integer> path) {
        System.out.println("Buses for the route:");

        // Iterate through each segment of the bus route
        for (int i = 0; i < path.size() - 1; i++) {
            // Get the indices of the current and next stations in the route
            int currentStationIdx = path.get(i);
            int nextStationIdx = path.get(i + 1);

            // Find the bus connection between the current and next stations
            Connection busConnection = findConnectionBetweenStations(currentStationIdx, nextStationIdx);

            // Check if a direct bus connection is found
            if (busConnection != null) {
                // Get the bus ID associated with the connection
                int busId = getBusIdByConnectionId(busConnection.getId());

                // Get the Bus object based on the bus ID
                Bus bus = getBusById(busId);

                // Check if the Bus object is found
                if (bus != null) {
                    // Print the name of the bus line for the current segment
                    System.out.println("Bus name: " + bus.getLineName());
                } else {
                    // Print a message if Bus information is not found for the current segment
                    System.out.println("Bus information not found.");
                }
            } else {
                // Print a message if no direct bus connection is found for the current segment
                System.out.println("No direct bus connection between the stations.");
            }
        }
    }

    /**
     * Gets the bus ID based on the connection ID.
     *
     * @param connectionId ID of the connection.
     * @return Bus ID or -1 if not found.
     */
    private int getBusIdByConnectionId(int connectionId) {
        // Iterate through the list of buses
        for (Bus bus : buses) {
            // Check if the bus ID matches the given connection ID
            if (bus.getId() == connectionId) {
                // Return the bus ID if a match is found
                return bus.getId();
            }
        }

        // Return -1 if no matching bus ID is found
        return -1;
    }

    /**
     * Finds a connection between two stations.
     *
     * @param startIdx Index of the starting station.
     * @param endIdx   Index of the destination station.
     * @return Connection object or null if not found.
     */
    private Connection findConnectionBetweenStations(int startIdx, int endIdx) {
        // Iterate through the list of connections
        for (Connection connection : connections) {
            // Check if the connection's origin and destination station indices match the provided indices
            if (connection.getOriginStationId() - 1 == startIdx && connection.getDestinationStationId() - 1 == endIdx) {
                // Return the connection if a match is found
                return connection;
            }
        }

        // Return null if no matching connection is found
        return null;
    }

    /**
     * Finds indirect paths between two stations using recursion.
     *
     * @param startIdx Index of the starting station.
     * @param endIdx   Index of the destination station.
     * @return List of indirect paths between the stations.
     */
    private List<List<Integer>> findIndirectPaths(int startIdx, int endIdx) {
        // Create a list to store indirect paths
        List<List<Integer>> indirectPaths = new ArrayList<>();

        // Create a boolean array to keep track of visited stations
        boolean[] visited = new boolean[stations.size()];

        // Call the recursive method to find indirect paths
        findIndirectPathsRecursive(startIdx, endIdx, new ArrayList<>(), visited, indirectPaths);

        // Return the list of indirect paths
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
        // Mark the current station as visited and add it to the current path
        visited[currentIdx] = true;
        currentPath.add(currentIdx);

        // If the destination station is reached, add the current path to the list of indirect paths
        if (currentIdx == endIdx) {
            indirectPaths.add(new ArrayList<>(currentPath));
        } else {
            // Explore neighbors for possible indirect paths
            for (int neighborIdx = 0; neighborIdx < stations.size(); neighborIdx++) {
                // Check if the neighbor is not visited and there is a connection to it
                if (!visited[neighborIdx] && distance[currentIdx][neighborIdx] != INF) {
                    // Recursively explore the path to the neighbor
                    findIndirectPathsRecursive(neighborIdx, endIdx, currentPath, visited, indirectPaths);
                }
            }
        }

        // Backtrack: mark the current station as not visited and remove it from the current path
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
        // Initialize the total distance
        double totalDistance = 0;

        // Iterate through the path to sum up the distances between consecutive stations
        for (int i = 0; i < path.size() - 1; i++) {
            int currentIdx = path.get(i);
            int nextIdx = path.get(i + 1);

            // Add the distance between the current station and the next station to the total distance
            totalDistance += distance[currentIdx][nextIdx];
        }

        // Return the calculated total distance for the given path
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
        // Initialize a list to store the reconstructed path
        List<Integer> path = new ArrayList<>();

        // Set the current station index to the starting index
        int current = startIdx;

        // Iterate until reaching the destination station
        while (current != endIdx) {
            // Check for invalid or disconnected path
            if (current == -1) {
                System.out.println("No path between the stations.");
                return null;
            }

            // Add the current station index to the path
            path.add(current);

            // Move to the next station index using the next matrix
            current = next[current][endIdx];
        }

        // Add the destination station index to the path
        path.add(endIdx);

        // Return the reconstructed path
        return path;
    }
}