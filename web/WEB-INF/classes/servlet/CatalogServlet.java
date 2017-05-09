package servlet;

import entity.Product;
import service.CartService;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CartService.getInstance().updateCart(req);
        List<Product> products = ProductService.getInstance().getAll();
        req.setAttribute("products", products);
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/catalog.jsp").forward(req, resp);
    }
}
