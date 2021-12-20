package alex.falendysh.epam_exhibition.dao.impl;

import alex.falendysh.epam_exhibition.configuration.ConnectionPool;
import alex.falendysh.epam_exhibition.configuration.CustomConnectionPool;
import alex.falendysh.epam_exhibition.dao.interfaces.TicketDao;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.exceptions.ticket.TicketsCountException;
import alex.falendysh.epam_exhibition.model.Exhibition;
import alex.falendysh.epam_exhibition.model.ExhibitionHall;
import alex.falendysh.epam_exhibition.model.Ticket;
import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.util.enums.Role;
import alex.falendysh.epam_exhibition.util.enums.TicketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static alex.falendysh.epam_exhibition.util.Constants.ModelConstants.*;
import static alex.falendysh.epam_exhibition.util.Constants.SqlConstants.*;


public class JdbcTicketDAO
        implements TicketDao<Ticket> {
    private Logger logger = LoggerFactory.getLogger(JdbcTicketDAO.class);
    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();

    @Override
    public Optional<Ticket> getById(int id) {
        Ticket ticket = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TICKET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int ticketId = -1;
            while (resultSet.next() && id != ticketId) {
                ticketId = resultSet.getInt(1);
                ticket = getTicketFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error when get ticket ", e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(ticket);
    }

    private Ticket getTicketFromResultSet(ResultSet resultSet) throws SQLException {
        int ticketId = resultSet.getInt(TICKET_ID);
        LocalDate visitDate = resultSet.getDate(VISIT_DATE).toLocalDate();
        LocalDate orderDate = resultSet.getDate(ORDER_DATE).toLocalDate();
        TicketType ticketType = TicketType.valueOf(resultSet.getString(TICKET_TYPE));
        int ticketPrice = resultSet.getInt(TICKET_PRICE);
        boolean isPaid = resultSet.getBoolean(IS_PAID);

        int userId = resultSet.getInt(USER_ID);
        String email = resultSet.getString(EMAIL);
        String pass = resultSet.getString(PASSWORD);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String phone = resultSet.getString(PHONE);
        Role role = Role.valueOf(resultSet.getString(ROLE));
        User user = new User(userId, email, pass, firstName, lastName, phone, role);

        int exhibitionId = resultSet.getInt(EXHIBITION_ID);
        String name = resultSet.getString(EXHIBITION_NAME);
        String description = resultSet.getString(DESCRIPTION);
        LocalDate beginDate = resultSet.getDate(BEGIN_DATE).toLocalDate();
        LocalDate endDate = resultSet.getDate(END_DATE).toLocalDate();
        int fullTicketPrice = resultSet.getInt(FULL_TICKET_PRICE);

        int exhibitionHallId = resultSet.getInt(EXHIBITION_HALL_ID);
        String exhibitionHallName = resultSet.getString(EXHIBITION_HALL_NAME);

        ExhibitionHall exhibitionHall = new ExhibitionHall(
                exhibitionHallId, exhibitionHallName);
        Exhibition exhibition = new Exhibition(exhibitionId, name, description,
                beginDate, endDate, fullTicketPrice, exhibitionHall);

        return new Ticket(ticketId, visitDate, orderDate,
                ticketType, ticketPrice, isPaid, user, exhibition);
    }

    @Override
    public List<Ticket> getAllTicketsByUserId(int id) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USER_TICKETS_BY_USER_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int userId;
            while (resultSet.next()) {
                userId = resultSet.getInt(USER_ID);
                if (userId == id) {
                    tickets.add(getTicketFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error("Error when get user tickets ", e);
            throw new DaoException(e);
        }
        return tickets;
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_TICKETS);
            while (resultSet.next()) {
                tickets.add(getTicketFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error when get all tickets ", e);
            throw new DaoException(e);
        }
        return tickets;
    }

    @Override
    public int countTicketsForTheDate(LocalDate date) {
        int ticketsOrdered = -1;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_TICKETS_FOR_VISIT_DATE)) {
            statement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ticketsOrdered = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error when calculate tickets for the date ", e);
            throw new TicketsCountException();
        }
        return ticketsOrdered;
    }

    @Override
    public boolean save(Ticket ticket) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_TICKET)) {
            setTicketFromResultSet(ticket, statement);
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Error when save ticket ", e);
            throw new DaoException();
        }
    }

    private void setTicketFromResultSet(Ticket ticket, PreparedStatement statement) throws SQLException {
        statement.setDate(1, Date.valueOf(ticket.getVisitDate()));
        statement.setDate(2, Date.valueOf(ticket.getOrderDate()));
        statement.setString(3, ticket.getTicketType().toString());
        statement.setInt(4, ticket.getTicketPrice());
        statement.setBoolean(5, ticket.isPaid());
        statement.setInt(6, ticket.getUser().getId());
        statement.setInt(7, ticket.getExhibition().getId());
    }

    @Override
    public boolean saveListOfTickets(List<Ticket> tickets) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_TICKET);
            for (Ticket ticket : tickets) {
                setTicketFromResultSet(ticket, statement);
                statement.execute();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            logger.error("Error when save list of tickets ", e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                logger.error("Roll back exception");
                ex.printStackTrace();
            }
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error("Error while set autocommit true");
                e.printStackTrace();
            }
        }
    }

    public Optional<Ticket> update(Ticket ticket) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TICKET)) {
            setTicketFromResultSet(ticket, statement);
            statement.setInt(8, ticket.getId());
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return Optional.of(ticket);
            }
        } catch (SQLException e) {
            logger.error("Error when update ticket ", e);
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean updateListOfTickets(List<Ticket> tickets) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(UPDATE_TICKET);
            int updatedRows = 0;
            for (Ticket ticket : tickets) {
                setTicketFromResultSet(ticket, statement);
                statement.setInt(8, ticket.getId());
                updatedRows += statement.executeUpdate();
            }
            if (updatedRows == tickets.size()) {
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error when update list of tickets ", e);
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                logger.error("Roll back exception");
                ex.printStackTrace();
            }
            throw new DaoException();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TICKET)) {
            statement.setInt(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error when delete ticket ", e);
            throw new DaoException(e);
        }
        return false;
    }
}