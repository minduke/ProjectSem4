package com.tuithemngot.dto;

public class OrderDetailDTO {
    private int stt;

    private Long order_id;

    private String pro_name;

    private float import_price;

    private float pro_price;

    private int quantity;

    private float subTotal;

    private float total;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int stt, Long order_id, String pro_name, float import_price, float pro_price, int quantity, float subTotal, float total) {
        this.stt = stt;
        this.order_id = order_id;
        this.pro_name = pro_name;
        this.import_price = import_price;
        this.pro_price = pro_price;
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.total = total;
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

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public float getImport_price() {
        return import_price;
    }

    public void setImport_price(float import_price) {
        this.import_price = import_price;
    }

    public float getPro_price() {
        return pro_price;
    }

    public void setPro_price(float pro_price) {
        this.pro_price = pro_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
