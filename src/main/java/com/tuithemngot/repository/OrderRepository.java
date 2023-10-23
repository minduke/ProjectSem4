package com.tuithemngot.repository;

import com.tuithemngot.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate orderDB;

    class OrderRowMapper implements RowMapper<Order>{

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order orders = new Order();
            orders.setOrder_id(rs.getLong("order_id"));
            orders.setOrder_date(rs.getString("order_date"));
            orders.setCus_id(rs.getInt("cus_id"));
            orders.setOrder_total(rs.getFloat("order_total"));
            orders.setStatus(rs.getString("status"));
            orders.setOrder_receiver(rs.getString("order_receiver"));
            orders.setOrder_delivery_address(rs.getString("order_delivery_address"));
            orders.setOrder_phone_receiver(rs.getString("order_phone_receiver"));
            orders.setOrder_note(rs.getString("order_note"));
            return orders;
        }
    }

    public List<Order> findAll(){
        try{
            return orderDB.query("select * from orders", new OrderRowMapper());
        }catch (Exception e){
            System.out.println("Something wrong in Orders");
        }
        return null;
    }
}
