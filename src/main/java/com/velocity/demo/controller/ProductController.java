package com.velocity.demo.controller;

import java.util.List;

import com.velocity.demo.dto.ProductDTO;
import com.velocity.demo.dto.Response;
import com.velocity.demo.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.velocity.demo.service.ProductService;

@RestController()
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping()
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok().body(productService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
		ProductDTO productDTO = productService.findById(id);
		if (productDTO == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(productDTO);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok().body(productService.addProduct(productDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
		var updatedProduct = productService.updateProduct(id, productDTO);
		if (updatedProduct == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(updatedProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteProduct(@PathVariable int id) {
		boolean deleted = productService.deleteProduct(id);
		if (!deleted) {
			return ResponseEntity.notFound().build();
		}
		Response response = new Response();
		response.setMessage("Successfully deleted the product");
		return ResponseEntity.ok().body(response);
	}
	
	
}
