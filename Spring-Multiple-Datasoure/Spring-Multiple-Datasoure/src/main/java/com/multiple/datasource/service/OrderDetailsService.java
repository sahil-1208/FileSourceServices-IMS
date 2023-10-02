package com.multiple.datasource.service;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.multiple.datasource.entity.orderDetails.OrderDetails;

import com.multiple.datasource.excelconnect.OrderDetailsExcel;
import com.multiple.datasource.repository.orderDetails.OrderDetailsRepo;

@Service
public class OrderDetailsService {
	
	@Autowired
	private OrderDetailsRepo orderDetailsRepo;
	
	
	public void save (MultipartFile file) {
			try {
				List<OrderDetails> listOrderDetails = OrderDetailsExcel.convertExcelToListOfOrderDetails(file.getInputStream());
				orderDetailsRepo.saveAll(listOrderDetails);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
	}

	public List<OrderDetails> getAllOrderDetails() {
		return orderDetailsRepo.findAll();
	}
}
