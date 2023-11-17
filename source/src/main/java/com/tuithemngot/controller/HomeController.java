package com.tuithemngot.controller;

import com.tuithemngot.config.Config;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public String productDetail(@PathVariable("id") Long id,
                                Model model) {
        Product product = proRepo.findById(id);
        model.addAttribute("product", product);
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/productDetail";
    }


    @RequestMapping("/cart")
    public String showCart(HttpSession session,
                           Model model) {
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
    public String checkin(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletRequest request) {
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
        request.getSession().removeAttribute("gioHang");
        return "redirect:/home";
    }

    @RequestMapping("/menu/{id}")
    public String banhLanh(Model model,
                           @PathVariable("id") Long id){
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
    public String registered(@RequestParam("gender") String gender,
                             @RequestParam("password") String password,
                             Customer customer,
                             @RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             RedirectAttributes redirectAttributes){
        String temp = encryptPassword(password);
        customer.setCus_password(temp);
        String cus_gender = gender;
        customer.setCus_gender(cus_gender);
        String findByUsername = "select count(*) from customers where cus_username = ?";
        int countUsername = jdbcTemplate.queryForObject(findByUsername, Integer.class, username);
        if (countUsername == 1){
            redirectAttributes.addFlashAttribute("error", "Tên tài khoản đã tồn tại");
            return "redirect:/register";
        } else {
            customer.setCus_username(username);
            String findByEmail = "select count(*) from customers where cus_email = ?";
            int countEmail = jdbcTemplate.queryForObject(findByEmail, Integer.class, email);
            if (countEmail == 1){
                redirectAttributes.addFlashAttribute("error", "Email đã tồn tại");
                return "redirect:/register";
            } else {
                customer.setCus_email(email);
                String findByPhone = "select count(*) from customers where cus_phone = ?";
                int countPhone = jdbcTemplate.queryForObject(findByPhone, Integer.class, phone);
                if (countPhone >= 1){
                    redirectAttributes.addFlashAttribute("error", "Số điện thoại đã tồn tại");
                    return "redirect:/register";
                } else {
                    customer.setCus_phone(phone);
                }
            }
        }
        customerRepository.insertCustomer(customer);
        return "redirect:/login";
    }

    @RequestMapping(value = "/save-order", method = RequestMethod.POST)
    public String saveOrder(@RequestParam("order_receiver") String receiver,
                            @RequestParam("order_phone_receiver") String phone_receiver,
                            @RequestParam("order_delivery_address") String address,
                            @RequestParam("note") String order_note,
                            @RequestParam("temp") String temp,// cái này để lấy địa chỉ gán sẵn của user
                            @RequestParam("optionsRadios") String options,
                            HttpSession session,
                            HttpServletRequest request) throws UnsupportedEncodingException {

        Customer customer = (Customer) session.getAttribute("user");
        Long cus_id = customer.getCus_id();
        Cart cart = (Cart) session.getAttribute("gioHang");
        double order_total = cart.getTotal();
        String adr = "";
        if (address.isEmpty()){
            adr = temp;
        } else {
            adr = address;
        }

        String insertOrder = String.format("exec sp_insert_order ?, ?, ?, ?, ?, ?", cus_id, order_total, receiver, adr, phone_receiver, order_note);
        jdbcTemplate = new JdbcTemplate(dataSource);
        Long order_id = jdbcTemplate.queryForObject(insertOrder, new Object[]{cus_id, order_total, receiver, adr, phone_receiver, order_note}, Long.class);
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
        if (options.equals("COD")){
            String updatePayment = "update orders set payment_methods = ? where order_id = ?";
            jdbcTemplate.update(updatePayment, options, order_id);
            return "redirect:/resultCOD";
        } else {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String orderType = "other";

            Long amount = (long) (order_total * 100);
            String bankCode = "NCB";

            String vnp_TxnRef = Config.getRandomNumber(8);
            String vnp_IpAddr = Config.getIpAddress(request);

            String vnp_TmnCode = Config.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");

            //cho nay co 3 cach thanh toan VNBANK,VNPAYQR,INTCARD nay h thieu cai nay
            vnp_Params.put("vnp_BankCode", "VNBANK");


            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_TxnRef", String.valueOf(order_id));
            vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + order_id);

            //cai nay optional
            vnp_Params.put("vnp_OrderType", orderType);


            vnp_Params.put("vnp_ReturnUrl", "http://localhost:8080/result");
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String updatePayment = "update orders set payment_methods = ? where order_id = ?";
            jdbcTemplate.update(updatePayment, options, order_id);
            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;

            return "redirect:" + paymentUrl;
        }
    }


    @RequestMapping("/result")
    public String result(@RequestParam("vnp_ResponseCode") int resultCode,
                         @RequestParam("vnp_TxnRef") Long id,
                         @RequestParam("vnp_BankCode") String name,
                         @RequestParam("vnp_PayDate") String date,
                         @RequestParam("vnp_TransactionNo") String no,
                         Model model) throws ParseException {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        if (resultCode == 00){

            Long order_id = id;
            String dateTimeString = date;
            String pattern = "yyyyMMddHHmmss";
            SimpleDateFormat inputDateFormat = new SimpleDateFormat(pattern);
            java.util.Date date2 = inputDateFormat.parse(dateTimeString);

            // Chuyển đổi sang định dạng ngày giờ của SQL Server
            String sqlServerDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(sqlServerDateTimeFormat);
            String sqlServerDateTimeString = outputDateFormat.format(date2);
            String transaction_no = no;
            String sql = "update orders set transaction_no = ?, transaction_date = ? where order_id = ?";
            jdbcTemplate.update(sql, transaction_no, sqlServerDateTimeString, order_id);

            model.addAttribute("msg", "Thanh toán thành công");
            model.addAttribute("date", sqlServerDateTimeString);
            model.addAttribute("no", transaction_no);
            model.addAttribute("bank_name", name);
        } else {
            model.addAttribute("msg", "Thanh toán thất bại");
        }
        return "/default/result";
    }

    @RequestMapping("/resultCOD")
    public String COD(Model model){
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        model.addAttribute("msg", "Đặt hàng thành công. Chúng tôi sẽ liên lạc với bạn trong thời gian nhanh nhất");
        return "default/resultCOD";
    }

    @RequestMapping("/lich-su-dat-hang")
    public String lichSuDonHang(Model model,
                                HttpServletRequest request) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        Customer customer = (Customer) request.getSession().getAttribute("user");
        Long id = customer.getCus_id();
        List<OrderDTO> orderHistory = orderRepoDTO.showOrderByCusId(id);
        model.addAttribute("orders", orderHistory);

        return "/default/orderHistory";
    }


    @RequestMapping("/thong-tin-user")
    public String thongTin(Model model,
                           HttpServletRequest request) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "/default/information";
    }

    @RequestMapping("/chi-tiet-don-hang/{id}")
    public String chiTiet(Model model,
                          HttpServletRequest request,
                          @PathVariable("id") Long id) {
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        List<OrderDetailDTO> list = orderDetailRepoDTO.showOrderDetail(id);
        model.addAttribute("details", list);
        return "default/orderDetailUser";
    }

    @RequestMapping("/thay-doi-mat-khau")
    public String pass(HttpSession session,
                       Model model){
        List<Type_product> showMenu = typeProductRepository.findAll();
        model.addAttribute("menus", showMenu);
        return "default/changePass";
    }

    @RequestMapping(value = "/change-pass", method = RequestMethod.POST)
    public String changePassword(@RequestParam(value = "old-password", required = false) String old,
                                 @RequestParam(value = "new-password", required = false) String neww,
                                 @RequestParam(value = "confirm-password", required = false) String confirm,
                                 HttpSession session,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {

        Object object = session.getAttribute("user");
        Customer customer = (Customer) object;
        String oldPass = customer.getCus_password();
        Long id = customer.getCus_id();
        if (checkPassword(old, oldPass)){
            if (!neww.equals(confirm)){
                redirectAttributes.addFlashAttribute("msg", "Mật khẩu không trùng khớp!");
                return "redirect:/thay-doi-mat-khau";
            } else {
                String newPass = encryptPassword(confirm);
                customerRepository.updatePassword(newPass, id);
                session.removeAttribute("user");
                return "redirect:/login";
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", "Sai mật khẩu hiện tại!");
            return "redirect:/thay-doi-mat-khau";
        }
//        return "/default/changePass";
    }




    public static String encryptPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
