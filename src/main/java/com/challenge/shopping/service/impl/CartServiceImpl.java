package com.challenge.shopping.service.impl;

import com.challenge.shopping.entity.Cart;
import com.challenge.shopping.entity.Product;
import com.challenge.shopping.repository.CartRepository;
import com.challenge.shopping.service.CartService;
import com.challenge.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public Cart getCartByCustomerId(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found for customer with ID: " + customerId));
    }

    @Override
    public Cart updateCart(Long customerId, Cart cartDetails) {
        Cart cart = getCartByCustomerId(customerId);
        return cartRepository.save(cart);
    }

    @Override
    public void emptyCart(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        cart.getCartItems().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

    @Override
    public Cart addProductToCart(Long customerId, Long productId, int quantity) {
        Cart cart = getCartByCustomerId(customerId);
        Product product = productService.getProductById(productId);
        cart.addItem(product, quantity);
        return cartRepository.save(cart);
    }


    @Override
    public Cart removeProductFromCart(Long customerId, Long productId) {
        Cart cart = getCartByCustomerId(customerId);
        Product product = productService.getProductById(productId);
        cart.removeItem(product);
        return cartRepository.save(cart);
    }
}
