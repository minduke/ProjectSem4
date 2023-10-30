package com.tuithemngot.controller;


import com.tuithemngot.model.Cart;
import com.tuithemngot.model.Product;
import com.tuithemngot.repository.ProductRepository;
import com.tuithemngot.service.CartManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopCartController {


    @Autowired
    ProductRepository proRepo;

    @Autowired
    CartManager cartManager;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String add(HttpSession session, @PathVariable("id") Long id, @RequestParam(value = "qty", required = false, defaultValue = "1") int qty){
        Product product = proRepo.findById(id);
        Cart cart = cartManager.getCart(session);
        cart.addItem(product, qty);
        cartManager.setCart(session, cart);
        return "redirect:/cart";
    }

    @RequestMapping("/remove/{id}")
    public String remove(HttpSession session, @PathVariable("id") Long id){
        Product product = proRepo.findById(id);
        Cart cart = cartManager.getCart(session);
        cart.removeItem(product);
//        cartManager.removeCart(session);
        return "redirect:/home";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, @RequestParam(value = "id", required = false) Long id, @RequestParam(value = "qty") int qty){
        Product product = proRepo.findById(id);
        Cart cart = cartManager.getCart(session);
        cart.updateItem(product, qty);
        return "redirect:/home";
    }
}
