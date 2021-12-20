package alex.falendysh.epam_exhibition.dao.interfaces;

import alex.falendysh.epam_exhibition.dao.Dao;
import alex.falendysh.epam_exhibition.exceptions.DaoException;
import alex.falendysh.epam_exhibition.model.Exhibition;

import java.util.List;
import java.util.Optional;

public interface ExhibitionDao <T extends Exhibition> extends Dao<T> {

    Optional<Exhibition> getByName(String name) throws DaoException;

    List<Exhibition> getAllByExhibitionHallId(int id) throws DaoException;
}
