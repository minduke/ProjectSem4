package com.tuithemngot.repository.repositoryDTO;

import com.tuithemngot.dto.OrderDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class OrderDetailRepoDTO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class OrderDetailDTORowMapper implements RowMapper<OrderDetailDTO>{

        @Override
        public OrderDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setStt(rs.getInt("stt"));
            orderDetailDTO.setOrder_id(rs.getLong("order_id"));
            orderDetailDTO.setPro_name(rs.getString("pro_name"));
            orderDetailDTO.setImport_price(rs.getFloat("import_price"));
            orderDetailDTO.setPro_price(rs.getFloat("pro_price"));
            orderDetailDTO.setQuantity(rs.getInt("quantity"));
            orderDetailDTO.setSubTotal(rs.getFloat("detail_total"));
            orderDetailDTO.setTotal(rs.getFloat("order_total"));
            return orderDetailDTO;
        }
    }

    public List<OrderDetailDTO> showOrderDetail(Long id){
        try {
            return jdbcTemplate.query("exec sp_show_order_by_id ?", new OrderDetailDTORowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
