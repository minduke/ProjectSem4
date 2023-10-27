package com.tuithemngot.controller;

import com.tuithemngot.model.*;
import com.tuithemngot.repository.*;
import com.tuithemngot.service.CartManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ProductRepository proRepo;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        List<Product> listP = proRepo.findAll();
        model.addAttribute("products", listP);
        return "default/home";
    }


    @RequestMapping("/shop-cart/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        Product product = proRepo.findById(id);
        model.addAttribute("product", product);
        return "default/productDetail";
    }

    @Autowired
    CartManager cartManager;

    @RequestMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        List<CartItem> items = cartManager.getCart(session).getItems();
        model.addAttribute("items", items);
        return "default/shopCart";
    }

    @RequestMapping("/check-out")
    public String checkOut() {
        return "default/shopcartCheckout";
    }

    @RequestMapping("/tin-tuc")
    public String newsBOARD() {
        return "default/newsBoard";
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "default/loginForm";
    }

    @RequestMapping("/banhLanh")
    public String banhLanh(Model model){
        List<Product> listP = proRepo.findAll();
        model.addAttribute("products", listP);
        return "default/banhLanh";
    }
}
