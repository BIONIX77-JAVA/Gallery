package alex.falendysh.epam_exhibition.configuration;

import alex.falendysh.epam_exhibition.util.FileUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomConnectionPool implements ConnectionPool {

    private static CustomConnectionPool customConnectionPoolInstance;
    private final List<Connection> idleConnections;
    private final List<Connection> usedConnections;

    private CustomConnectionPool(List<Connection> connections) {
        this.idleConnections = connections;
        this.usedConnections = new ArrayList<>(connections.size());
    }

    public static ConnectionPool getInstance() {
        if (customConnectionPoolInstance != null) {
            return customConnectionPoolInstance;
        }
        synchronized (CustomConnectionPool.class) {
            try {
                DriverManager.registerDriver(new org.postgresql.Driver());
            } catch (SQLException e) {
                throw new RuntimeException("Failed to register postgresql driver: " + e.getMessage());
            }
            Properties properties = FileUtils.readProperties();
            int initialPoolSize = Integer.parseInt(properties.getProperty
                    ("db.connection-pool.size", "200"));
            List<Connection> connections = new ArrayList<>(initialPoolSize);
            for (int step = 0; step < initialPoolSize; step++) {
                try {
                    connections.add(createConnection(properties));
                } catch (SQLException e) {
                    throw new RuntimeException("Failed to add new connection as: " + e.getMessage());
                }
            }
            customConnectionPoolInstance = new CustomConnectionPool(connections);
            return customConnectionPoolInstance;
        }
    }

    private static Connection createConnection(Properties properties)
            throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password"));
    }

    @Override
    public Connection getConnection() {
        // here please
        Connection connection = idleConnections
                .remove(idleConnections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

//    @Override
//    public Connection getConnection() {
//        Connection connection = idleConnections
//                .remove(idleConnections.size() - 1);
//        usedConnections.add(connection);
//        int increaseIdle = 100;
//        increaseIdle++;
//        if(usedConnections.size()==increaseIdle){
//            idleConnections.add(connection);
//        }
//        return connection;
//    }

    @Override
    public boolean releaseConnection(Connection connection) {
        idleConnections.add(connection);
        return usedConnections.remove(connection);
    }

    public int getSize() {
        return idleConnections.size() + usedConnections.size();
    }
}