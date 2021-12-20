package alex.falendysh.epam_exhibition.dao.interfaces;

import alex.falendysh.epam_exhibition.dao.Dao;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao<T extends User> extends Dao<T> {

    Optional<T> getByEmail(String email) throws DaoException;

    Optional<T> insertNewUser(User user) throws SQLException;
    Optional<T> insertNewUserNew(User user);
}