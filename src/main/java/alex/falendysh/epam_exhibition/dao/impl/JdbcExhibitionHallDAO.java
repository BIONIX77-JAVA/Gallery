package alex.falendysh.epam_exhibition.dao.impl;

import alex.falendysh.epam_exhibition.configuration.ConnectionPool;
import alex.falendysh.epam_exhibition.configuration.CustomConnectionPool;
import alex.falendysh.epam_exhibition.dao.interfaces.ExhibitionHallDao;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.exceptions.dao.DuplicateValueException;
import alex.falendysh.epam_exhibition.model.ExhibitionHall;
import alex.falendysh.epam_exhibition.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static alex.falendysh.epam_exhibition.util.Constants.ModelConstants.*;
import static alex.falendysh.epam_exhibition.util.Constants.SqlConstants.*;


public class JdbcExhibitionHallDAO implements ExhibitionHallDao<ExhibitionHall> {
    private Logger logger = (Logger) LoggerFactory.getLogger(JdbcExhibitionHallDAO.class);
    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();


    @Override
    public Optional<ExhibitionHall> getById(int id) {
        ExhibitionHall exhibitionHall = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITION_HALL_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int exhibitionHallId = -1;
            while (resultSet.next() && id != exhibitionHallId) {
                exhibitionHallId = resultSet.getInt(EXHIBITION_HALL_ID);
                exhibitionHall = getExhibitionHallFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error when get exhibition hall ", e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(exhibitionHall);
    }

    private ExhibitionHall getExhibitionHallFromResultSet(ResultSet resultSet) throws SQLException {
        int exhibitionHallId = resultSet.getInt(EXHIBITION_HALL_ID);
        String name = resultSet.getString(EXHIBITION_HALL_NAME);
        int capacity = resultSet.getInt(CAPACITY_OF_PAINTINGS_ON_THE_WALL);
        int numberOfVisitorsPerDay = resultSet.getInt(ALLOWABLE_NUMBER_OF_VISITORS_PER_DAY);
        return new ExhibitionHall(exhibitionHallId,  name, capacity, numberOfVisitorsPerDay);
    }

    @Override
    public Optional<ExhibitionHall> getByName(String name) {
        ExhibitionHall exhibitionHall = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITION_HALL_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            String exhibitionHallName = "";
            while (resultSet.next() && !name.equals(exhibitionHallName)) {
                exhibitionHallName = resultSet.getString(EXHIBITION_HALL_NAME);
                exhibitionHall = getExhibitionHallFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error when get exhibition hall ", e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(exhibitionHall);
    }

    @Override
    public List<ExhibitionHall> getAll() {
        List<ExhibitionHall> exhibitionHalls = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_EXHIBITION_HALLS);
            while (resultSet.next()) {
                exhibitionHalls.add(getExhibitionHallFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error when get all exhibition halls ", e);
            throw new DaoException(e);
        }
        return exhibitionHalls;
    }

    @Override
    public boolean save(ExhibitionHall exhibitionHall) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EXHIBITION_HALL)) {
            statement.setString(1, exhibitionHall.getName());
            statement.setInt(2, exhibitionHall.getAllowableNumberOfVisitorsPerDay());
            statement.setInt(3,exhibitionHall.getCapacity());
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Error when save exhibition hall ", e);
            throw new DuplicateValueException(e);
        }
    }

    public Optional<ExhibitionHall> update(ExhibitionHall exhibitionHall) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EXHIBITION_HALL)) {
            statement.setString(1, exhibitionHall.getName());
            statement.setInt(2, exhibitionHall.getId());
            statement.setInt(3, exhibitionHall.getAllowableNumberOfVisitorsPerDay());
            statement.setInt(4,exhibitionHall.getCapacity());
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return Optional.of(exhibitionHall);
            }
        } catch (SQLException e) {
            logger.error("Error when update exhibition hall ", e);
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXHIBITION_HALL)) {
            statement.setInt(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error when delete exhibition hall ", e);
            throw new DaoException(e);
        }
        return false;
    }
}
