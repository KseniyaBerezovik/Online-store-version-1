package servlet;

import com.google.gson.Gson;
import dto.AmountDto;
import dto.ProductDto;
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
        Gson gson = new Gson();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        ProductDto productDto = gson.fromJson(req.getReader(), ProductDto.class);
        Client currentClient = (Client) req.getSession().getAttribute("client");
        Product product = ProductService.getInstance().getByID(productDto.getId()).get();

        CartService.getInstance().addProduct(product, productDto.getAmount(), currentClient);
        resp.getWriter().write(gson.toJson(new AmountDto(CartService.getInstance().getAmountProductsInCart(currentClient))));
    }
}
