package entity;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> cartContent = new HashMap<>();
    private Client client;
    private double allCost;

    public Cart(Map<Product, Integer> cartContent, Client client) {
        this.cartContent = cartContent;
        this.client = client;
    }

    public Map<Product, Integer> getCartContent() {
        return cartContent;
    }

    public Client getClient() {
        return client;
    }

    public double getAllCost() {
        this.allCost = 0;
        for (Product product : cartContent.keySet()) {
            allCost += (product.getPrice() * cartContent.get(product));
        }
        return allCost;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartContent=" + cartContent +
                ", client=" + client +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;

        Cart cart1 = (Cart) o;

        if (cartContent != null ? !cartContent.equals(cart1.cartContent) : cart1.cartContent != null) return false;
        return client != null ? client.equals(cart1.client) : cart1.client == null;
    }

    @Override
    public int hashCode() {
        int result = cartContent != null ? cartContent.hashCode() : 0;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }
}
