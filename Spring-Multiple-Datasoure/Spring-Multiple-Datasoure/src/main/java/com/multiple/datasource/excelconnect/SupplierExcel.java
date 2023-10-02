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


import com.multiple.datasource.entity.supplier.SupplierDetails;


public class SupplierExcel {
	public static boolean checkExcelFormat(MultipartFile file) {
		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	public static List<SupplierDetails> convertExcelToListOfSupplier(InputStream is){
		List<SupplierDetails> supplierData = new ArrayList<SupplierDetails>();
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("supplier");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext()) {
					Row row = iterator.next();
					if(rowNumber==0) {
						rowNumber++;
						continue;
					}
					SupplierDetails supplier = new SupplierDetails();
					Iterator<Cell> cells = row.iterator();
					int cid=0;
					while(cells.hasNext()) {
						Cell cell = cells.next();
						
						switch(cid) {
							case 0: supplier.setSupplierId((long)cell.getNumericCellValue());break;
							case 1: supplier.setName(cell.getStringCellValue());break;
							case 2: supplier.setAddress(cell.getStringCellValue());break;
							case 3: supplier.setUserId((long)cell.getNumericCellValue());break;
							 default:
		                            break;
						}
						cid++;
					}
					supplierData.add(supplier);				
			}
			return supplierData;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return supplierData;
	}

}
