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

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productAmount = Integer.parseInt(req.getParameter("productAmount"));
        long id = Long.valueOf(req.getParameter("id"));
        if(productAmount > 0) {
            Product product = ProductService.getInstance().getByID(id).get();
            Client currentClient = (Client) req.getSession().getAttribute("client");
            CartService.getInstance().addProduct(product, productAmount, currentClient);
        }
        resp.sendRedirect("/catalog");
    }
}
