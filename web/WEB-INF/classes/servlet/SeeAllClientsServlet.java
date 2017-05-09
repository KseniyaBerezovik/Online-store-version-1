package servlet;

import entity.Client;
import service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/seeAllClients")
public class SeeAllClientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Client> clients = ClientService.getInstance().getAll();
        req.setAttribute("clients", clients);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/see-all-clients.jsp").forward(req, resp);
    }
}
