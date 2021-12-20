//package alex.falendysh.epam_exhibition.servlet._take_to_use;
//
//import alex.falendish.dao.impl.JdbcUserDAO;
//import alex.falendish.model.User;
//import alex.falendish.utils.Role;
//import alex.falendish.utils.UserStatus;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import static alex.falendish.utils.CommonUtils.isBlank;
//
///**
// * ControllerServlet.java
// * This servlet acts as a page controller for the application, handling all
// * requests from the user.
// */
//
//@WebServlet("/")
//public class UserServletsControler extends HttpServlet {
//    private JdbcUserDAO jdbcUserDAO;
//
//    public void init() {
//        jdbcUserDAO= new JdbcUserDAO() {
//
//            private void doPost(HttpServletRequest request, HttpServletResponse response)
//                    throws ServletException, IOException {
//                doGet(request, response);
//            }
//
//            private void doGet(HttpServletRequest request, HttpServletResponse response)
//                    throws ServletException, IOException {
//                String action = request.getServletPath();
//
//                try {
//                    switch (action) {
//                        case "/new":
//                            showNewForm(request, response);
//                            break;
//                        case "/insert":
//                            insertNewUser(request, response);
//                            break;
//                        case "/delete":
//                            deleteUser(request, response);
//                            break;
//                        case "/edit":
//                            showEditForm(request, response);
//                            break;
//                        case "/update":
//                            updateUser(request, response);
//                            break;
//                        default:
//                            listUser(request, response);
//                            break;
//                    }
//                } catch (SQLException ex) {
//                    throw new ServletException(ex);
//                }
//            }
//
//            private Set<Role> parseUserRoles(String value) {
//                if (isBlank(value)) {
//                    return Collections.emptySet();
//                }
//                return Arrays.stream(value.split(","))
//                        .map(Role::valueOf)
//                        .collect(Collectors.toSet());
//            }
//
//            private void listUser(HttpServletRequest request, HttpServletResponse response)
//                    throws SQLException, IOException, ServletException {
//                List<User> listUser = jdbcUserDAO.selectAllUsers();
//                request.setAttribute("listUser", listUser);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
//                dispatcher.forward(request, response);
//            }
//
//            private void showNewForm(HttpServletRequest request, HttpServletResponse response)
//                    throws ServletException, IOException {
//                RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//                dispatcher.forward(request, response);
//            }
//
//            private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//                    throws SQLException, ServletException, IOException {
//                long id = Long.parseLong(request.getParameter("id"));
//                User existingUser = jdbcUserDAO.findUserById(id);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
//                request.setAttribute("user", existingUser);
//                dispatcher.forward(request, response);
//
//            }
//
//            private void insertNewUser(HttpServletRequest request, HttpServletResponse response)
//                    throws SQLException, IOException {
//                Long id = Long.valueOf(request.getParameter("id"));
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//                String mail = request.getParameter("mail");
//                String firstName = request.getParameter("first_name");
//                String lastName = request.getParameter("last_name");
//                UserStatus status = UserStatus.valueOf(request.getParameter("status"));
//                boolean locked = Boolean.parseBoolean(request.getParameter("locked"));
//                Set<Role> newUserRole = (parseUserRoles(request.getParameter("roles")));
//                User newUser = new User(id, username, password, mail, firstName,
//                        lastName, newUserRole, status, locked, LocalDateTime.now());
//
//                jdbcUserDAO.createUser(newUser);
//                response.sendRedirect("list");
//            }
//
//            private void updateUser(HttpServletRequest request, HttpServletResponse response)
//                    throws SQLException, IOException {
//                Long id = Long.valueOf(request.getParameter("id"));
//                String username = request.getParameter("userName");
//                String password = request.getParameter("password");
//                String mail = request.getParameter("mail");
//                String firstName = request.getParameter("first_name");
//                String lastName = request.getParameter("last_name");
//                User book = new User(username, password, mail, firstName, lastName);
//                jdbcUserDAO.updateUser(id, book);
//                response.sendRedirect("list");
//            }
//
//            private void deleteUser(HttpServletRequest request, HttpServletResponse response)
//                    throws SQLException, IOException {
//                long id = Long.parseLong((request.getParameter("id")));
//                jdbcUserDAO.deleteUser(id);
//                response.sendRedirect("list");
//
//            }
//        };
//    }
//}