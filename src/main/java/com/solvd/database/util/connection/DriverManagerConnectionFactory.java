package com.solvd.database.util.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Implementation of ConnectionFactory using DriverManager.
 */
public class DriverManagerConnectionFactory implements ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private final String url;
    private final String userName;
    private final String password;

    public DriverManagerConnectionFactory(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public Connection createConnection() {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            LOGGER.error("Error creating connection", e);
            return null;
        }
    }
}