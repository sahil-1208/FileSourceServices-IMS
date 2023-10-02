package com.multiple.datasource.entity.product;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.multiple.datasource.enums.ProductCategory;
import com.multiple.datasource.enums.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductDetails {

	@Id
	@Column(name = "product_id")
	private Long productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "product_price")
	private Long productPrize;
		
	@Column(name = "quantity")
	private Long quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "product_status")
	private ProductStatus productStatus;
	
	@Column(name = "supplier_id")
	private Long supplierId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category")
	private ProductCategory productCategory;
}
