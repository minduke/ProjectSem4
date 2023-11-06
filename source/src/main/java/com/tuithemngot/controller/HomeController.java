package com.tuithemngot.controller;

import com.tuithemngot.dto.OrderDTO;
import com.tuithemngot.dto.OrderDetailDTO;
import com.tuithemngot.model.*;
import com.tuithemngot.repository.*;
import com.tuithemngot.repository.repositoryDTO.OrderDetailRepoDTO;
import com.tuithemngot.repository.repositoryDTO.OrderRepoDTO;
import com.tuithemngot.service.CartManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
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

    @Autowired
    DataSource dataSource;

    @Autowired
    OrderRepoDTO orderRepoDTO;

    @Autowired
    OrderDetailRepoDTO orderDetailRepoDTO;

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



//    @RequestMapping("/layout")
//    public String layout(Model model){
//        List<Type_product> showMenu = typeProductRepository.findAll();
//        model.addAttribute("menus", showMenu);
//        return "default/layout";
//    }

    @RequestMapping(value = "/check-out", method = RequestMethod.GET)
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
    public String checkin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        // đăng nhập không mã hoá pass
//        String sql = "select count(*) from customers where cus_username = ? and cus_password = ?";
//        int count = jdbcTemplate.queryForObject(sql, Integer.class, username, password);
//        if (count == 1) {
//            Customer customer = customerRepository.findByLogin(username, password);
//            request.getSession().setAttribute("user", customer);
//            return "redirect:/home";
//        } else {
//            return "redirect:/login";
//        }

        // đăng nhập có mã hoá
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null){
            return "redirect:/login";
        } else {
            String pass = customer.getCus_password();
            if (checkPassword(password, pass)){
                request.getSession().setAttribute("user", customer);
                return "redirect:/home";
            }
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/home";
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
    public String registered(@RequestParam("gender") String gender, @RequestParam("password") String password, Customer customer){
        String temp = encryptPassword(password);
        customer.setCus_password(temp);
        String cus_gender = gender;
        customer.setCus_gender(cus_gender);
        customerRepository.insertCustomer(customer);
        return "redirect:/login";
    }

    @RequestMapping(value = "/save-order", method = RequestMethod.POST)
    public String saveOrder(@RequestParam("order_receiver") String receiver, @RequestParam("order_phone_receiver") String phone_receiver, @RequestParam("order_delivery_address") String address, HttpSession session, HttpServletRequest request){

        Customer customer = (Customer) session.getAttribute("user");
        Long cus_id = customer.getCus_id();
        Cart cart = (Cart) session.getAttribute("gioHang");
        double order_total = cart.getTotal();
        String insertOrder = String.format("exec sp_insert_order ?, ?, ?, ?, ?", cus_id, order_total, receiver, address, phone_receiver);
        jdbcTemplate = new JdbcTemplate(dataSource);
        Long order_id = jdbcTemplate.queryForObject(insertOrder, new Object[]{cus_id, order_total, receiver, address, phone_receiver}, Long.class);
        System.out.println(order_id);
        String sql = "insert into order_detail (order_id, pro_id, import_price, pro_price, quantity, detail_total) values (?, ?, ?, ?, ?, ?)";
        cart = cartManager.getCart(session);
        List<CartItem> items = cart.getItems();
        for (CartItem item : items){
            Product product = item.getProduct();
            Long pro_id = product.getPro_id();
            float import_price = product.getImport_price();
            float pro_price = product.getPro_price();
            int quantity = item.getQuantity();
            double subTotal = item.getSubTotal();

            jdbcTemplate.update(sql, order_id, pro_id, import_price, pro_price, quantity, subTotal);
        }

        session.removeAttribute("gioHang");
        return "redirect:/home";
    }


    @RequestMapping("/lich-su-dat-hang")
    public String lichSuDonHang(Model model, HttpServletRequest request) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        Object check_session = request.getSession().getAttribute("user");
        if (check_session != null){
            Customer customer = (Customer) request.getSession().getAttribute("user");
            Long id = customer.getCus_id();
            List<OrderDTO> orderHistory = orderRepoDTO.showOrderByCusId(id);
            model.addAttribute("orders", orderHistory);
            return "/default/orderHistory";
        }
        return "redirect:/login";
    }


    @RequestMapping("/thong-tin-user")
    public String thongTin(Model model, HttpServletRequest request) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        Object check_session = request.getSession().getAttribute("user");
        if (check_session != null){
            return "/default/information";
        }
        return "redirect:/login";
    }

    @RequestMapping("/chi-tiet-don-hang/{id}")
    public String chiTiet(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        Object check_session = request.getSession().getAttribute("user");
        if (check_session != null){
            List<OrderDetailDTO> list = orderDetailRepoDTO.showOrderDetail(id);
            model.addAttribute("details", list);
            return "default/orderDetailUser";
        }
        return "redirect:/login";
    }

    @RequestMapping("/thay-doi-mat-khau")
    public String thayMatKhau() {return "default/thayMKhau";}

    @RequestMapping("/quen-mat-khau")
    public String layMatKhau() {return "default/quenMK";}


    public static String encryptPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
