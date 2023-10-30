package com.tuithemngot.repository;

import com.tuithemngot.model.Import;
import com.tuithemngot.model.Product;
import com.tuithemngot.model.Type_product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Type_product_Repository {
    @Autowired
    JdbcTemplate typeDB;


    class Type_productRowMapper implements RowMapper<Type_product> {

        @Override
        public  Type_product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Type_product type = new Type_product();
            type.setStt(rs.getInt("stt"));
            type.setType_id(rs.getLong("type_id"));
            type.setType_name(rs.getString("type_name"));
            return type;
        }

    }
    public List<Type_product> findAll() {
        try{
            return typeDB.query("exec sp_show_type", new Type_productRowMapper());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertType(Type_product type){
        try {
            return typeDB.update("insert into type_product values (?)", new Object[]{type.getType_name()});
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Type_product findById(Long id){
        try {
            return typeDB.queryForObject("select row_number() over (order by type_id) 'stt', * from type_product where type_id = ?", new Type_productRowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
