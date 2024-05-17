package com.velocity.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import com.velocity.demo.dto.ProductDTO;
import com.velocity.demo.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.velocity.demo.model.Product;
import com.velocity.demo.repository.ProductRepository;

@Service
public class ProductService implements IProductService {

	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<ProductDTO> findAll(){
		return productRepository.findAll().stream()
				.map(productMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDTO findById(int id) {
		if (productRepository.existsById(id)) {
			return productMapper.toDto(productRepository.findById(id).get());
		} else {
			return null;
		}
	}

	@Override
	public ProductDTO addProduct(ProductDTO productDTO) {
		Product product = productMapper.toEntity(productDTO);
		Product savedProduct = productRepository.save(product);
		return productMapper.toDto(savedProduct);
	}

	@Override
	public ProductDTO updateProduct(int id, ProductDTO productDTO) {
		if (productRepository.existsById(id)) {
			productDTO.setId(id);
			Product product = productMapper.toEntity(productDTO);
			Product updatedProduct = productRepository.save(product);
			return productMapper.toDto(updatedProduct);
		} else {
			return null;
		}
	}

	@Override
	public boolean deleteProduct(int id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
    }


}
