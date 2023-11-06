package com.tuithemngot.repository;

import com.tuithemngot.model.Import;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ImportRepository {
  @Autowired
    JdbcTemplate iDB;

  class ImportRowMapper implements RowMapper<Import> {

      @Override
      public Import mapRow(ResultSet rs,int rowNum) throws SQLException {
          Import imp = new Import();
          imp.setStt(rs.getInt("stt"));
          imp.setImport_id(rs.getLong("import_id"));
          imp.setImport_date(rs.getString("import_date"));
          imp.setImport_total(rs.getString("import_total"));
          return imp;
      }
  }

  public List<Import> findAll() {
      try{
         return iDB.query("exec sp_show_import", new ImportRowMapper());
      }catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }

  public Import findById(Long id){
      try {
          return iDB.queryForObject("select * from import where id = ?", new ImportRowMapper(), new Object[]{id});
      } catch (Exception e){
          e.printStackTrace();
      }
      return null;
  }

}
