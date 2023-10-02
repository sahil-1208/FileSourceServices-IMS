package com.multiple.datasource.service;


import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multiple.datasource.entity.product.ProductDetails;
import com.multiple.datasource.repository.product.ProductRepo;
import com.multiple.datasource.excelconnect.ProductExcel;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	public void save (MultipartFile file) {
			try {
				List<ProductDetails> listProduct = ProductExcel.convertExcelToListOfProduct(file.getInputStream());
				productRepo.saveAll(listProduct);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
	}
	

	public List<ProductDetails> getAllproductDetails() {
		return productRepo.findAll();
	}
	
}

