package alex.falendysh.epam_exhibition.servlet._take_to_use;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Registration", value = "/Registration")
public class Registration extends HttpServlet {

    public String getServletInfo() {
        return "Registration servlet";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("login") != null) {
            RequestDispatcher req = request.getRequestDispatcher("/CheckUser");
            req.forward(request, response);

        } else if (request.getParameter("registration") != null) {
            RequestDispatcher req = request.getRequestDispatcher("WEB-INF/jsp/registration.jsp");
            req.forward(request, response);
        }

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
////        previous version
//        if(username.isEmpty() || password.isEmpty())
//        {
//            RequestDispatcher req = request.getRequestDispatcher("WEB-INF/jsp/register_main.jsp");
//            req.include(request, response);
//        }
//        else {
//            RequestDispatcher req = request.getRequestDispatcher("WEB-INF/jsp/sign_if_registered.jsp");
//            req.forward(request, response);
//        }


//                    String username = request.getParameter("username");
//                    String password = request.getParameter("password");
//                    if (request.getParameter("login") != null) {
//                        this.forward("/CheckUser", request, response);
//
//                    } else if (request.getParameter("registration") != null) {
//                        this.forward("/register_main.jsp", request, response);
//                    }
    }
}