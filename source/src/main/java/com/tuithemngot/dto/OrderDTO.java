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

    private String order_note;

    private Long transaction_no;

    private String transaction_date;

    private String payment_methods;

    public OrderDTO() {
    }

    public OrderDTO(int stt,
                    Long order_id,
                    String order_receiver,
                    String order_delivery_address,
                    String order_phone_receiver,
                    String order_date,
                    float total,
                    String status,
                    String order_note,
                    Long transaction_no,
                    String transaction_date,
                    String payment_methods) {
        this.stt = stt;
        this.order_id = order_id;
        this.order_receiver = order_receiver;
        this.order_delivery_address = order_delivery_address;
        this.order_phone_receiver = order_phone_receiver;
        this.order_date = order_date;
        this.total = total;
        this.status = status;
        this.order_note = order_note;
        this.transaction_no = transaction_no;
        this.transaction_date = transaction_date;
        this.payment_methods = payment_methods;
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

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public Long getTransaction_no() {
        return transaction_no;
    }

    public void setTransaction_no(Long transaction_no) {
        this.transaction_no = transaction_no;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getPayment_methods() {
        return payment_methods;
    }

    public void setPayment_methods(String payment_methods) {
        this.payment_methods = payment_methods;
    }
}
