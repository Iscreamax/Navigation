package navigation.dao.impl;

import navigation.dao.connector.ConnectionPool;
import navigation.dao.interfaces.IRouteDAO;
import navigation.dao.models.Bus;
import navigation.dao.models.BusStop;
import navigation.dao.models.Route;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO implements IRouteDAO {
    private static final Logger LOGGER = LogManager.getLogger(RouteDAO.class);
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;
    private ResultSet rs;
    private PreparedStatement pr;
    Route route = new Route();
    Bus bus = new Bus();
    BusStop busStop = new BusStop();

    @Override
    public Route getEntityById(int id) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM routes WHERE id=?");
            pr.setInt(1, id);
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                route.setId(rs.getInt("id"));
                route.setBusId(rs.getInt("busses_id"));
                route.setBusStopId(rs.getInt("bus_stops_id"));
            }
        } catch (SQLException e) {
            LOGGER.info("There was some problem to find the Route by id", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
        return route;
    }

    @Override
    public void createEntity(Route entity) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("INSERT INTO routes (buses_id, bus_stops_id) VALUES (?, ?)");
            pr.setInt(1, route.getBusId());
            pr.setInt(2, route.getBusStopId());
            pr.executeUpdate();
            LOGGER.info("The Route was created");
        } catch (SQLException e) {
            LOGGER.info("There was a problem to insert into Routs", e);
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
    public void updateEntity(Route entity) {
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("UPDATE routes SET buses_id=?, bus_stops_id=? ");
            pr.setInt(1, route.getBusId());
            pr.setInt(2, route.getBusStopId());
            pr.executeUpdate();
            LOGGER.info("The Route was updated");
        } catch (SQLException e) {
            LOGGER.info("There was a problem to update the Route", e);
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
            pr = connection.prepareStatement("DELETE FROM routes WHERE id=?");
            pr.setInt(1, id);
            pr.executeUpdate();
            LOGGER.info("The Route was deleted");
        } catch (SQLException e) {
            LOGGER.info("There was problem to delete the Route", e);
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
    public List<Route> showAll() {
        List<Route> routes = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            pr = connection.prepareStatement("SELECT * FROM routes");
            pr.execute();
            rs = pr.getResultSet();
            while (rs.next()) {
                Route route = new Route();
                route.setId(rs.getInt("id"));
                route.setBusId(rs.getInt("buses_id"));
                route.setBusStopId(rs.getInt("bus_stops_id"));
                routes.add(route);
            }
        } catch (SQLException e) {
            LOGGER.info("There was a problem to show a list of routes", e);
        } finally {
            try {
                if (connection != null) connectionPool.releaseConnection(connection);
                if (rs == null) rs.close();
                if (pr == null) pr.close();
            } catch (SQLException e) {
                LOGGER.info("There was a problem in finally block", e);
            }
        }
        return routes;
    }
}
