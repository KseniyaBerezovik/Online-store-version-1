package servlet;

import entity.Client;
import entity.Order;
import entity.Product;
import service.ClientService;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

@WebServlet("/downloadStatistics")
public class DownloadStatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-disposition","attachment; filename=statistics.txt");
        long id = Long.parseLong(req.getParameter("id"));
        Client client = ClientService.getInstance().getClientByID(id).get();
        Set<Order> orders = OrderService.getInstance().getOrdersByClient(client);
        PrintWriter writer = resp.getWriter();
        writer.write("Заказы пользователя " + client.getName() + " " + client.getSurname() + ":\n");
        for(Order order : orders) {
            writer.write("Номер заказа: " + order.getId() + "\n");
            writer.write("Дата формирования заказа: " + order.getDateOfIssue() + "\n");
            if(order.getClosingDate() != null) {
                writer.write("Дата закрытия заказа: " + order.getClosingDate() + "\n");
            }
            writer.write("Состав заказа:\n");
            for(Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
                writer.write("Товар: " + entry.getKey().getName() + "\t");
                writer.write("Количество: " + entry.getValue() + "\n");
            }
            writer.write("\n");
        }
    }
}
