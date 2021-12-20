package alex.falendysh.epam_exhibition.dao;

import alex.falendysh.epam_exhibition.exceptions.DaoException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    boolean save(T t) throws DaoException;

    boolean delete(int id) throws DaoException;

    Optional<T> update(T t) throws DaoException;
}

