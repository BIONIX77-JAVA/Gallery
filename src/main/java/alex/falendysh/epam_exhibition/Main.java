package alex.falendysh.epam_exhibition;

import alex.falendysh.epam_exhibition.configuration.DBCPDataSource;
import alex.falendysh.epam_exhibition.dao.impl.JdbcUserDAO;
import alex.falendysh.epam_exhibition.dao.interfaces.UserDao;
import alex.falendysh.epam_exhibition.exceptions.user.InvalidUserException;
import alex.falendysh.epam_exhibition.exceptions.user.SuchUserIsAlreadyExistsException;
import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.service.impl.UserServiceImpl;
import alex.falendysh.epam_exhibition.service.interfaces.UserService;
import alex.falendysh.epam_exhibition.util.dto.UserDto;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static alex.falendysh.epam_exhibition.util.enums.Role.USER;

public class Main {

    public static void main(String[] args) throws SQLException {


        UserDao<User> userDao = new JdbcUserDAO();
        System.out.println("In main");
        // 1. createUser
//        userDao.insertNewUser(new User("df@gmail.com","43224","fopo","bobo","098668909767", USER));

        // 2. updateUser
//        user.updateUser(6789876567L,new User(35423L,"UPDATINGUSER@gmail.com", null,"223sfR@gmail.com", "Blo", "Ko",
//            Collections.singleton(Role.USER), UserStatus.ACTIVE, false, LocalDateTime.now()));

        // 3. deleteUser
//        userDao.delete(5);

        // 4. findUserById
//        Optional<User> s = userDao.getByEmail("detikmua@gmail.com");
//        System.out.println(s);

        // 5. findByUsername false
//        User r = user.findByUsername("BOTOLOMER@gmail.com");
//        System.out.println(r);

        // 6. selectAllUsers
//        List<User> all = user.selectAllUsers();
//        for (User user1 : all) {
//            System.out.println(user1);
//        }


//        User r = user.findByUsername("foli@g.mail");
//        System.out.println(r);
//        User admin = user.createUser(new User(777L, "bionix", "061277","detikmua@gmail.com\"", "Al", "Fa",
//    Collections.singleton(Role.ADMIN), UserStatus.ACTIVE, false, LocalDateTime.now()));
//        System.out.println(admin);


//        User newID = user.updateUser(36L,new User(1L, "bionix", "061277","detikmua@gmail.com", "Al", "Fa",
//                Collections.singleton(Role.ADMIN), UserStatus.ACTIVE, false, LocalDateTime.now()));
//        System.out.println(newID);


//        userDao.insertNewUser(new User(21,"erwww@mail.com","765567ddd","ddsse",
//                "eeeeewe","0778897787",USER));
//        System.out.println(userDao);

    }
}