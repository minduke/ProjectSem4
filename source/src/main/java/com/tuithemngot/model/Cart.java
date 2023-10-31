package com.tuithemngot.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> items;
    private double total;

    public Cart() {
        items = new ArrayList<CartItem>();
        total = 0;
    }

    public CartItem getItem(Product p){
        for(CartItem item : items){
            if (item != null && item.getProduct() != null && p != null && p.getPro_id() != null && item.getProduct().getPro_id().equals(p.getPro_id())){
                return item;
            }
        }
        return null;
    }

    public List<CartItem> getItems(){
        return items;
    }

    // lấy về số lượng item
    public int getItemCount(){
        return items.size();
    }

    // thêm 1 item
    public void addItem(CartItem item){
        addItem(item.getProduct(), item.getQuantity());
    }

    // thêm item với số lượng cho trước
    public void addItem(Product p, int quantity){
        CartItem item = getItem(p);
        if (item != null){
            item.setQuantity(item.getQuantity() + quantity); // lấy số lượng mới cộng số lượng cũ
        } else { // nếu chưa tồn tại sản phẩm
            item = new CartItem(p); // tạo mới 1 sản phẩm
            item.setQuantity(quantity); // set số lượng
            items.add(item); // thêm vào list các item
        }
    }

    // update sản phẩm
    public void updateItem(Product p, int quantity){
        CartItem item = getItem(p); // lấy về sản phẩm cần update
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    // xoá
    public void removeItem(Product p){
        CartItem item = getItem(p); // lấy về sản phẩm cần update
        if (item != null) {
            items.remove(item);
        }
    }

    // clear
    public void clear(){
        items.clear();
        total = 0;
    }

    // check gio hang co trong khong
    public boolean isEmpty(){
        return items.isEmpty();
    }

    // lay ve tong tien
    public double getTotal(){
        total = 0;
        for (CartItem item : items){
            total += item.getSubTotal();
        }
        return total;
    }
}
