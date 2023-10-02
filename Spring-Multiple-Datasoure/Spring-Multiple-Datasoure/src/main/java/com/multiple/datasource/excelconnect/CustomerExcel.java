package com.multiple.datasource.excelconnect;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.multiple.datasource.entity.customer.CustomerDetails;

public class CustomerExcel {
	
	public static boolean checkExcelFormat(MultipartFile file) {
		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	public static List<CustomerDetails> convertExcelToListOfCustomer(InputStream is){
		List<CustomerDetails> customerData = new ArrayList<CustomerDetails>();
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("customer");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext()) {
					Row row = iterator.next();
					if(rowNumber==0) {
						rowNumber++;
						continue;
					}
					CustomerDetails customer = new CustomerDetails();
					Iterator<Cell> cells = row.iterator();
					int cid=0;
					while(cells.hasNext()) {
						Cell cell = cells.next();
						
						switch(cid) {
							case 0: customer.setCustomerId((long)cell.getNumericCellValue());break;
							case 1: customer.setName(cell.getStringCellValue());break;
							case 2: customer.setAddress(cell.getStringCellValue());break;
							case 3: customer.setUserId((long)cell.getNumericCellValue());break;
							 default:
		                            break;
						}
						cid++;
					}
					customerData.add(customer);				
			}
			return customerData;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return customerData;
	}
}
