package com.tuithemngot.repository;

import com.tuithemngot.model.ImportDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ImportDetailRepository {
    @Autowired
    JdbcTemplate impDetailRepo;

    class ImportDetailRowMapper implements RowMapper<ImportDetail>{

        @Override
        public ImportDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            ImportDetail impDetail = new ImportDetail();
            impDetail.setStt(rs.getInt("stt"));
            impDetail.setImport_detail_id(rs.getLong("import_detail_id"));
            impDetail.setImport_id(rs.getLong("import_id"));
            impDetail.setPro_id(rs.getInt("pro_id"));
            impDetail.setPro_name(rs.getString("pro_name"));
            impDetail.setImport_price(rs.getFloat("import_price"));
            impDetail.setQuantity(rs.getInt("quantity"));
            impDetail.setImport_detail_total(rs.getFloat("import_detail_total"));
            return impDetail;
        }
    }

    public List<ImportDetail> findAll(){
        try {
            return impDetailRepo.query("select * from import_detail", new ImportDetailRowMapper());
        }catch (Exception e){
            System.out.println("Something wrong in import_detail");
        }
        return null;
    }

    public List<ImportDetail> findById(Long id){
        try {
            return impDetailRepo.query("select row_number() over (order by d.import_detail_id) as 'stt', *\n" +
                    "from import_detail d inner join products p on d.pro_id = p.pro_id\n" +
                    "where d.import_id = ?", new ImportDetailRowMapper(), new Object[]{id});
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
