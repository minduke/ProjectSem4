package com.tuithemngot.repository;

import com.tuithemngot.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate cDB;

    class CustomerRowMapper implements RowMapper<Customer>{

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer cus = new Customer();
            cus.setCus_id(rs.getLong("cus_id"));
            cus.setCus_name(rs.getString("cus_name"));
            cus.setCus_birthday(rs.getString("cus_birthday"));
            cus.setCus_address(rs.getString("cus_address"));
            cus.setCus_phone(rs.getString("cus_phone"));
            cus.setCus_email(rs.getString("cus_email"));
            cus.setCus_gender(rs.getString("cus_gender"));
            cus.setCus_username(rs.getString("cus_username"));
            cus.setCus_password(rs.getString("cus_password"));
            cus.setCus_create_at(rs.getString("cus_create_at"));
            return cus;
        }
    }

    public List<Customer> findAll(){
        try{
            return cDB.query("select * from customers", new CustomerRowMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
