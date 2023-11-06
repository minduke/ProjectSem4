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
            cus.setStt(rs.getInt("stt"));
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
            return cDB.query("exec sp_show_customer", new CustomerRowMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> findBySearch(String keyword){
        try {
            return cDB.query("exec sp_show_cus_by_name ?", new CustomerRowMapper(), new Object[]{keyword});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int insertCustomer(Customer customer){
        try {
            return cDB.update("insert into customers (cus_name, cus_address, cus_phone, cus_email, cus_gender, cus_username, cus_password) values (?, ?, ?, ?, ?, ?, ?)",
                    new Object[]{customer.getCus_name(), customer.getCus_address(), customer.getCus_phone(), customer.getCus_email(), customer.getCus_gender(), customer.getCus_username(), customer.getCus_password()});
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public Customer findByLogin(String username, String password){
        try {
            return cDB.queryForObject("select row_number() over (order by cus_id) 'stt', * from customers where cus_username = ? and cus_password = ?", new CustomerRowMapper(), new Object[]{username, password});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Customer findByUsername(String username){
        try {
            return cDB.queryForObject("exec sp_find_cus_username ?", new CustomerRowMapper(), new Object[]{username});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
