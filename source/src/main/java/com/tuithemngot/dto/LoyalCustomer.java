package com.tuithemngot.dto;

public class LoyalCustomer {
    private int stt;

    private Long cus_id;

    private String cus_name;

    private int total_orders;

    private double total_amount_ordered;

    public LoyalCustomer() {
    }

    public LoyalCustomer(int stt, Long cus_id, String cus_name, int total_orders, double total_amount_ordered) {
        this.stt = stt;
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.total_orders = total_orders;
        this.total_amount_ordered = total_amount_ordered;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Long getCus_id() {
        return cus_id;
    }

    public void setCus_id(Long cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public int getTotal_orders() {
        return total_orders;
    }

    public void setTotal_orders(int total_orders) {
        this.total_orders = total_orders;
    }

    public double getTotal_amount_ordered() {
        return total_amount_ordered;
    }

    public void setTotal_amount_ordered(double total_amount_ordered) {
        this.total_amount_ordered = total_amount_ordered;
    }
}
