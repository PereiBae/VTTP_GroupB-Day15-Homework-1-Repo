package vttp.groupb.ssf.Day15Hw1Repo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import vttp.groupb.ssf.Day15Hw1Repo.models.Cart;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ShoppingCartRepository {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    // Save a cart under a specific user in Redis
    // Key: Username, Field: CartId, Value: Cart Object
    public void saveCart(String username, String cartId, Cart cart) {
        String userKey="User:" + username;
        redisTemplate.opsForHash().put(userKey,cartId,cart);
    }

    // Retrieve a specific cart under that user
    // return the Cart Object or null if it does not exist
    public Cart findCart(String username, String cartId) {
        String userKey = "User:" + username;
        return (Cart) redisTemplate.opsForHash().get(userKey, cartId);
    }

    // Retrieve all carts for a specific user
    public Map<String, Cart> findAllCarts(String username) {
        String userKey = "User:" + username;
        // Cast the resulting map from Redis to Map<String, Cart>
        Map<Object, Object> redisData = redisTemplate.opsForHash().entries(userKey);

        // Convert Map<Object, Object> to Map<String, Cart>
        Map<String, Cart> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : redisData.entrySet()) {
            result.put((String) entry.getKey(), (Cart) entry.getValue());
        }

        return result;
    }

    // Delete a specific cart under a user
    public void deleteCart(String username, String cartId) {
        String userKey = "User:" + username;
        redisTemplate.opsForHash().delete(userKey, cartId);
    }

    // Check if a user exists in Redis
    public boolean userExists(String username) {
        String userKey = "User:" + username;
        return Boolean.TRUE.equals(redisTemplate.hasKey(userKey));
    }

}
