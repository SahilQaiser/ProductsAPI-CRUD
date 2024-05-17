package com.velocity.demo.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column
	private String name;

	@Column(name = "stock_qty")
	private Integer stockQuantity;

	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	@ManyToOne
	private Supplier supplier;

	@Column
	private Double unitPrice;

	@Column
	private Timestamp createdAt;

	@Column
	private Timestamp updatedAt;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Product{" +
				"Id=" + Id +
				", name='" + name + '\'' +
				", stockQuantity=" + stockQuantity +
				", supplier=" + supplier +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
