package servlet;

import entity.Cart;
import entity.Client;
import entity.Order;
import entity.Product;
import service.CartService;
import service.OrderService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client currentClient = (Client) req.getSession().getAttribute("client");
        Cart cart = CartService.getInstance().getCartByClient(currentClient);
        Map<Product, Integer> cartContent = cart.getCartContent();
        Optional<Order> order = OrderService.getInstance().create(currentClient, cartContent);
        String jspName;
        if (order.isPresent()) {
            req.setAttribute("order", order.get());
            CartService.getInstance().clear(currentClient);
            jspName = "create-order.jsp";
        } else {
            Map<Product, Integer> missingProducts = new HashMap<>();
            for(Map.Entry<Product, Integer> entry : cart.getCartContent().entrySet()) {
                Product currentProduct = ProductService.getInstance().getByID(entry.getKey().getId()).get();
                int currentAmount = currentProduct.getAmount();
                if(entry.getValue() > currentAmount) {
                    missingProducts.put(currentProduct, entry.getValue());
                }
            }
            jspName = "missing-products.jsp";
            req.setAttribute("missingProducts", missingProducts.entrySet());
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/" + jspName);
        dispatcher.forward(req, resp);
    }
}
