package alex.falendysh.epam_exhibition.dao.interfaces;

import alex.falendysh.epam_exhibition.dao.Dao;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.model.ExhibitionHall;

import java.util.Optional;

public interface ExhibitionHallDao<T extends ExhibitionHall> extends Dao<T> {

    Optional<T> getByName(String name) throws DaoException;

}
