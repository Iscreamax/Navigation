package navigation.dao.impl;

import navigation.dao.connector.ConnectionPool;
import navigation.dao.interfaces.IBusDAO;
import navigation.dao.models.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusDAO implements IBusDAO {
    private static final Logger LOGGER = LogManager.getLogger(BusDAO.class);
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pr;
    Bus bus = new Bus();

    @Override
    public Bus getEntityById(int id) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM buses WHERE id=?");
            pr.setInt(1, id);
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                bus.setId(rs.getInt("id"));
                bus.setNumber(rs.getString("number"));
                bus.setMaxCountOfPassengers(rs.getInt("max_count_of_passengers"));
                bus.setStartTime(rs.getString("start_time"));
                bus.setEndTime(rs.getString("end_time"));
            }
            LOGGER.info("Bus was found by id" + bus);
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
        return bus;
    }

    @Override
    public void createEntity(Bus bus) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("INSERT INTO buses (number, max_count_of_passengers, start_time, end_time) VALUES (?, ?, ?, ?)");
            pr.setString(1, bus.getNumber());
            pr.setInt(2, bus.getMaxCountOfPassengers());
            pr.setString(3, bus.getStartTime());
            pr.setString(4, bus.getEndTime());
            pr.executeUpdate();
            LOGGER.info("The Bus was created: " + bus);
        } catch (SQLException e) {
            LOGGER.info("There was a problem to insert into Buses the new entity", e);
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
    public void updateEntity(Bus bus) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("UPDATE buses SET number=?, max_count_of_passengers=?, start_time=?, end_time=? WHERE id=?");
            pr.setString(1, bus.getNumber());
            pr.setInt(2, bus.getMaxCountOfPassengers());
            pr.setString(3, bus.getStartTime());
            pr.setString(4, bus.getEndTime());
            pr.executeUpdate();
            LOGGER.info("The bus was updated");
        } catch (SQLException e) {
            LOGGER.info("There was a problem to update the Bus", e);
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
            pr = connection.prepareStatement("DELETE FROM buses WHERE id=?");
            pr.setInt(1, id);
            pr.executeUpdate();
            LOGGER.info("The Bus was deleted");
        } catch (SQLException e) {
            LOGGER.info("There was a problem to remove the Bus", e);
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
    public List<Bus> showAll() {
        List<Bus> buses = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM buses");
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                Bus bus = new Bus();
                bus.setId(rs.getInt("id"));
                bus.setNumber(rs.getString("number"));
                bus.setMaxCountOfPassengers(rs.getInt("max_count_of_passengers"));
                bus.setStartTime(rs.getString("start_time"));
                bus.setEndTime(rs.getString("end_time"));
                buses.add(bus);
            }
        } catch (SQLException e) {
            LOGGER.info("There was a problem to show a list of buses", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
        return buses;
    }
}

