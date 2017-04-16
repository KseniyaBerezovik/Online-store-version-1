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

@WebServlet("/showClient")
public class ShowClient extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long clientID = Long.parseLong(req.getParameter("id"));
        Client client = ClientService.getInstance().getClientByID(clientID).get();

        req.setAttribute("client", client);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/show-client.jsp");
        dispatcher.forward(req, resp);
    }
}
