package vttp.groupb.ssf.Day15Hw1Repo.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import vttp.groupb.ssf.Day15Hw1Repo.services.ShoppingCartService;

@Controller
public class LoginPageController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/")
    public String showLoginPage(Model model) {
        model.addAttribute("title", "Login");
        return "index";
    }

    @PostMapping("/login")
    public String loginUser(String username, HttpSession session) {
        if (!shoppingCartService.userExists(username)) {
            shoppingCartService.createCartForUser(username); // Initialize the user
        }
        session.setAttribute("loggedInUser", username);
        return "redirect:/carts";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate(); // Clear session
        return "redirect:/";
    }

}
