package navigation.dao.impl;

import navigation.dao.connector.ConnectionPool;
import navigation.dao.interfaces.IBusStopDAO;
import navigation.dao.interfaces.ICityDAO;
import navigation.dao.models.BusStop;
import navigation.dao.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusStopDAO implements IBusStopDAO {
    private static final Logger LOGGER = LogManager.getLogger(BusStopDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pr;
    private BusStop busStop = new BusStop();
    private City city = new City();

    @Override
    public BusStop getEntityById(int id) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM bus_stops WHERE id=?");
            pr.setInt(1, id);
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                busStop.setId(rs.getInt("id"));
                busStop.setName(rs.getString("name"));
                busStop.setLatitude(rs.getFloat("latitude"));
                busStop.setLongitude(rs.getFloat("longitude"));
                city.setId(rs.getInt("cities_id"));
                ICityDAO cityDAO = new CityDAO();
                city.setName(cityDAO.getEntityById(rs.getInt("cities_id")).getName());
                busStop.setCity(city);
            }
        } catch (SQLException e) {
            LOGGER.info("There was some problem to find Bus stop by id", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
        return busStop;
    }

    @Override
    public void createEntity(BusStop busStop) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("INSERT INTO bus_stops (name, latitude, longitude, cities_id) VALUES (?, ?, ?, ?)");
            pr.setString(1, busStop.getName());
            pr.setFloat(2, busStop.getLatitude());
            pr.setFloat(3, busStop.getLongitude());
            pr.setInt(4, busStop.getCity().getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("There was a problem to insert into Bus stop", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
    }

    @Override
    public void updateEntity(BusStop entity) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("UPDATE bus_stops SET name=?, latitude=?, longitude=?, cities_id=? WHERE id=?");
            pr.setString(1, busStop.getName());
            pr.setFloat(2, busStop.getLatitude());
            pr.setFloat(3, busStop.getLongitude());
            pr.setInt(4, busStop.getCity().getId());
            pr.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("There was a problem to update the Bus stop", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
    }


    @Override
    public void removeEntity(int id) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("DELETE FROM bus_stops WHERE id=?");
            pr.setInt(1, id);
            pr.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("There was problem to delete the Bus stop", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
    }

    @Override
    public List<BusStop> showAll() {
        List<BusStop> busStops = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM bus_stops");
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                BusStop busStop = new BusStop();
                busStop.setId(rs.getInt("id"));
                busStop.setName(rs.getString("name"));
                busStop.setLatitude(rs.getFloat("latitude"));
                busStop.setLongitude(rs.getFloat("longitude"));
                ICityDAO cityDAO = new CityDAO();
                City city = cityDAO.getEntityById(rs.getInt("cities_id"));
                busStop.setCity(city);
                busStops.add(busStop);
            }
        } catch (SQLException e) {
            LOGGER.info("There was a problem to show a list of Bus stops", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
        return busStops;
    }
}
