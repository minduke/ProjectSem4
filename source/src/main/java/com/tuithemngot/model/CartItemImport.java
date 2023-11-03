package com.tuithemngot.model;

public class CartItemImport {

    private final Product product;

    private int quantity;

    private double subTotal;

    public CartItemImport(Product product) {
        this.product = product;
        this.quantity = 1;
        this.subTotal = product.getImport_price() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        subTotal = product.getImport_price() * quantity;
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
