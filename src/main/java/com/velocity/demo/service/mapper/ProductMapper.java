package com.velocity.demo.service.mapper;

import com.velocity.demo.dto.ProductDTO;
import com.velocity.demo.model.Product;
import com.velocity.demo.model.Supplier;
import com.velocity.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private SupplierRepository supplierRepository;

    public ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setUnitPrice(product.getUnitPrice());
        productDTO.setStockQuantity(product.getStockQuantity());

        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        Product product = new Product();
        product.setName(productDTO.getName());
        if (productDTO.getId() != null) {
            product.setId(productDTO.getId());
        }
        product.setUnitPrice(productDTO.getUnitPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        if (productDTO.getSupplierName() != null) {
            Supplier supplier = supplierRepository.findByName(productDTO.getSupplierName())
                    .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
            product.setSupplier(supplier);
        }

        return product;
    }
}

