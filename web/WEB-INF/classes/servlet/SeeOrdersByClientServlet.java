package servlet;

import entity.Client;
import entity.Order;
import service.ClientService;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/seeOrdersByClient")
public class SeeOrdersByClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Client client = ClientService.getInstance().getClientByID(id).get();
        Set<Order> orders = OrderService.getInstance().getOrdersByClient(client);
        req.setAttribute("fullName", client.getFullName());
        req.setAttribute("orders", orders);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/see-orders-by-client.jsp").forward(req, resp);
    }
}