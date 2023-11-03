package com.tuithemngot.model;

import java.util.ArrayList;
import java.util.List;

public class CartImport {

    private final List<CartItemImport> items;

    private double total;

    public CartImport(){
        items = new ArrayList<CartItemImport>();
        total = 0;
    }

    public CartItemImport getItem(Product product){
        for (CartItemImport item : items){
            if (item != null && item.getProduct() != null && product != null && product.getPro_id() != null && item.getProduct().getPro_id().equals(product.getPro_id())){
                return item;
            }
        }
        return null;
    }

    public List<CartItemImport> getItems(){
        return items;
    }

    public int getItemCount(){
        return items.size();
    }

    public void addItem(CartItemImport cartItemImport){
        addItem(cartItemImport.getProduct(), cartItemImport.getQuantity());
    }

    public void addItem(Product product, int quantity){
        CartItemImport itemImport = getItem(product);
        if (itemImport != null){
            itemImport.setQuantity(itemImport.getQuantity() + quantity);
        } else {
            itemImport = new CartItemImport(product);
            itemImport.setQuantity(1);
            items.add(itemImport);
        }
    }

    public void updateItem(Product product, int quantity, float import_price){
        CartItemImport itemImport = getItem(product);
        if (itemImport != null){
            itemImport.setQuantity(quantity);
            product.setImport_price(import_price);
        }
    }

    public void removeItem(Product product){
        CartItemImport itemImport = getItem(product);
        if (itemImport != null) {
            items.remove(itemImport);
        }
    }

    public void clear(){
        items.clear();
        total = 0;
    }

    public double getTotal(){
        total = 0;
        for (CartItemImport item : items){
            total += item.getSubTotal();
        }
        return total;
    }
}
