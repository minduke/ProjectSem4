package com.tuithemngot.model;


public class Type_product {

    private int stt;

    private int type_id;


    private String type_name;

    public Type_product() {

    }

    public Type_product(int stt, int type_id, String type_name){
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

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
