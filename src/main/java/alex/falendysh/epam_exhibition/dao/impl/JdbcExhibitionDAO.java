package alex.falendysh.epam_exhibition.dao.impl;

import alex.falendysh.epam_exhibition.configuration.ConnectionPool;
import alex.falendysh.epam_exhibition.configuration.CustomConnectionPool;
import alex.falendysh.epam_exhibition.dao.interfaces.ExhibitionDao;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.exceptions.dao.DuplicateValueException;
import alex.falendysh.epam_exhibition.model.Exhibition;
import alex.falendysh.epam_exhibition.model.ExhibitionHall;
import alex.falendysh.epam_exhibition.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static alex.falendysh.epam_exhibition.util.Constants.ModelConstants.*;
import static alex.falendysh.epam_exhibition.util.Constants.SqlConstants.*;


public class JdbcExhibitionDAO implements ExhibitionDao<Exhibition> {
    private final Logger logger = LoggerFactory.getLogger(JdbcExhibitionDAO.class);
    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();


    @Override
    public Optional<Exhibition> getById(int id) {
        Exhibition exhibition = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITION_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int exhibitionId = -1;
            while (resultSet.next() && id != exhibitionId) {
                exhibitionId = resultSet.getInt(1);
                exhibition = getExhibitionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error when get exhibition ", e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(exhibition);
    }

    private Exhibition getExhibitionFromResultSet(ResultSet resultSet) throws SQLException {
        int exhibitionId = resultSet.getInt(EXHIBITION_ID);
        String name = resultSet.getString(EXHIBITION_NAME);
        String description = resultSet.getString(DESCRIPTION);
        LocalDate beginDate = resultSet.getDate(BEGIN_DATE).toLocalDate();
        LocalDate endDate = resultSet.getDate(END_DATE).toLocalDate();
        int fullTicketPrice = resultSet.getInt(FULL_TICKET_PRICE);
        int exhibitionHallId = resultSet.getInt(EXHIBITION_HALL_ID);
        String exhibitionHallName = resultSet.getString(EXHIBITION_HALL_NAME);
        int exhibitionHallCapacity = resultSet.getInt(CAPACITY_OF_PAINTINGS_ON_THE_WALL);
        int numberOfVisitorsPerDay = resultSet.getInt(ALLOWABLE_NUMBER_OF_VISITORS_PER_DAY);
        ExhibitionHall exhibitionHall = new ExhibitionHall(
                exhibitionHallId, exhibitionHallName,exhibitionHallCapacity, numberOfVisitorsPerDay);
        return new Exhibition(exhibitionId, name, description, beginDate, endDate,
                fullTicketPrice, exhibitionHall);
    }

    @Override
    public Optional<Exhibition> getByName(String name) {
        Exhibition exhibition = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITION_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            String exhibitionName = "";
            while (resultSet.next() && !name.equals(exhibitionName)) {
                exhibitionName = resultSet.getString(2);
                exhibition = getExhibitionFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error when get exhibition ", e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(exhibition);
    }

    @Override
    public List<Exhibition> getAllByExhibitionHallId(int id) {
        List<Exhibition> exhibitions = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EXHIBITIONS_BY_HALL_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int hallId = -1;
            while (resultSet.next()) {
                hallId = resultSet.getInt(7);
                if (hallId == id) {
                    exhibitions.add(getExhibitionFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error when get exhibitions for hall ", e);
            throw new DaoException(e);
        }
        return exhibitions;
    }

    @Override
    public List<Exhibition> getAll() {
        List<Exhibition> exhibitions = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_EXHIBITIONS);
            while (resultSet.next()) {
                exhibitions.add(getExhibitionFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error when get all exhibitions ", e);
            throw new DaoException(e);
        }
        return exhibitions;
    }

    @Override
    public boolean save(Exhibition exhibition) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EXHIBITION)) {
            setExhibitionFromResultSet(exhibition, statement);
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Error when save exhibition ", e);
            throw new DuplicateValueException(e);
        }
    }


    private void setExhibitionFromResultSet(Exhibition exhibition,
                                            PreparedStatement statement) throws SQLException {
        statement.setString(1, exhibition.getName());
        statement.setString(2, exhibition.getDescription());
        statement.setDate(3, Date.valueOf(exhibition.getBeginDate()));
        statement.setDate(4, Date.valueOf(exhibition.getEndDate()));
        statement.setInt(5, exhibition.getFullTicketPrice());
        statement.setInt(6, exhibition.getExhibitionHall().getId());
    }

    public Optional<Exhibition> update(Exhibition exhibition) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EXHIBITION)) {
            setExhibitionFromResultSet(exhibition, statement);
            statement.setInt(7, exhibition.getId());
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return Optional.of(exhibition);
            }
        } catch (SQLException e) {
            logger.error("Error when update exhibition ", e);
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXHIBITION)) {
            statement.setInt(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error when delete exhibition ", e);
            throw new DaoException(e);
        }
        return false;
    }
}
