package dto;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private int amount;
    private long id;

    public ProductDto(int amount, long id) {
        this.amount = amount;
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "amount=" + amount +
                ", id=" + id +
                '}';
    }
}
