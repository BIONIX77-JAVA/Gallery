package alex.falendysh.epam_exhibition.service.interfaces;

import alex.falendysh.epam_exhibition.exceptions.user.InvalidUserException;
import alex.falendysh.epam_exhibition.exceptions.user.SuchUserIsAlreadyExistsException;
import alex.falendysh.epam_exhibition.service.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UserService<T> extends Service<T> {

    Optional<T> getByEmail(String email);

    boolean save(T t) throws SuchUserIsAlreadyExistsException, InvalidUserException, NoSuchAlgorithmException;

    Optional<T> update(T t) throws InvalidUserException, NoSuchAlgorithmException;

    boolean isPasswordCorrectForUser(String incomePassword, String userPassword);

    boolean delete(int id);
}