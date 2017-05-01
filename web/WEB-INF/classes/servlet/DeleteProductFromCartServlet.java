package servlet;

import entity.Client;
import entity.Product;
import service.CartService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteFromCart")
public class DeleteProductFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        Product product = ProductService.getInstance().getByID(id).get();
        Client currentClient = (Client) req.getSession().getAttribute("client");
        CartService.getInstance().deleteProduct(product, amount, currentClient);
        resp.sendRedirect("/cart");
    }
}
