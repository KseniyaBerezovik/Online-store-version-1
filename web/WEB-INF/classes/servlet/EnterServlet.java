package servlet;

import entity.Client;
import service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/enter")
public class EnterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/enter.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");

        Optional<Client> clientOptional = ClientService.getInstance().getByEmailAndPass(email, pass);

        String jspName;
        if(!clientOptional.isPresent()) {
            jspName = "enter.jsp";
        } else {
            jspName = "catalog.jsp";
            HttpSession session = req.getSession(true);
            session.setAttribute("userID", clientOptional.get().getId());
            session.setAttribute("fullName", clientOptional.get().getName() + " " + clientOptional.get().getSurname());
            session.setAttribute("role", clientOptional.get().getRole());
        }

        RequestDispatcher requestDispatcher
                = getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + jspName);
        requestDispatcher.forward(req, resp);
    }
}
