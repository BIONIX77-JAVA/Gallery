package alex.falendysh.epam_exhibition.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    Optional<T> getById(int id);

    List<T> getAll();

}
