package com.tuithemngot.dto;

public class OrderDTO {

    private int stt;

    private Long order_id;

    private String order_receiver;

    private String order_delivery_address;

    private String order_phone_receiver;

    private String order_date;

    private float total;

    private String status;

    public OrderDTO() {
    }

    public OrderDTO(int stt, Long order_id, String order_receiver, String order_delivery_address, String order_phone_receiver, String order_date, float total, String status) {
        this.stt = stt;
        this.order_id = order_id;
        this.order_receiver = order_receiver;
        this.order_delivery_address = order_delivery_address;
        this.order_phone_receiver = order_phone_receiver;
        this.order_date = order_date;
        this.total = total;
        this.status = status;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
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

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }



    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
