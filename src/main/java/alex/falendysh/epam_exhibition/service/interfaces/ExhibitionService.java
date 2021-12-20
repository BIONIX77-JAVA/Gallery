package alex.falendysh.epam_exhibition.service.interfaces;

import alex.falendysh.epam_exhibition.exceptions.InvalidDataException;
import alex.falendysh.epam_exhibition.exceptions.exhibition.InvalidDateException;
import alex.falendysh.epam_exhibition.exceptions.exhibition.PriceBelowZeroException;
import alex.falendysh.epam_exhibition.service.Service;

import java.util.List;
import java.util.Optional;

public interface ExhibitionService<T> extends Service<T> {

    Optional<T> getByName(String name);

    List<T> getAllByExhibitionHallId(int id);

    boolean save(T t) throws InvalidDateException, PriceBelowZeroException, InvalidDataException;

    Optional<T> update(T t) throws InvalidDateException, PriceBelowZeroException, InvalidDataException;

    boolean delete(int id);
}
