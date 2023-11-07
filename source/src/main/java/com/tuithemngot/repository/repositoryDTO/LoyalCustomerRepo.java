package com.tuithemngot.repository.repositoryDTO;

import com.tuithemngot.dto.LoyalCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LoyalCustomerRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class LoyalCustomerRowMapper implements RowMapper<LoyalCustomer>{

        @Override
        public LoyalCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
            LoyalCustomer loyalCustomer = new LoyalCustomer();
            loyalCustomer.setStt(rs.getInt("stt"));
            loyalCustomer.setCus_id(rs.getLong("cus_id"));
            loyalCustomer.setCus_name(rs.getString("cus_name"));
            loyalCustomer.setTotal_orders(rs.getInt("total_orders"));
            loyalCustomer.setTotal_amount_ordered(rs.getDouble("total_amount_ordered"));
            return loyalCustomer;
        }
    }

    public List<LoyalCustomer> showAll(){
        try {
            return jdbcTemplate.query("exec sp_show_loyal_customer", new LoyalCustomerRowMapper());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<LoyalCustomer> showByName(String keyword){
        try {
            return jdbcTemplate.query("exec sp_show_loyal_customer_by_name ?", new LoyalCustomerRowMapper(), new Object[]{keyword});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
