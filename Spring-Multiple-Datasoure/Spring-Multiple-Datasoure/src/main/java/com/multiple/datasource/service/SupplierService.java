package com.multiple.datasource.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.multiple.datasource.entity.supplier.SupplierDetails;
import com.multiple.datasource.excelconnect.SupplierExcel;
import com.multiple.datasource.repository.supplier.SupplierRepo;

@Service
public class SupplierService {
	@Autowired
	private SupplierRepo supplierRepo;
	
	public void save(MultipartFile file) {
		try {
			List<SupplierDetails> listSupplier = SupplierExcel.convertExcelToListOfSupplier(file.getInputStream());
			supplierRepo.saveAll(listSupplier);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<SupplierDetails> getAllSupplier(){
		return supplierRepo.findAll();
	}
}