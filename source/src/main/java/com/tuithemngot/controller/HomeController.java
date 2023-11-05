package com.tuithemngot.controller;

import com.tuithemngot.model.*;
import com.tuithemngot.repository.*;
import com.tuithemngot.service.CartManager;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ProductRepository proRepo;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        List<Product> listP = proRepo.findAllForUser();
        model.addAttribute("products", listP);
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/home";
    }


    @RequestMapping("/shop-cart/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model) {
        Product product = proRepo.findById(id);
        model.addAttribute("product", product);
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/productDetail";
    }

    @Autowired
    CartManager cartManager;

    @RequestMapping("/cart")
    public String showCart(HttpSession session, Model model) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        List<CartItem> items = cartManager.getCart(session).getItems();
        if (items == null || items.isEmpty()){
            model.addAttribute("msg", "Chưa có sản phẩm trong giỏ");
        } else {
            model.addAttribute("items", items);
        }
        return "default/shopCart";
    }

    @Autowired
    Type_product_Repository typeProductRepository;

    @RequestMapping("/layout")
    public String layout(Model model){
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/layout";
    }

    @RequestMapping("/check-out")
    public String checkOut(Model model) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/shopcartCheckout";
    }

    @RequestMapping("/tin-tuc")
    public String newsBOARD(Model model) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/newsBoard";
    }

    @RequestMapping("/login")
    public String loginForm(Model model) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/loginForm";
    }

    @RequestMapping("/menu/{id}")
    public String banhLanh(Model model, @PathVariable("id") Long id){
        List<Product> listP = proRepo.findByFilter(id);
        model.addAttribute("products", listP);
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        Type_product typeProduct = typeProductRepository.findById(id);
        model.addAttribute("typeName", typeProduct);
        return "default/showProductByType";
    }
}
