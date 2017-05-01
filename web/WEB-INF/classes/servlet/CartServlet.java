package servlet;

import entity.Cart;
import entity.Client;
import service.CartService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client currentClient = (Client) req.getSession().getAttribute("client");
        Cart cart = CartService.getInstance().getCartByClient(currentClient);
        HttpSession session = req.getSession();
        session.setAttribute("cart", cart.getCartContent());
        session.setAttribute("cost", cart.getAllCost());

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
        dispatcher.forward(req, resp);
    }
}
