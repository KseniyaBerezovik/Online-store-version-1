package servlet;

import entity.Client;
import entity.Order;
import service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/seeOrders")
public class SeeOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client currentClient = (Client) req.getSession().getAttribute("client");
        Set<Order> orders = OrderService.getInstance().getOrdersByClient(currentClient);

        req.setAttribute("orders", orders);

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/see-orders.jsp");
        requestDispatcher.forward(req, resp);
    }
}