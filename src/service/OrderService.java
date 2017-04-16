package service;

import dao.OrderDao;
import dao.ProductDao;
import entity.Client;
import entity.Order;
import entity.Product;
import other.OrderStatus;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class OrderService {
    private static OrderService INSTANCE = null;
    private static Object LOCK = new Object();

    private OrderService() {}

    public static OrderService getInstance() {
        if(INSTANCE == null) {
            synchronized (LOCK) {
                if(INSTANCE == null) {
                    INSTANCE = new OrderService();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Order> create(Client client, Map<Product, Integer> products) {
        if(!isPossibleCreateOrder(products)) {
            return Optional.empty();
        }
        Order order = new Order(products, LocalDate.now(), OrderStatus.ACCEPTED);

        return OrderDao.getInstance().save(order, client);
    }

    private boolean isPossibleCreateOrder(Map<Product, Integer> products) {
        for(Map.Entry<Product, Integer> entry : products.entrySet()) {
            int amount = ProductDao.getInstance().getByID(entry.getKey().getId()).get().getAmount();
            if (entry.getValue() > amount) {
                System.out.println("Товаров на складе недостаточно");
                return false;
            }
        }
        return true;
    }
}
