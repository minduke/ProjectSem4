package com.tuithemngot.controller;

import com.tuithemngot.dto.OrderDTO;
import com.tuithemngot.dto.OrderDetailDTO;
import com.tuithemngot.model.*;
import com.tuithemngot.repository.*;
import com.tuithemngot.repository.repositoryDTO.OrderDetailRepoDTO;
import com.tuithemngot.repository.repositoryDTO.OrderRepoDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        return "/admin/home";
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
                            @RequestParam("pro_spec") String pro_spec, @RequestParam("type_id") int type_id, MyUploadForm myUploadForm,
                            @ModelAttribute("myUploadForm") MyUploadForm myUploadForm1, @RequestParam("fileDatas") MultipartFile file) {

        product.setPro_name(pro_name);
        product.setPro_image(file.getOriginalFilename());
        product.setImport_price(import_price);
        product.setPro_price(pro_price);
        product.setPro_spec(pro_spec);
        product.setType_id(type_id);
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
    public String showProductByFilter(@PathVariable("id") Long id, Model model){
        List<Product> listFilter = proRep.findByFilter(id);
        model.addAttribute("lists", listFilter);
        return "admin/showProductByType";
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

}
