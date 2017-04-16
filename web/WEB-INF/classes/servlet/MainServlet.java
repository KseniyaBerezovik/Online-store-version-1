package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/OnlineStore")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String jspPath;
        if(session.getAttribute("userID") != null) {
            jspPath = "/WEB-INF/jsp/catalog.jsp";
        } else {
            jspPath = "/WEB-INF/jsp/main.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspPath);
        dispatcher.forward(req, resp);
    }
}
