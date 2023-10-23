package com.tuithemngot.repository.repositoryDTO;

import com.tuithemngot.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderRepoDTO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class OrderDTORowMapper implements RowMapper<OrderDTO>{

        @Override
        public OrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setStt(rs.getInt("stt"));
            orderDTO.setOrder_id(rs.getLong("order_id"));
            orderDTO.setOrder_receiver(rs.getString("order_receiver"));
            orderDTO.setOrder_delivery_address(rs.getString("order_delivery_address"));
            orderDTO.setOrder_phone_receiver(rs.getString("order_phone_receiver"));
            orderDTO.setOrder_date(rs.getString("order_date"));
            orderDTO.setTotal(rs.getFloat("order_total"));
            orderDTO.setStatus(rs.getString("status"));
            return orderDTO;
        }
    }

    public List<OrderDTO> showOrder(){
        try {
            return jdbcTemplate.query("select row_number() over (order by order_id) as 'stt', * from orders", new OrderDTORowMapper());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
