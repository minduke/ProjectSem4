package com.tuithemngot.model;

public class Product {

    private Long pro_id;


    private String pro_name;


    private String pro_image;


    private float import_price;


    private float pro_price;


    private String pro_spec;


    private Long type_id;

    private String type_name;

    private String pro_status;

    public Product() {
    }

    public Product(Long pro_id, String pro_name, String pro_image, float import_price, float pro_price, String pro_spec, Long type_id, String type_name, String pro_status) {
        this.pro_id = pro_id;
        this.pro_name = pro_name;
        this.pro_image = pro_image;
        this.import_price = import_price;
        this.pro_price = pro_price;
        this.pro_spec = pro_spec;
        this.type_id = type_id;
        this.type_name = type_name;
        this.pro_status = pro_status;
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

    public String getPro_image() {
        return pro_image;
    }

    public void setPro_image(String pro_image) {
        this.pro_image = pro_image;
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

    public String getPro_spec() {
        return pro_spec;
    }

    public void setPro_spec(String pro_spec) {
        this.pro_spec = pro_spec;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getPro_status() {
        return pro_status;
    }

    public void setPro_status(String pro_status) {
        this.pro_status = pro_status;
    }
}
