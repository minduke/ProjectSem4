package com.tuithemngot.repository;

import com.tuithemngot.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FeedbackRepository {
    @Autowired
    JdbcTemplate feedbackDB;
    class FeedbackRowMapper implements RowMapper<Feedback>{

        @Override
        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feedback feedback = new Feedback();
            feedback.setFeedback_id(rs.getLong("feedback_id"));
            feedback.setFeedback_rate(rs.getString("feedback_rate"));
            feedback.setFeedback_create_at(rs.getString("feedback_create_at"));
            feedback.setFeedback_content(rs.getString("feedbabck_content"));
            feedback.setOrder_id(rs.getInt("order_id"));
            return feedback;
        }
    }

    public List<Feedback> findAll(){
        try {
            return feedbackDB.query("select * from feedback", new FeedbackRowMapper());
        }catch (Exception e){
            System.out.println("Lỗi gì đó ở feedback");
        }
        return null;
    }
}
