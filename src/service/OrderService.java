package service;

import dao.OrderDao;
import dao.ProductDao;
import entity.Client;
import entity.Order;
import entity.Product;
import entity.OrderStatus;

import java.time.LocalDate;
import java.util.*;

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
        if(getMissingProducts(products).size() != 0) {
            return Optional.empty();
        }
        Order order = new Order(products, LocalDate.now(), OrderStatus.ACCEPTED);

        return OrderDao.getInstance().save(order, client);
    }

    public Map<Product, Integer> getMissingProducts(Map<Product, Integer> products) {
       Map<Product, Integer> missingProductsSelectedAmountMap = new HashMap<>();
        for(Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = ProductDao.getInstance().getByID(entry.getKey().getId()).get();
            int amountInStorage = product.getAmount();
            int selectedAmount = entry.getValue();
            if (selectedAmount > amountInStorage) {
                missingProductsSelectedAmountMap.put(product, selectedAmount);
            }
        }
        return missingProductsSelectedAmountMap;
    }

    public Set<Order> getOrdersByClient(Client client) {
        Set<Order> result = OrderDao.getInstance().getOrdersByClient(client);
        return result.size() == 0 ? null : result;
    }

    public Set<Order> getOrdersByProduct(Product product) {
        return OrderDao.getInstance().getOrdersByProduct(product);
    }

    public void deleteOrdersBeforeDate(LocalDate date) {
        OrderDao.getInstance().deleteOrdersBeforeDate(date);
    }

    public Set<Order> getAll() {
        return OrderDao.getInstance().getAll();
    }
}
