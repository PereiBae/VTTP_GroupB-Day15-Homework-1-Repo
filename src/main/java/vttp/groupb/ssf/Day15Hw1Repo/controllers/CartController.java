package vttp.groupb.ssf.Day15Hw1Repo.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vttp.groupb.ssf.Day15Hw1Repo.models.Cart;
import vttp.groupb.ssf.Day15Hw1Repo.services.ShoppingCartService;

@Controller
public class CartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/carts")
    public String viewCarts(HttpSession session, Model model) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }

        model.addAttribute("title", "My Carts");
        model.addAttribute("carts", shoppingCartService.getAllCartsForUser(loggedInUser));
        return "cart";
    }

    @PostMapping("/carts/create")
    public String createCart(HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }

        shoppingCartService.createCartForUser(loggedInUser);
        return "redirect:/carts";
    }

    @GetMapping("/cart/{cartId}")
    public String viewCart(@PathVariable("cartId") String cartId, HttpSession session, Model model) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }

        Cart cart = shoppingCartService.getCartForUser(loggedInUser, cartId);
        model.addAttribute("title", "Cart Details");
        model.addAttribute("cart", cart);
        return "CartID";
    }


    @PostMapping("/cart/{cartId}/add")
    public String addItemToCart(
            @PathVariable("cartId") String cartId,
            @RequestParam("itemName") String itemName,
            @RequestParam("quantity") int quantity,
            HttpSession session
    ) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/";
        }

        shoppingCartService.addItemToCart(loggedInUser, cartId, itemName, quantity);
        return "redirect:/cart/" + cartId;
    }

}
