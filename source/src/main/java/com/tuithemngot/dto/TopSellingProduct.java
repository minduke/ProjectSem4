package com.tuithemngot.dto;

public class TopSellingProduct {
    private int stt;

    private Long pro_id;

    private String pro_name;

    private int total_quantity_sold;

    private int total_revenue;

    public TopSellingProduct() {
    }

    public TopSellingProduct(int stt, Long pro_id, String pro_name, int total_quantity_sold, int total_revenue) {
        this.stt = stt;
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.total_quantity_sold = total_quantity_sold;
        this.total_revenue = total_revenue;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Long getPro_id() {
        return pro_id;
    }

    public void setPro_id(Long pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public int getTotal_quantity_sold() {
        return total_quantity_sold;
    }

    public void setTotal_quantity_sold(int total_quantity_sold) {
        this.total_quantity_sold = total_quantity_sold;
    }

    public int getTotal_revenue() {
        return total_revenue;
    }

    public void setTotal_revenue(int total_revenue) {
        this.total_revenue = total_revenue;
    }
}
