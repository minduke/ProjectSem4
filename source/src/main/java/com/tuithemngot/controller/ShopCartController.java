package com.tuithemngot.controller;


import com.tuithemngot.model.Cart;
import com.tuithemngot.model.CartImport;
import com.tuithemngot.model.CartItem;
import com.tuithemngot.model.Product;
import com.tuithemngot.repository.ProductRepository;
import com.tuithemngot.service.CartManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopCartController {


    @Autowired
    ProductRepository proRepo;

    @Autowired
    CartManager cartManager;

    @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
    public String add(HttpSession session, @PathVariable("id") Long id, @RequestParam(value = "qty", required = false, defaultValue = "1") int qty, HttpServletRequest request){
        Object check_cart = request.getSession().getAttribute("user");
        if (check_cart != null) {
            Product product = proRepo.findById(id);
            Cart cart = cartManager.getCart(session);
            cart.addItem(product, qty);
            cartManager.setCart(session, cart);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/remove/{id}")
    public String remove(HttpSession session, @PathVariable("id") Long id){
        Product product = proRepo.findById(id);
        Cart cart = cartManager.getCart(session);
        cart.removeItem(product);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(HttpSession session, @PathVariable(value = "id", required = false) Long id, @RequestParam(value = "qty", required = false) Integer qty){
        Product product = proRepo.findById(id);
        Cart cart = cartManager.getCart(session);
        if (qty == null){
            qty = 0;
        }
        cart.updateItem(product, qty);
        return "redirect:/cart";
    }

    // add to cart của admin
    @RequestMapping(value = "/add-to-cart/{id}", method = RequestMethod.POST)
    public String addImport(HttpSession session, @PathVariable("id") Long id, HttpServletRequest request){
        Product product = proRepo.findById(id);
        CartImport cartImport = cartManager.getImportCart(session);
        cartImport.addItem(product, 1);
        cartManager.setImportCart(session, cartImport);
        return "redirect:/admin/products";
    }

    @RequestMapping(value = "/remove-import/{id}", method = RequestMethod.POST)
    public String removeImport(HttpSession session, @PathVariable("id") Long id){
        Product product = proRepo.findById(id);
        CartImport cartImport = cartManager.getImportCart(session);
        cartImport.removeItem(product);
        return "redirect:/admin/new-import";
    }

    @RequestMapping(value = "/update-import/{id}", method = RequestMethod.POST)
    public String updateImport(HttpSession session, @PathVariable("id") Long id, @RequestParam(value = "import_price", required = false) float import_price, @RequestParam(value = "quantity") Integer quantity){
        Product product = proRepo.findById(id);
        CartImport cartImport = cartManager.getImportCart(session);
        if (quantity == null){
            quantity = 0;
        }
        cartImport.updateItem(product, quantity, import_price);
        return "redirect:/admin/new-import";
    }
}
