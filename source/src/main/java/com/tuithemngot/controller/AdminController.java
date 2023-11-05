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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // admin home (show order with status = đang giao)
    @Autowired
    OrderRepoDTO orderRepoDTO;

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

    @Autowired
    OrderDetailRepoDTO orderDetailRepoDTO;
    @RequestMapping("/order-detail/{id}")
    public String showOrderById(@PathVariable("id") Long id, Model model){
        List<OrderDetailDTO> orderDetailDTO = orderDetailRepoDTO.showOrderDetail(id);
        model.addAttribute("details", orderDetailDTO);
        return "/admin/showDetail";
    }

    // customer
    @Autowired
    private CustomerRepository cusRepo;

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




    // product
    @Autowired
    private ProductRepository proRep;

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


    // type product
    @Autowired
    Type_product_Repository typeRepo;

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

    // import
    @Autowired
    ImportRepository importRepository;

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String showImport(Model model){
        List<Import> listImport = importRepository.findAll();
        model.addAttribute("imports", listImport);
        return "admin/showImport";
    }

    @Autowired
    ImportDetailRepository importDetailRepository;
    @RequestMapping("/import-detail/{id}")
    public String showImportById(@PathVariable("id") Long id, Model model){
        List<ImportDetail> importDetailList = importDetailRepository.findById(id);
        model.addAttribute("impDetails", importDetailList);
        return "/admin/ImportDetail";
    }

    @Autowired
    CartManager cartManager;
    @RequestMapping(value = "/new-import", method = RequestMethod.GET)
    public String newImport(HttpSession session, Model model){
        List<CartItemImport> items = cartManager.getImportCart(session).getItems();
        model.addAttribute("items", items);
        return "/admin/showCartImport";
    }

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

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
}
