package com.challenge.shopping.service.impl;

import com.challenge.shopping.entity.Cart;
import com.challenge.shopping.entity.Product;
import com.challenge.shopping.repository.CartRepository;
import com.challenge.shopping.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceImplTest {

    @Autowired
    private CartServiceImpl cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ProductService productService;

    @org.junit.Test
    @Test
    public void testAddProductToCart() {
        Long customerId = 1L;
        Long productId = 1L;
        int quantity = 1;

        Cart cart = new Cart();
        Product product = new Product();

        when(cartRepository.findByCustomerId(customerId)).thenReturn(Optional.of(cart));
        when(productService.getProductById(productId)).thenReturn(product);

        cartService.addProductToCart(customerId, productId, quantity);

        verify(cartRepository, times(1)).save(cart);
        verify(productService, times(1)).updateProduct(productId, product);
    }

    // Other test methods...
}