package com.multiple.datasource.service;


import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multiple.datasource.entity.customer.CustomerDetails;
import com.multiple.datasource.excelconnect.CustomerExcel;
import com.multiple.datasource.repository.customer.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	
	public void save(MultipartFile file) {
		try {
			List<CustomerDetails> listCustomer = CustomerExcel.convertExcelToListOfCustomer(file.getInputStream());
			customerRepo.saveAll(listCustomer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<CustomerDetails> getAllcustomer(){
		return customerRepo.findAll();
	}
}
