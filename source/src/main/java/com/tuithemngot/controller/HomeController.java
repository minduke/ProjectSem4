package com.tuithemngot.controller;

import com.tuithemngot.model.*;
import com.tuithemngot.repository.*;
import com.tuithemngot.service.CartManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ProductRepository proRepo;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartManager cartManager;

    @Autowired
    Type_product_Repository typeProductRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

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

    @RequestMapping(value = "/checkin", method = RequestMethod.POST)
    public String checkin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request){
        String sql = "select count(*) from customers where cus_username = ? and cus_password = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
        if (count == 1){
            Customer customer = customerRepository.findByLogin(username, password);
            request.getSession().setAttribute("user", customer);
            return "redirect:/home";
        } else {
            return "redirect:/login";
        }
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



    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "default/register";
    }

    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public String registered(@RequestParam("gender") String gender, Customer customer){
        String cus_gender = gender;
        customer.setCus_gender(cus_gender);
        customerRepository.insertCustomer(customer);
        return "redirect:/login";
    }
}
