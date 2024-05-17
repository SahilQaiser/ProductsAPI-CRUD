package com.velocity.demo.service;

import com.velocity.demo.dto.ProductDTO;
import com.velocity.demo.model.Product;
import com.velocity.demo.repository.ProductRepository;
import com.velocity.demo.service.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(new Product()));

        productService.findAll();

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        when(productRepository.existsById(1)).thenReturn(true);
        when(productRepository.findById(1)).thenReturn(Optional.of(new Product()));

        productService.findById(1);

        verify(productRepository, times(1)).existsById(1);
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void updateProductById() {
        var productId = 1;
        Product product = new Product();
        product.setId(productId);
        product.setName("test");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName("test");

        when(productRepository.existsById(productId)).thenReturn(true);
        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.toDto(product)).thenReturn(productDTO);

        ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);

        verify(productRepository, times(1)).existsById(productId);
        verify(productMapper, times(1)).toEntity(productDTO);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).toDto(product);
    }

    @Test
    void updateProductById_NotFound() {
        var productId = 1;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName("test");

        when(productRepository.existsById(productId)).thenReturn(false);

        ProductDTO updatedProductDTO = productService.updateProduct(productId, productDTO);

        verify(productRepository, times(1)).existsById(productId);
        assertNull(updatedProductDTO, "ProductDTO should be null when product is not found");

    }


}
