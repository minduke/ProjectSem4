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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    OrderRepoDTO orderRepoDTO;

    @Autowired
    OrderDetailRepoDTO orderDetailRepoDTO;

    @Autowired
    private CustomerRepository cusRepo;

    @Autowired
    private ProductRepository proRep;

    @Autowired
    Type_product_Repository typeRepo;

    @Autowired
    ImportRepository importRepository;

    @Autowired
    ImportDetailRepository importDetailRepository;

    @Autowired
    CartManager cartManager;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    AdminRepository adminRepository;

    @RequestMapping("/")
    public String homeAdmin(Model model) {
        List<OrderDTO> orders = orderRepoDTO.showOrder();
        model.addAttribute("orders", orders);
        model.addAttribute("orderStatuses", Arrays.asList("Chờ xác nhận", "Đã xác nhận", "Đang giao", "Hoàn thành", "Huỷ"));
        return "/admin/home";
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    public String updateStatus(@RequestParam(value = "update", required = false) String status, @PathVariable(value = "id", required = false) Long id){
        orderRepoDTO.updateStatus(status, id);
        return "redirect:/admin/";
    }

    @RequestMapping("/date")
    public String orderByDate(Model model, @RequestParam(value = "from", required = false) String from, @RequestParam(value = "to", required = false) String to){
        List<OrderDTO> orders = orderRepoDTO.showOrderByDate(from, to);
        model.addAttribute("orders", orders);
        return "/admin/showOrderByDate";
    }

    @RequestMapping("/month")
    public String orderByMonth(Model model, @RequestParam(value = "month", required = false) String month){
        List<OrderDTO> orders = orderRepoDTO.showOrderByMonth(month);
        model.addAttribute("orders", orders);
        return "/admin/showOrderByMonth";
    }


    @RequestMapping("/order-detail/{id}")
    public String showOrderById(@PathVariable("id") Long id, Model model){
        List<OrderDetailDTO> orderDetailDTO = orderDetailRepoDTO.showOrderDetail(id);
        model.addAttribute("details", orderDetailDTO);
        return "/admin/showDetail";
    }



    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String showCustomer(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Customer> listC;

        if (keyword != null){
            listC = cusRepo.findBySearch(keyword);

        } else {
            listC = cusRepo.findAll();

        }
        model.addAttribute("customers", listC);

        return "/admin/showCustomer";
    }



    @RequestMapping(value = "/insertPro", method = RequestMethod.POST)
    public String insertPro(Product product, @RequestParam("pro_name") String pro_name,
                            @RequestParam("import_price") float import_price, @RequestParam("pro_price") float pro_price,
                            @RequestParam("pro_spec") String pro_spec, MyUploadForm myUploadForm,
                            @ModelAttribute("myUploadForm") MyUploadForm myUploadForm1, @RequestParam("fileDatas") MultipartFile file, HttpServletRequest request) {

        product.setPro_name(pro_name);
        product.setPro_image(file.getOriginalFilename());
        product.setImport_price(import_price);
        product.setPro_price(pro_price);
        product.setPro_spec(pro_spec);
        product.setType_id((Long) request.getSession().getAttribute("type_id"));
        proRep.insertProduct(product);


//         cấu hình đường dẫn gốc để lưu ảnh khi upload
        Path staticPath = Paths.get("src", "main", "resources", "static", "images");
        String temp = staticPath.toString();


//         gán đường dẫn để java hiểu
        File uploadRootDir = new File(temp);

        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        MultipartFile[] fileDatas = myUploadForm.getFileDatas();

//        List<File> uploadedFiles = new ArrayList<File>();

        for (MultipartFile fileData : fileDatas) {

            String originalFileName = fileData.getOriginalFilename();
            File imageSrc = new File(uploadRootDir.getAbsolutePath() + originalFileName);

            try {

                File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + originalFileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileData.getBytes());

                stream.close();

//                    uploadedFiles.add(serverFile);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "redirect:/admin/products";

    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(Model model) {
        MyUploadForm myUploadForm = new MyUploadForm();
        model.addAttribute("myUpload", myUploadForm);
        return "admin/insertProduct";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String showProducts(Model model){
        List<Product> listP = proRep.findAll();
        model.addAttribute("products", listP);
        model.addAttribute("proStatuses", Arrays.asList("Hết hàng", "Còn hàng"));
        return "admin/showProduct";
    }

    @RequestMapping("/delete/{id}")
    public String deletePro(@PathVariable("id") Long id){
        proRep.deleteById(id);
        return "redirect:/admin/products";
    }

    @RequestMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") Long id, Model model){
        Product product = proRep.findById(id);
        model.addAttribute("product", product);
        return "admin/editProduct";
    }

    @RequestMapping("/edit")
    public String saveEdit(Product product){
        proRep.update(product);
        return "redirect:/admin/products";
    }

    @RequestMapping("/list-product/{id}")
    public String showProductByFilter(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        List<Product> listFilter = proRep.findByFilter(id);
        model.addAttribute("lists", listFilter);
        model.addAttribute("proStatuses", Arrays.asList("Hết hàng", "Còn hàng"));
        request.getSession().setAttribute("type_id", id);
        request.getSession().setAttribute("type_name", typeRepo.findById(id).getType_name());
        return "admin/showProductByType";
    }

    @RequestMapping(value = "/updateProStatus/{id}", method = RequestMethod.POST)
    public String updateProStatus(@RequestParam(value = "update", required = false) String update, @PathVariable(value = "id", required = false) Long id){
        proRep.updateProStatus(update, id);
        return "redirect:/admin/products";
    }


    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public String showType(Model model){
        List<Type_product> listType = typeRepo.findAll();
        model.addAttribute("types", listType);
        return "admin/showType";
    }

    @RequestMapping("/addType")
    public String addType(Model model){
        Type_product type = new Type_product();
        model.addAttribute("types", type);
        return "/admin/insertType";
    }

    @RequestMapping(value = "/insertType", method = RequestMethod.POST)
    public String insertType(Type_product typeProduct){
        typeRepo.insertType(typeProduct);
        return "redirect:/admin/type";
    }


    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String showImport(Model model){
        List<Import> listImport = importRepository.findAll();
        model.addAttribute("imports", listImport);
        return "admin/showImport";
    }


    @RequestMapping("/import-detail/{id}")
    public String showImportById(@PathVariable("id") Long id, Model model){
        List<ImportDetail> importDetailList = importDetailRepository.findById(id);
        model.addAttribute("impDetails", importDetailList);
        return "/admin/ImportDetail";
    }


    @RequestMapping(value = "/new-import", method = RequestMethod.GET)
    public String newImport(HttpSession session, Model model){
        List<CartItemImport> items = cartManager.getImportCart(session).getItems();
        model.addAttribute("items", items);
        return "/admin/showCartImport";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginAD() {
        return "/admin/LoginAD";
    }


    @RequestMapping(value = "/chklogin", method = RequestMethod.POST)
    public String chklogin(@RequestParam("usr")String username,@RequestParam("pwd") String password, HttpServletRequest request) {
        Logger log = Logger.getGlobal();
        log.info("Tài khoản: " +username + " <---> " + "Mật khẩu: " + password);
        String query = "select count(*) from admin where username = ? and password = ? ";
        int count = jdbcTemplate.queryForObject(query, Integer.class, username, password);
        if (count == 1){
            request.getSession().setAttribute("myacc", username);
            return "redirect:/admin/";
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("myacc");
        return "redirect:/admin/login";
    }


    @RequestMapping(value = "/save-import", method = RequestMethod.POST)
    public String saveImport(@RequestParam("import_total") double total, HttpSession session){
        try {

            String insertImport = String.format("exec sp_insert_import ?", total);
            jdbcTemplate = new JdbcTemplate(dataSource);
            Long import_id = jdbcTemplate.queryForObject(insertImport, new Object[]{total}, Long.class);
            System.out.println(import_id);
            String sql = "insert into import_detail (import_id, pro_id, import_price, quantity, import_detail_total) values (?, ?, ?, ?, ?)";
            CartImport importDetailList = cartManager.getImportCart(session);
            List<CartItemImport> items = importDetailList.getItems();
            for (CartItemImport item : items) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                double importPrice = product.getImport_price();
                double importDetailTotal = item.getSubTotal();
                jdbcTemplate.update(sql, import_id, product.getPro_id(), importPrice, quantity, importDetailTotal);
            }
            session.removeAttribute("nhapHang");

        } catch (Exception e) {
            if (dataSource instanceof DataSourceTransactionManager) {
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) dataSource;
                TransactionStatus status = transactionManager.getTransaction(null);
                transactionManager.rollback(status);
            }
            e.printStackTrace();
        }
        return "redirect:/admin/";

    }

}
