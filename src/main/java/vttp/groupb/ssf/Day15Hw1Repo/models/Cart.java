package vttp.groupb.ssf.Day15Hw1Repo.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private String cartId;
    private Map<String, Integer> items = new HashMap<>();
    private int totalQuantity = 0;

    public Cart() {

    }

    public Cart(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void validate() {
        if (totalQuantity == 0 && items != null) {
            totalQuantity = items.values().stream().mapToInt(Integer::intValue).sum();
        }
    }

    // Add an item to the cart
    public void addItem(String itemName, int quantity) {
        // items.getOrDefault checks the map if the item exists in the map already then updates the quantity
        items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
        totalQuantity += quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                ", items=" + items +
                ", totalQuantity=" + totalQuantity +
                '}';
    }
}
