package alex.falendysh.epam_exhibition.service.interfaces;

import alex.falendysh.epam_exhibition.exceptions.SuchExhibitionHallIsAlreadyExistsException;
import alex.falendysh.epam_exhibition.service.Service;

import java.util.Optional;

public interface ExhibitionHallService<T> extends Service<T> {

    Optional<T> getByName(String name);

    boolean save(T t) throws SuchExhibitionHallIsAlreadyExistsException;

    Optional<T> update(T t);

    boolean delete(int id);
}
