package com.tuithemngot.model;




public class Customer {

    private int stt;

    private Long cus_id;


    private String cus_name;


    private String cus_birthday;


    private String cus_address;


    private String cus_phone;


    private String cus_email;


    private String cus_gender;


    private String cus_username;


    private String cus_password;


    private String cus_create_at;

    public Customer() {
    }

    public Customer(int stt, Long cus_id, String cus_name, String cus_birthday, String cus_address, String cus_phone, String cus_email, String cus_gender, String cus_username, String cus_password, String cus_create_at) {
        this.stt = stt;
        this.cus_id = cus_id;
        this.cus_name = cus_name;
        this.cus_birthday = cus_birthday;
        this.cus_address = cus_address;
        this.cus_phone = cus_phone;
        this.cus_email = cus_email;
        this.cus_gender = cus_gender;
        this.cus_username = cus_username;
        this.cus_password = cus_password;
        this.cus_create_at = cus_create_at;
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

    public String getCus_birthday() {
        return cus_birthday;
    }

    public void setCus_birthday(String cus_birthday) {
        this.cus_birthday = cus_birthday;
    }

    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public String getCus_gender() {
        return cus_gender;
    }

    public void setCus_gender(String cus_gender) {
        this.cus_gender = cus_gender;
    }

    public String getCus_username() {
        return cus_username;
    }

    public void setCus_username(String cus_username) {
        this.cus_username = cus_username;
    }

    public String getCus_password() {
        return cus_password;
    }

    public void setCus_password(String cus_password) {
        this.cus_password = cus_password;
    }

    public String getCus_create_at() {
        return cus_create_at;
    }

    public void setCus_create_at(String cus_create_at) {
        this.cus_create_at = cus_create_at;
    }
}
