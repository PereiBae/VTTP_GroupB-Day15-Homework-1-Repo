package vttp.groupb.ssf.Day15Hw1Repo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vttp.groupb.ssf.Day15Hw1Repo.models.Cart;
import vttp.groupb.ssf.Day15Hw1Repo.repositories.ShoppingCartRepository;

import java.util.Map;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    // Generate a unique cartId for each cart under a user
    private String generateCartId() {
        return java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    // Create a new cart for a User
    public void createCartForUser(String username) {
        String cartId = generateCartId();
        Cart newCart = new Cart(cartId);
        shoppingCartRepository.saveCart(username, cartId, newCart);
    }

    // Add items to cart
    public void addItemToCart(String username, String cartId, String itemName, int quantity) {
        Cart cart = shoppingCartRepository.findCart(username, cartId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for ID: " + cartId);
        }

        // Validate and initialize totalQuantity if necessary
        cart.validate();

        cart.addItem(itemName, quantity);
        shoppingCartRepository.saveCart(username, cartId, cart);
    }

    // Get all carts for a user
    public Map<String, Cart> getAllCartsForUser(String username) {
        return shoppingCartRepository.findAllCarts(username);
    }

    // Get a specific cart for a User
    public Cart getCartForUser(String username, String cartId) {
        Cart cart = shoppingCartRepository.findCart(username, cartId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found for ID: " + cartId);
        }
        return cart;
    }

    // Delete a specific cart for a User
    public void deleteCartForUser(String username, String cartId) {
        shoppingCartRepository.deleteCart(username, cartId);
    }

    // Check if a user exists
    public boolean userExists(String username) {
        return shoppingCartRepository.userExists(username);
    }

}
