package com.tuithemngot.model;


public class Type_product {

    private int stt;

    private Long type_id;


    private String type_name;

    public Type_product() {

    }

    public Type_product(int stt, Long type_id, String type_name){
        this.stt = stt;
        this.type_id = type_id;
        this.type_name = type_name;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
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
}
