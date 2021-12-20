//package alex.falendysh.epam_exhibition.servlet._take_to_use;
//
//import alex.falendish.controller.Dispatcher;
//import alex.falendish.model.User;
//import alex.falendish.utils.Role;
//import alex.falendish.utils.UserStatus;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static alex.falendish.utils.CommonUtils.isBlank;
//
//@WebServlet(name = "AddUser", value = "/AddUser")
//public class AddUser extends Dispatcher {
//
//    public String getServletInfo() {
//        return "Add user servlet";
//    }
//
//    public void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        ServletContext ctx = getServletContext();
//
//        if (request.getParameter("save") != null) {
//
//            Long id = Long.valueOf(request.getParameter("id"));
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//            String mail = request.getParameter("mail");
//            String firstName = request.getParameter("first_name");
//            String lastName = request.getParameter("last_name");
//            UserStatus status = UserStatus.valueOf(request.getParameter("status"));
//            boolean locked = Boolean.parseBoolean(request.getParameter("locked"));
//            Set<Role> newUserRole = (parseUserRoles(request.getParameter("roles")));
//            User newUser = new User(id, username, password, mail, firstName,
//                    lastName, newUserRole, status, locked, LocalDateTime.now());
//            ctx.setAttribute("user", newUser);
//
//            boolean res = UserList.addUser(newUser);
//
//            if (res) {
//                this.forward("/successRegistration.jsp", request, response);
//            } else {
//                this.forward("/errorRegistration.html", request, response);
//            }
//        } else if (request.getParameter("cancel") != null) {
//            this.forward("/login.html", request, response);
//        }
//    }
//
//    private Set<Role> parseUserRoles(String value) {
//        if (isBlank(value)) {
//            return Collections.emptySet();
//        }
//        return Arrays.stream(value.split(","))
//                .map(Role::valueOf)
//                .collect(Collectors.toSet());
//    }
//}
//
//class UserList {
//
//    public static User findUser(String user) {
//        return users.get(user);
//    }
//
//    public static boolean addUser(User user) {
//        boolean result = false;
//
//        if ((!users.containsKey(user.getUsername())) && (user.getPassword() != null) && (!"".equals(user.getPassword()))) {
//            users.put(user.getUsername(), user);
//            result = true;
//        }
//        return result;
//    }
//
//    private static Map<String, User> users = new HashMap<String, User>();
//}