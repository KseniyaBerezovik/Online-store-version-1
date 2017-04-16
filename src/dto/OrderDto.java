package dto;

import entity.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OrderDto {
    private Map<Product, Integer> products = new HashMap<>();
    private LocalDate dateOfIssue;
    private double totalPrice = 0;

    public OrderDto(Map<Product, Integer> products, LocalDate dateOfIssue) {
        this.products = products;
        this.dateOfIssue = dateOfIssue;
        for (Product product : products.keySet()) {
            totalPrice += product.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
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

    @Override
    public String toString() {
        return "OrderDto{" +
                "products=" + products +
                ", dateOfIssue=" + dateOfIssue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDto)) return false;

        OrderDto orderDto = (OrderDto) o;

        if (products != null ? !products.equals(orderDto.products) : orderDto.products != null) return false;
        return dateOfIssue != null ? dateOfIssue.equals(orderDto.dateOfIssue) : orderDto.dateOfIssue == null;
    }

    @Override
    public int hashCode() {
        int result = products != null ? products.hashCode() : 0;
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        return result;
    }
}
