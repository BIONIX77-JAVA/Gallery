package alex.falendysh.epam_exhibition.service.impl;

import alex.falendysh.epam_exhibition.configuration.ConnectionPool;
import alex.falendysh.epam_exhibition.configuration.CustomConnectionPool;
import alex.falendysh.epam_exhibition.dao.interfaces.UserDao;
import alex.falendysh.epam_exhibition.exceptions.dao.DuplicateValueException;
import alex.falendysh.epam_exhibition.exceptions.user.*;
import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.service.interfaces.UserService;
import alex.falendysh.epam_exhibition.service.validators.ValidatorMatcher;
import alex.falendysh.epam_exhibition.util.PasswordEncryptor;
import alex.falendysh.epam_exhibition.util.dto.UserDto;
import alex.falendysh.epam_exhibition.util.dto.mappers.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static alex.falendysh.epam_exhibition.service.validators.EmailValidator.EMAIL_VALIDATOR;
import static alex.falendysh.epam_exhibition.service.validators.NameValidator.NAME_VALIDATOR;
import static alex.falendysh.epam_exhibition.service.validators.PhoneValidator.PHONE_VALIDATOR;

public class UserServiceImpl implements UserService<UserDto> {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final ConnectionPool connectionPool = CustomConnectionPool.getInstance();

    private final UserDao<User> userDao;
    private final DtoMapper<UserDto, User> dtoMapper;



    public UserServiceImpl(UserDao<User> userDao, DtoMapper<UserDto, User> dtoMapper) {
        this.userDao = userDao;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public Optional<UserDto> getById(int id) {
        if (id <= 0) {
            return Optional.empty();
        }
        UserDto userDto = null;
        if (userDao.getById(id).isPresent()) {
            userDto = dtoMapper.mapToDto(userDao.getById(id).get());
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        if (!EMAIL_VALIDATOR.isValid(email)) {
            return Optional.empty();
        }
        UserDto userDto = null;
        if (userDao.getByEmail(email).isPresent()) {
            userDto = dtoMapper.mapToDto(userDao.getByEmail(email).get());
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(UserDto userDto) throws SuchUserIsAlreadyExistsException, InvalidUserException, NoSuchAlgorithmException {
        System.out.println("I am in saveMethod UserServiceImpl");
        if (userDto.getEmail() == null ||
                userDto.getPassword() == null || userDto.getFirstName() == null
                || userDto.getLastName() == null || userDto.getPhone() == null ||
                userDto.getRole() == null) {
            logger.error("Invalid input data ");
            throw new InvalidUserException();
        }
        User user = getUser(userDto);
        validateUser(user);
        try {
            return userDao.save(user);
        } catch (DuplicateValueException e) {
            logger.error(e.getMessage());
            throw new SuchUserIsAlreadyExistsException();
        }
    }

    private User getUser(UserDto userDto) throws NoSuchAlgorithmException {
        String userPhone = modifyPhoneForDB(userDto.getPhone());
        String passwordToSave = PasswordEncryptor.encrypt(userDto.getPassword());
        userDto.setPhone(userPhone);
        userDto.setPassword(passwordToSave);
        return dtoMapper.mapFromDto(userDto);
    }

    private String modifyPhoneForDB(String phone) {
        return "+" + phone.replaceAll("\\D", "");
    }

    private void validateUser(User user) throws InvalidUserException {
        ValidatorMatcher valide = new ValidatorMatcher();
        if (!EMAIL_VALIDATOR.isValid(user.getEmail())) {
            throw new InvalidEmailException();
        }
        if (!NAME_VALIDATOR.isValid(user.getFirstName())) {
            throw new InvalidUserNameException();
        }
        if (!NAME_VALIDATOR.isValid(user.getLastName())) {
            throw new InvalidUserNameException();
        }
        if (!PHONE_VALIDATOR.isValid(user.getPhone())) {
            throw new InvalidPhoneNumberException();
        }

        // check how its working
        if (!valide.isValide()) {
            System.out.println(valide.getInvalidFields());
        }
    }

    @Override
    public Optional<UserDto> update(UserDto userDto) throws InvalidUserException, NoSuchAlgorithmException {
        if (userDto.getId() <= 0 || userDto.getEmail() == null ||
                userDto.getPassword() == null || userDto.getFirstName() == null
                || userDto.getLastName() == null || userDto.getPhone() == null ||
                userDto.getRole() == null) {
            logger.error("Invalid input data ");
            throw new InvalidUserException();
        }
        User user = getUser(userDto);
        validateUser(user);
        Optional<User> userToUpdate = userDao.getById(user.getId());
        UserDto userDtoToReturn = null;
        if (userToUpdate.isPresent() &&
                isPasswordCorrectForUser(user.getPassword(), userToUpdate.get().getPassword()) &&
                userDao.update(user).isPresent()) {
            userDtoToReturn = dtoMapper.mapToDto(userDao.update(user).get());
        }
        return Optional.ofNullable(userDtoToReturn);
    }

    @Override
    public boolean isPasswordCorrectForUser(String incomePassword, String userPassword) {
        return incomePassword.equals(userPassword);
    }

    @Override
    public boolean delete(int id) {
        if (id <= 0) {
            return false;
        }
        return userDao.delete(id);
    }
}
