package service;

import dao.CartDao;
import dto.CartDto;
import entity.Cart;
import entity.Client;
import entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CartService {
    private static CartService INSTANCE = null;
    private static final Object LOCK = new Object();

    private CartService() {}

    public static CartService getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new CartService();
                }
            }
        }
        return INSTANCE;
    }

    public void addProduct(Product product, int amount, Client client) {
        CartDao.getInstance().addProduct(product, amount, client);
    }

    public boolean deleteProduct(Product product, int amount, Client client) {
        return CartDao.getInstance().deleteProduct(product, amount, client);
    }

    public Cart getCartByClient(Client client) {
        return CartDao.getInstance().getCartByClient(client);
    }

    public int getAmountProductsInCart(Client client) {
        return CartDao.getInstance().getAmountProductsInCart(client);
    }

    public void updateCart(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        Client currentClient = (Client) session.getAttribute("client");
        CartDto cartDto = new CartDto(CartService.getInstance().getAmountProductsInCart(currentClient), currentClient);
        session.setAttribute("cartDto", cartDto);
    }

    public void clear(Client client) {
        CartDao.getInstance().clear(client);
    }
}
