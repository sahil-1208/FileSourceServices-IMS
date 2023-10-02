package com.multiple.datasource.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.multiple.datasource.entity.customer.CustomerDetails;
import com.multiple.datasource.entity.orderDetails.OrderDetails;
import com.multiple.datasource.entity.product.ProductDetails;
import com.multiple.datasource.entity.user.UserDetails;
import com.multiple.datasource.excelconnect.CustomerExcel;
import com.multiple.datasource.excelconnect.OrderDetailsExcel;
import com.multiple.datasource.excelconnect.ProductExcel;
import com.multiple.datasource.excelconnect.SupplierExcel;
import com.multiple.datasource.excelconnect.UserExcel;
import com.multiple.datasource.service.CustomerService;
import com.multiple.datasource.service.OrderDetailsService;
import com.multiple.datasource.service.ProductService;
import com.multiple.datasource.service.SupplierService;
import com.multiple.datasource.service.UserService;

@RestController
@RequestMapping(value = "/multidb")
public class AppController {
	
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/customer")
	public ResponseEntity<?> uploadCustomerExcel(@RequestParam("file") MultipartFile file) {
		
		if(CustomerExcel.checkExcelFormat(file)) {
			customerService.save(file);
			return ResponseEntity.ok(Map.of("message","file is upload"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uplod excel file");
		
	}
	
	@GetMapping("/customer/find-all")
	public List<CustomerDetails> getAllDataFromDataBase(){
		return customerService.getAllcustomer();
	}
	
	@PostMapping("/supplier")
	public ResponseEntity<?> uploadSupplierExcel(@RequestParam("file") MultipartFile file) {
		
		if(SupplierExcel.checkExcelFormat(file)) {
			supplierService.save(file);
			return ResponseEntity.ok(Map.of("message","file is upload"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uplod excel file");
		
	}
	
	@GetMapping("/supplier/find-all")
	public List<CustomerDetails> getAllsupplierDataFromDataBase(){
		return customerService.getAllcustomer();
	}
	
	@PostMapping("/order-details")
	public ResponseEntity<?> uploadOrderDetailsExcel(@RequestParam("file") MultipartFile file) {
		
		if(OrderDetailsExcel.checkExcelFormat(file)) {
			orderDetailsService.save(file);
			return ResponseEntity.ok(Map.of("message","file is upload"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uplod excel file");
		
	}
	
	@GetMapping("/order-details/find-all")
	public List<OrderDetails> getAllOrderDetailsFromDataBase(){
		return orderDetailsService.getAllOrderDetails();
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> uploadPoductDetailsExcel(@RequestParam("file") MultipartFile file) {
		
		if(ProductExcel.checkExcelFormat(file)) {
			productService.save(file);
			return ResponseEntity.ok(Map.of("message","file is upload"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uplod excel file");
		
	}
	
	@GetMapping("/product/find-all")
	public List<ProductDetails> getAllProductFromDataBase(){
		return productService.getAllproductDetails();
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> uploadUserDetailsExcel(@RequestParam("file") MultipartFile file) {
		
		if(UserExcel.checkExcelFormat(file)) {
			userService.save(file);
			return ResponseEntity.ok(Map.of("message","file is upload"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("uplod excel file");
		
	}
	
	@GetMapping("/user/find-all")
	public List<UserDetails> getAllUserFromDataBase(){
		return userService.getAllUserDetails();
	}
	
	

}
