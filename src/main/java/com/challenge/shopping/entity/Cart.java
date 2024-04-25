package com.challenge.shopping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @OneToOne
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    private double totalPrice;

    public void addItem(Product product, int quantity) {
        CartItem cartItem = new CartItem(product, this, quantity);
        cartItems.add(cartItem);
        totalPrice += product.getPrice() * quantity;
    }

    public void removeItem(Product product) {
        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(product));
        totalPrice -= product.getPrice();
    }

    public void calculateTotalPrice() {
        double total = 0.0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        this.totalPrice = total;
    }

}
