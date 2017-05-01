package dto;

import entity.Client;

public class CartDto {
    private int amountProducts;
    Client client;

    public CartDto(int amountProducts, Client client) {
        this.amountProducts = amountProducts;
        this.client = client;
    }

    public int getAmountProducts() {
        return amountProducts;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "amountProducts=" + amountProducts +
                ", client=" + client +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartDto)) return false;

        CartDto cartDto = (CartDto) o;

        if (amountProducts != cartDto.amountProducts) return false;
        return client != null ? client.equals(cartDto.client) : cartDto.client == null;
    }

    @Override
    public int hashCode() {
        int result = amountProducts;
        result = 31 * result + (client != null ? client.hashCode() : 0);
        return result;
    }
}
