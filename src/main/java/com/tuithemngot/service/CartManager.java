package com.tuithemngot.service;

import com.tuithemngot.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class CartManager {
    private static final String SESSION_STRING_SHOPPING_CART = "gioHang";

    //phuong thuc lay ve gio hang
    public Cart getCart(HttpSession session){
        Cart cart = (Cart)session.getAttribute(SESSION_STRING_SHOPPING_CART);
        if (cart == null){
            cart = new Cart();
        }
        return cart;
    }

    // thiet lap 1 gio hang
    public void setCart(HttpSession session, Cart cart){
        session.setAttribute(SESSION_STRING_SHOPPING_CART, cart);
    }

    // xoa 1 gio hang
    public void removeCart(HttpSession session){
        session.removeAttribute(SESSION_STRING_SHOPPING_CART);
    }
}
