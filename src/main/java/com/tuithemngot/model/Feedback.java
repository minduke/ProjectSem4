package com.tuithemngot.model;




public class Feedback {

    private Long feedback_id;


    private String feedback_rate;


    private String feedback_create_at;


    private String feedback_content;


    private int order_id;

    public Feedback() {
    }

    public Feedback(Long feedback_id, String feedback_rate, String feedback_create_at, String feedback_content, int order_id) {
        this.feedback_id = feedback_id;
        this.feedback_rate = feedback_rate;
        this.feedback_create_at = feedback_create_at;
        this.feedback_content = feedback_content;
        this.order_id = order_id;
    }

    public Long getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(Long feedback_id) {
        this.feedback_id = feedback_id;
    }

    public String getFeedback_rate() {
        return feedback_rate;
    }

    public void setFeedback_rate(String feedback_rate) {
        this.feedback_rate = feedback_rate;
    }

    public String getFeedback_create_at() {
        return feedback_create_at;
    }

    public void setFeedback_create_at(String feedback_create_at) {
        this.feedback_create_at = feedback_create_at;
    }

    public String getFeedback_content() {
        return feedback_content;
    }

    public void setFeedback_content(String feedback_content) {
        this.feedback_content = feedback_content;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
