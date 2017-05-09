package servlet;

import dto.CartDto;
import entity.Client;
import entity.Role;
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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/WEB-INF/jsp/registration-client.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        Optional<Client> clientByEmail = ClientService.getInstance().getClientByEmail(email);

        if (clientByEmail.isPresent()) {
            RequestDispatcher requestDispatcher
                    = getServletContext().getRequestDispatcher("/WEB-INF/jsp/client-already-exists.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        Client client = new Client(
                name,
                surname,
                email,
                pass,
                phone,
                address,
                Role.USER);

        Client clientFromDB = ClientService.getInstance().save(client).get();

        HttpSession session = req.getSession();
        session.setAttribute("client", clientFromDB);
        session.setAttribute("cartDto", new CartDto(0, clientFromDB));
        session.setAttribute("role", Role.USER);

        resp.sendRedirect("/catalog");
    }
}