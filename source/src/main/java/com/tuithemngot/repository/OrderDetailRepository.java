package com.tuithemngot.repository;

import com.tuithemngot.model.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDetailRepository {
    @Autowired
    JdbcTemplate OrderDetailRepo;

    class OrderDetailRowMapper implements RowMapper<OrderDetail>{

        @Override
        public OrderDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setDetail_id(rs.getLong("detail_id"));
            orderDetail.setOrder_id(rs.getLong("order_id"));
            orderDetail.setPro_id(rs.getLong("pro_id"));
            orderDetail.setImport_price(rs.getFloat("import_price"));
            orderDetail.setPro_price(rs.getFloat("pro_price"));
            orderDetail.setQuantity(rs.getInt("quantity"));
            orderDetail.setDetail_total(rs.getFloat("detail_total"));
            return orderDetail;
        }
    }

    public List<OrderDetail> findAll(){
        try{
            return OrderDetailRepo.query("select * from order_detail", new OrderDetailRowMapper());
        }catch (Exception e){
            System.out.println("Something wrong in order_detail");
        }
        return null;
    }
}
