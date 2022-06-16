package navigation.dao.connector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = null;
    private static final int INITIAL_POOL_SIZE = 5;
    private static final List<Connection> freeConnections = new ArrayList<>();
    private static final List<Connection> usedConnections = new ArrayList<>();

    private ConnectionPool() {
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
            create();
        }
        return instance;
    }

    public static void create() {
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/db.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("There was a failure to find db.property file", e);
        } catch (IOException e) {
            LOGGER.error("Error in creating of the connection", e);
        }

        String url = properties.getProperty("db.url");
        String userName = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            freeConnections.add(createConnection(url, userName, password));
        }
    }

    public synchronized Connection getConnection() {
        Connection connection = freeConnections.remove(freeConnections.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            if (usedConnections.remove(connection)) {
                freeConnections.add(connection);
            } else {
                throw new SQLException("There was a problem with releasing connection.");
            }
        }
    }

    private static Connection createConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public int getSize() {
        return freeConnections.size() + usedConnections.size();
    }

}
