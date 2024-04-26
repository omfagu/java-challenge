package com.challenge.shopping.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @Test
    public void testAddProductToCart() throws Exception {
        Long customerId = 4L;
        Long productId = 1L;
        int quantity = 2;

        mockMvc.perform(post("/api/carts/" + customerId + "/addProduct")
                        .param("productId", String.valueOf(productId))
                        .param("quantity", String.valueOf(quantity)))
                .andExpect(status().isOk());
}

    @Test
    public void testRemoveProductFromCart() throws Exception {
        Long customerId = 4L;
        Long productId = 1L;

        mockMvc.perform(delete("/api/carts/" + customerId + "/removeProduct")
                        .param("productId", String.valueOf(productId)))
                .andExpect(status().isOk());
    }
}
