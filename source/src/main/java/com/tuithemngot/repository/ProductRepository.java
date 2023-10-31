package com.tuithemngot.repository;

import com.tuithemngot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate pDB;

    class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product pro = new Product();
            pro.setPro_id(rs.getLong("pro_id"));
            pro.setPro_name(rs.getString("pro_name"));
            pro.setPro_image(rs.getString("pro_image"));
            pro.setImport_price(rs.getFloat("import_price"));
            pro.setPro_price(rs.getFloat("pro_price"));
            pro.setPro_spec(rs.getString("pro_spec"));
            pro.setType_id(rs.getLong("type_id"));
            pro.setType_name(rs.getString("type_name"));
            pro.setPro_status(rs.getString("pro_status"));
            return pro;
        }
    }

    public List<Product> findAll(){
        try {
            return pDB.query("select * from products p inner join type_product t on p.type_id = t.type_id", new ProductRowMapper());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findByFilter(Long id){
        try {
            return pDB.query("select * from products p inner join type_product t on p.type_id = t.type_id where p.type_id = ?", new ProductRowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int insertProduct(Product pro){
        try{
            return pDB.update("insert into Products (pro_name, pro_image, import_price, pro_price, pro_spec, type_id) values (?, ?, ?, ?, ?, ?)",
                    new Object[]{pro.getPro_name(), pro.getPro_image(), pro.getImport_price(), pro.getPro_price(), pro.getPro_spec(), pro.getType_id()});
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteById(Long id){
        try {
            return pDB.update("delete products where pro_id = ?", new Object[]{id});
        }catch (Exception e){
            e.printStackTrace();

        }
        return 0;
    }

    public Product findById(Long id){
        try {
            return pDB.queryForObject("select * from products p inner join type_product t on p.type_id = t.type_id where pro_id = ?", new ProductRowMapper(), new Object[]{id});
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int update(Product pro){
        try {
            return pDB.update("update products set pro_name = ?, import_price = ?, pro_price = ?, pro_spec = ?, type_id = ? where pro_id = ?",
                    new Object[]{pro.getPro_name(), pro.getImport_price(), pro.getPro_price(), pro.getPro_spec(), pro.getType_id(), pro.getPro_id()});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int updateProStatus(String update, Long id){
        try {
            return pDB.update("update products set pro_status = ? where pro_id = ?", new Object[]{update, id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
