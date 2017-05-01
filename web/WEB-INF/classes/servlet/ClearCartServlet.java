package servlet;

import entity.Client;
import service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/clearCart")
public class ClearCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Client currentClient = (Client) req.getSession().getAttribute("client");
        CartService.getInstance().clear(currentClient);
        resp.sendRedirect("/cart");
    }
}
