package com.multiple.datasource.excelconnect;


import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.multiple.datasource.entity.product.ProductDetails;
import com.multiple.datasource.enums.ProductCategory;
import com.multiple.datasource.enums.ProductStatus;

public class ProductExcel {
	
	public static boolean checkExcelFormat(MultipartFile file) {
		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	public static List<ProductDetails> convertExcelToListOfProduct(InputStream is){
		List<ProductDetails> productDetailsData = new ArrayList<ProductDetails>();
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("product");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext()) {
					Row row = iterator.next();
					if(rowNumber==0) {
						rowNumber++;
						continue;
					}
					ProductDetails productDetails = new ProductDetails();
					Iterator<Cell> cells = row.iterator();
					int cid=0;
					while(cells.hasNext()) {
						Cell cell = cells.next();
						
						switch(cid) {
							case 0: productDetails.setProductId((long)cell.getNumericCellValue());break;
							case 1: productDetails.setProductName(cell.getStringCellValue());break;
							case 2: productDetails.setDescription(cell.getStringCellValue());break;
							case 3: productDetails.setProductPrize((long)cell.getNumericCellValue());break;
							case 4: productDetails.setQuantity((long)cell.getNumericCellValue());break;
							case 5: productDetails.setProductStatus(ProductStatus.valueOf(cell.getStringCellValue()));break;
							case 6: productDetails.setSupplierId((long)cell.getNumericCellValue());break;
							case 7: productDetails.setProductCategory(ProductCategory.valueOf(cell.getStringCellValue()));break;
							 default:
		                            break;
						}
						cid++;
					}
					productDetailsData.add(productDetails);
			}
			return productDetailsData;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return productDetailsData;
	}


}

