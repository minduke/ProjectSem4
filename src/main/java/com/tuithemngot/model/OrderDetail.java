package com.tuithemngot.model;


public class OrderDetail {

    private Long detail_id;


    private Long order_id;


    private Long pro_id;


    private float import_price;


    private float pro_price;


    private int quantity;


    private float detail_total;

    public OrderDetail() {
    }

    public OrderDetail(Long detail_id, Long order_id, Long pro_id, float import_price, float pro_price, int quantity, float detail_total) {
        this.detail_id = detail_id;
        this.order_id = order_id;
        this.pro_id = pro_id;
        this.import_price = import_price;
        this.pro_price = pro_price;
        this.quantity = quantity;
        this.detail_total = detail_total;
    }

    public Long getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(Long detail_id) {
        this.detail_id = detail_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getPro_id() {
        return pro_id;
    }

    public void setPro_id(Long pro_id) {
        this.pro_id = pro_id;
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

    public float getDetail_total() {
        return detail_total;
    }

    public void setDetail_total(float detail_total) {
        this.detail_total = detail_total;
    }
}
