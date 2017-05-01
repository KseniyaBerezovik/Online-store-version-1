package servlet;

import entity.Order;
import entity.Product;
import service.OrderService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/seeOrdersByProduct")
public class SeeOrdersByProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productID = Long.parseLong(req.getParameter("id"));
        Product product = ProductService.getInstance().getByID(productID).get();
        Set<Order> orders = OrderService.getInstance().getOrdersByProduct(product);
        orders.forEach(System.out::println);
        req.setAttribute("productName", product.getName());
        req.setAttribute("orders", orders);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/see-orders-by-product.jsp");
        dispatcher.forward(req, resp);
    }
}