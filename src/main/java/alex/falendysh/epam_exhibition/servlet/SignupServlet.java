package alex.falendysh.epam_exhibition.servlet;

import alex.falendysh.epam_exhibition.exceptions.user.InvalidUserException;
import alex.falendysh.epam_exhibition.exceptions.user.SuchUserIsAlreadyExistsException;
import alex.falendysh.epam_exhibition.model.User;
import alex.falendysh.epam_exhibition.service.impl.UserServiceImpl;
import alex.falendysh.epam_exhibition.service.interfaces.UserService;
import alex.falendysh.epam_exhibition.util.dto.UserDto;
import alex.falendysh.epam_exhibition.util.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static alex.falendysh.epam_exhibition.util.Constants.ModelConstants.*;
import static alex.falendysh.epam_exhibition.util.Constants.PathsConstants.*;
import static alex.falendysh.epam_exhibition.util.enums.Role.USER;

@WebServlet(urlPatterns = {"/signupServlet"})
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LoggerFactory.getLogger(SignupServlet.class);

    private UserService<UserDto> userService;

    public SignupServlet(UserService<UserDto> userService) {
        this.userService = userService;
    }

    public SignupServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signup.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDto userDto = new UserDto();
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String phone = request.getParameter(PHONE);
        if (email == null || password == null ||
                firstName == null || lastName == null ||
                phone == null) {
            RequestDispatcher rd=request.getRequestDispatcher(ABOUT_US);
            rd.forward(request,response);
        } else {
            System.out.println("get all parameters, try to register");
            userDto.setEmail(email);
            userDto.setPassword(password);
            userDto.setFirstName(firstName);
            userDto.setLastName(lastName);
            userDto.setPhone(phone);
            userDto.setRole(USER);
        }
        System.out.println(userDto.toString());
        boolean isSaved = false;
        try {
           isSaved = userService.save(userDto);
        } catch (SuchUserIsAlreadyExistsException e) {
            request.setAttribute("isUserExists", true);
            logger.warn("Such user is already exists", e);
        } catch (InvalidUserException e) {
            request.setAttribute("isInvalidData", true);
            logger.warn("Invalid input data", e);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("no such algorithm exception");
            e.printStackTrace();
        }

        RequestDispatcher rd;
        if (isSaved) {
            rd = request.getRequestDispatcher(LOGIN_JSP);
        } else {
            rd = request.getRequestDispatcher(SIGN_UP_JSP);
        }
        rd.forward(request,response);
    }
}