package com.tuithemngot.model;

public class Order {

    private Long order_id;


    private String order_date;


    private int cus_id;


    private float order_total;


    private String status;


    private String order_receiver;


    private String order_delivery_address;


    private String order_phone_receiver;


    private String order_note;

    public Order() {
    }

    public Order(Long order_id, String order_date, int cus_id, float order_total, String status, String order_receiver, String order_delivery_address, String order_phone_receiver, String order_note) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.cus_id = cus_id;
        this.order_total = order_total;
        this.status = status;
        this.order_receiver = order_receiver;
        this.order_delivery_address = order_delivery_address;
        this.order_phone_receiver = order_phone_receiver;
        this.order_note = order_note;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public float getOrder_total() {
        return order_total;
    }

    public void setOrder_total(float order_total) {
        this.order_total = order_total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_receiver() {
        return order_receiver;
    }

    public void setOrder_receiver(String order_receiver) {
        this.order_receiver = order_receiver;
    }

    public String getOrder_delivery_address() {
        return order_delivery_address;
    }

    public void setOrder_delivery_address(String order_delivery_address) {
        this.order_delivery_address = order_delivery_address;
    }

    public String getOrder_phone_receiver() {
        return order_phone_receiver;
    }

    public void setOrder_phone_receiver(String order_phone_receiver) {
        this.order_phone_receiver = order_phone_receiver;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }
}
