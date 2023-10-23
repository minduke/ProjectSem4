package com.tuithemngot.model;

public class ImportDetail {

    private int stt;

    private Long import_detail_id;


    private Long import_id;


    private int pro_id;

    private String pro_name;

    private float import_price;


    private int quantity;


    private float import_detail_total;

    public ImportDetail() {
    }

    public ImportDetail(int stt, Long import_detail_id, Long import_id, int pro_id, String pro_name, float import_price, int quantity, float import_detail_total) {
        this.stt = stt;
        this.import_detail_id = import_detail_id;
        this.import_id = import_id;
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.import_price = import_price;
        this.quantity = quantity;
        this.import_detail_total = import_detail_total;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Long getImport_detail_id() {
        return import_detail_id;
    }

    public void setImport_detail_id(Long import_detail_id) {
        this.import_detail_id = import_detail_id;
    }

    public Long getImport_id() {
        return import_id;
    }

    public void setImport_id(Long import_id) {
        this.import_id = import_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getImport_detail_total() {
        return import_detail_total;
    }

    public void setImport_detail_total(float import_detail_total) {
        this.import_detail_total = import_detail_total;
    }
}
