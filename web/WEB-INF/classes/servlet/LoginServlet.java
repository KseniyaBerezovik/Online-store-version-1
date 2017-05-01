package servlet;

import dto.CartDto;
import entity.Client;
import entity.Role;
import service.CartService;
import service.ClientService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");

        Optional<Client> clientOptional = ClientService.getInstance().getByEmailAndPass(email, pass);

        String jspPath;
        if(!clientOptional.isPresent()) {
            jspPath = "/login";
        } else {
            jspPath = "/catalog";
            HttpSession session = req.getSession(true);
            Client currentClient = clientOptional.get();
            CartDto cartDto = new CartDto(CartService.getInstance().getAmountProductsInCart(currentClient), currentClient);
            session.setAttribute("cartDto", cartDto);
            session.setAttribute("client", currentClient);
            session.setAttribute("fullName", currentClient.getName() + " " + currentClient.getSurname());
            session.setAttribute("role", currentClient.getRole());
            if(clientOptional.get().getRole() == Role.ADMIN) {
                req.getSession().setAttribute("isAdmin", true);
                jspPath = "/admin";
            }
        }
        resp.sendRedirect(jspPath);
    }
}
