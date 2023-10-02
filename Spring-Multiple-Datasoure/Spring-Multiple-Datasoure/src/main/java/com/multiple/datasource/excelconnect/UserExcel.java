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

import com.multiple.datasource.entity.user.UserDetails;
import com.multiple.datasource.enums.Role;

public class UserExcel {
	public static boolean checkExcelFormat(MultipartFile file) {
		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	public static List<UserDetails> convertExcelToListOfUser(InputStream is){
		List<UserDetails> userDetailsData = new ArrayList<UserDetails>();
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("user");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext()) {
					Row row = iterator.next();
					if(rowNumber==0) {
						rowNumber++;
						continue;
					}
					UserDetails userDetails = new UserDetails();
					Iterator<Cell> cells = row.iterator();
					int cid=0;
					while(cells.hasNext()) {
						Cell cell = cells.next();
						
						switch(cid) {
							case 0: userDetails.setUserId((long)cell.getNumericCellValue());break;
							case 1: userDetails.setPhone((long)cell.getNumericCellValue());break;
							case 2: userDetails.setEmail(cell.getStringCellValue());break;
							case 3: userDetails.setPassword(cell.getStringCellValue());break;
							case 4: userDetails.setRole(Role.valueOf(cell.getStringCellValue()));break;
							 default:
		                            break;
						}
						cid++;
					}
					userDetailsData.add(userDetails);
			}
			return userDetailsData;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userDetailsData;
	}


}
