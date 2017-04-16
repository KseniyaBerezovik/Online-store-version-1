package entity;

import other.OrderStatus;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private long id;
    private Map<Product, Integer> products = new HashMap<>();
    private LocalDate dateOfIssue;
    private LocalDate closingDate;
    private OrderStatus status;

    public Order(Map<Product, Integer> products, LocalDate dateOfIssue, OrderStatus status) {
        this.products = products;
        this.dateOfIssue = dateOfIssue;
        this.status = status;
    }

    public Order(long id, LocalDate dateOfIssue, LocalDate closingDate, OrderStatus status) {
        this.id = id;
        this.dateOfIssue = dateOfIssue;
        this.closingDate = closingDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        return dateOfIssue != null ? dateOfIssue.equals(order.dateOfIssue) : order.dateOfIssue == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                ", dateOfIssue=" + dateOfIssue +
                ", closingDate=" + closingDate +
                ", status=" + status +
                '}';
    }
}
