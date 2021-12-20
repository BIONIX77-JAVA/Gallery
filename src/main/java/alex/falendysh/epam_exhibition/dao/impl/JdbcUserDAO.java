package alex.falendysh.epam_exhibition.dao.impl;

import alex.falendysh.epam_exhibition.configuration.ConnectionPool;
import alex.falendysh.epam_exhibition.configuration.CustomConnectionPool;
import alex.falendysh.epam_exhibition.dao.interfaces.UserDao;
import alex.falendysh.epam_exhibition.exceptions.DBException;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.exceptions.dao.DuplicateValueException;
import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.util.PasswordUtils;
import alex.falendysh.epam_exhibition.util.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static alex.falendysh.epam_exhibition.Constants.DELETE_USER;
import static alex.falendysh.epam_exhibition.util.Constants.ModelConstants.*;
import static alex.falendysh.epam_exhibition.util.Constants.SqlConstants.*;


public class JdbcUserDAO implements UserDao<User> {

    private final Logger logger = LoggerFactory.getLogger(JdbcUserDAO.class);
        private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();



        @Override
    public Optional<User> getById(int id) {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            int userId = -1;
            while (resultSet.next() && id != userId) {
                userId = resultSet.getInt(1);
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error when get user ", e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            String userEmail = "";
            while (resultSet.next() && !email.equals(userEmail)) {
                userEmail = resultSet.getString(2);
                user = getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error when get user by email ", e);
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Error when get all users ", e);
            throw new DaoException(e);
        }
        return users;
    }

    /*
     * Validate user during login process
     */
    public Optional<Object> validateUser(String input, String password) throws DBException {
        Optional<User> user = getByEmail(input);
        user.ifPresent(value -> System.out.println("This user is present in database" + "Hello " + value.getFirstName()));
        boolean b = PasswordUtils.verifyPassword(password, user.get().password, user.get().getSalt());
        if (b) return Optional.of(user);
        return Optional.of(user);
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt(USER_ID);
        String email = resultSet.getString(EMAIL);
        String pass = resultSet.getString(PASSWORD);
        String firstName = resultSet.getString(FIRST_NAME);
        String lastName = resultSet.getString(LAST_NAME);
        String phone = resultSet.getString(PHONE);
        String role = resultSet.getString(ROLE);
        return new User(userId, email, pass, firstName, lastName, phone, Role.valueOf(role));
    }


    @Override
    public Optional<User> insertNewUser(User user) throws SQLException {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_INTO_EXHIBITION_USERS, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, String.valueOf(user.getId()));
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setString(6, user.getPhone());
            ps.setString(7, String.valueOf(user.getRole()));

            System.out.println(ps);

            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed");

        } finally {
            connectionPool.releaseConnection(connection);
        }
            return Optional.of(user);
        }

    @Override
    public boolean save(User user) {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            setUserToStatement(user, statement);
            statement.execute();
            return true;
        } catch (SQLException e) {
            logger.error("Error when save user ", e);
            throw new DuplicateValueException(e);
        }
    }

    private void setUserToStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getEmail());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirstName());
        statement.setString(4, user.getLastName());
        statement.setString(5, user.getPhone());
        statement.setString(6, user.getRole().toString());
    }

    /*
     * Update user with new password
     */
    public void update(User user, String newPassword) throws DBException {
        String salt = PasswordUtils.generateSalt(newPassword.length());
        String hashedPassword = PasswordUtils.hashPassword(newPassword, salt);
        user.setPassword(hashedPassword);
        user.setSalt(salt);
        update(user);
    }

    @Override
    public Optional<User> update(User user) {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            setUserToStatement(user, statement);
            statement.setInt(7, user.getId());
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return Optional.of(user);
            }
        } catch (SQLException e) {
            logger.error("Error when update user ", e);
            throw new DaoException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, id);
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 1) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("Error when delete user ", e);
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public Optional<User> insertNewUserNew(User user) {
        Connection connection = connectionPool.getConnection();
        String sql = "INSERT INTO exhibition_users (id, email, pass, first_name, last_name, phone, role) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getPhone());
            ps.setString(6, String.valueOf(user.getRole()));

            System.out.println(ps);

            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed");
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return Optional.of(user);
    }
}