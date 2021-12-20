//package alex.falendysh.epam_exhibition.servlet;
//
//import alex.falendysh.epam_exhibition.dao.impl.JdbcUserDAO;
//import alex.falendysh.epam_exhibition.model.User;
//import org.apache.log4j.Logger;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import static alex.falendysh.epam_exhibition.util.Constants.ModelConstants.*;
//
//@WebServlet(urlPatterns = {"/registration"})
//public class RegistrationServlet extends HttpServlet {
//
//    private static final Logger log = Logger.getLogger(RegistrationServlet.class);
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/registrationPage.jsp");
//        dispatcher.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String email = req.getParameter(EMAIL);
//        String password = req.getParameter(PASSWORD);
//        String password2 = req.getParameter("password2");
//        String firstName = req.getParameter(FIRST_NAME);
//        String lastName = req.getParameter(LAST_NAME);
//        String phone = req.getParameter(PHONE);
//
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setPhone(phone);
//        user.setRole();
//        boolean hasError = false;
//        String errorMessage = null;
//        String errorPass = null;
//        if (!password.equals(password2)) {
//            hasError = true;
//            errorPass = "password1 != password2";
//        }
//        user.setPassword(password);
//        JdbcUserDAO userDAO = JdbcUserDAO.getInstance();
//        userDAO.insertNewUser(user);
//
//        if (hasError) {
//            req.setAttribute("user", user);
//            req.setAttribute("errorPass", errorPass);
//            req.setAttribute("errorMessage", errorMessage);
//            doGet(req, resp);
//        } else {
//            resp.sendRedirect(req.getContextPath() + "/login");
//        }
//
//    }
//}
