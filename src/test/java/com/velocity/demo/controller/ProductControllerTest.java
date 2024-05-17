package com.velocity.demo.controller;

import com.velocity.demo.dto.ProductDTO;
import com.velocity.demo.model.Product;
import com.velocity.demo.service.IProductService;
import com.velocity.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("test");
        when(productService.findAll()).thenReturn(Collections.singletonList(productDTO));
        ResponseEntity<?> responseEntity = productController.getAll();
        verify(productService, times(1)).findAll();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getProductById() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("test");
        when(productService.findById(1)).thenReturn(productDTO);
        ResponseEntity<?> responseEntity = productController.getProduct(1);
        verify(productService, times(1)).findById(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void addProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("test");
        when(productService.addProduct(any())).thenReturn(productDTO);
        ResponseEntity<?> responseEntity = productController.addProduct(productDTO);
        verify(productService, times(1)).addProduct(productDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("test");
        when(productService.updateProduct(1, productDTO)).thenReturn(productDTO);
        ResponseEntity<?> responseEntity = productController.updateProduct(1, productDTO);
        verify(productService, times(1)).updateProduct(1, productDTO);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateProduct_NotFound() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("test");
        when(productService.updateProduct(1, productDTO)).thenReturn(null);
        ResponseEntity<?> responseEntity = productController.updateProduct(1, productDTO);
        verify(productService, times(1)).updateProduct(1, productDTO);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void deleteProduct() {
        when(productService.deleteProduct(1)).thenReturn(true);
        ResponseEntity<?> responseEntity = productController.deleteProduct(1);
        verify(productService, times(1)).deleteProduct(1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
