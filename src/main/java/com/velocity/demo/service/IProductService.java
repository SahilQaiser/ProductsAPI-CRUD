package com.velocity.demo.service;

import com.velocity.demo.dto.ProductDTO;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();
    ProductDTO findById(int id);
    ProductDTO addProduct(ProductDTO productDTO);
    ProductDTO updateProduct(int id, ProductDTO productDTO);
    boolean deleteProduct(int id);
}
