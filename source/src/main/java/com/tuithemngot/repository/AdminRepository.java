package com.tuithemngot.repository;

import com.tuithemngot.model.Admin;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {
    @Autowired 
    JdbcTemplate aDB;
    
    class AdminRowMapper implements RowMapper<Admin> {
    	 
    	@Override
    	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Admin admin = new Admin();
            admin.setId(rs.getInt("id"));
            admin.setUsername(rs.getString("username"));
            admin.setPassword(rs.getString("password"));
            return admin;
        }
    }
    public List<Admin> findAll() {
    	try {
    		return aDB.query("select * from admin", new AdminRowMapper());
    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    
}
