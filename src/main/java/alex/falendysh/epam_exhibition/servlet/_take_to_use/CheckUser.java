//package alex.falendysh.epam_exhibition.servlet._take_to_use;
//
//import alex.falendish.dao.UserDAO;
//import alex.falendish.dao.impl.JdbcUserDAO;
//import alex.falendish.model.User;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "CheckUser", value = "/CheckUser")
//public class CheckUser extends HttpServlet {
//
//    public String getServletInfo() {
//        return "Registration servlet";
//    }
//
//    public void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        UserDAO userDAO = new JdbcUserDAO();
//        String username = request.getParameter("username");
//        User u = userDAO.findByUsername(username);
//        if (u == null) {
//            RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
//            req.forward(request, response);
//        } else {
//            if
//            (!u.getPassword().equals(request.getParameter("password"))) {
//                RequestDispatcher req = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
//                req.forward(request, response);
//
//            } else {
//                RequestDispatcher req = request.getRequestDispatcher("/successLogin.jsp");
//                req.forward(request, response);
//            }
//        }
//    }
//}