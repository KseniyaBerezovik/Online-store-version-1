package dto;

import java.io.Serializable;

public class AmountDto implements Serializable {
    private int amount;

    public AmountDto(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "AmountDto{" +
                "amount=" + amount +
                '}';
    }
}
