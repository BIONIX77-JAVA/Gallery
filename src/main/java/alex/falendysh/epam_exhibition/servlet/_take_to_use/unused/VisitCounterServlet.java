//package alex.falendysh.epam_exhibition.servlet._take_to_use.unused;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//@WebServlet(name = "VisitCounterServlet", value = "/VisitCounterServlet")
//public class VisitCounterServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        HttpSession session = req.getSession();
//        Integer visitCounter = (Integer) session.getAttribute("visitCounter");
//        if (visitCounter == null) {
//            visitCounter = 1;
//        } else {
//            visitCounter++;
//        }
//        session.setAttribute("visitCounter", visitCounter);
//        String username = req.getParameter("username");
//        resp.setContentType("text/html");
//        PrintWriter printWriter = resp.getWriter();
//        if (username == null) {
//            printWriter.write("Hello, Anonymous" + " ");
//        } else {
//            printWriter.write("Hello, " + username + " ");
//        }
//        printWriter.write("Page was visited " + visitCounter + " times.");
//        printWriter.close();
//    }
//}