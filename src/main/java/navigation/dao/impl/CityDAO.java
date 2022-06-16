package navigation.dao.impl;

import navigation.dao.connector.ConnectionPool;
import navigation.dao.interfaces.ICityDAO;
import navigation.dao.models.City;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements ICityDAO {
    private static final Logger LOGGER = LogManager.getLogger(CityDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pr;
    private City city = new City();

    @Override
    public City getEntityById(int id) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM cities WHERE id=?");
            pr.setInt(1, id);
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.info("There was a problem with GET ENTITY BY ID", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
        return city;
    }

    @Override
    public void createEntity(City entity) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("INSERT INTO cities (name) VALUES (?)");
            pr.setString(1, city.getName());
            pr.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("There was a problem to insert into City the new entity", e);
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
    public void updateEntity(City entity) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("UPDATE cities SET name=? WHERE id=?");
            pr.setString(1, city.getName());
            pr.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("There was a problem to update the City", e);
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
            pr = connection.prepareStatement("DELETE FROM cities WHERE id=?");
            pr.setInt(1, id);
            pr.executeUpdate();
        } catch (SQLException e) {
            LOGGER.info("There was a problem to remove the City", e);
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
    public List<City> showAll() {
        List<City> cities = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM buses");
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                City city = new City();
                city.setId(rs.getInt("id"));
                city.setName(rs.getString("name"));
                cities.add(city);
            }
        } catch (SQLException e) {
            LOGGER.info("There was a problem to show a list of cities", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
        return cities;
    }
}
