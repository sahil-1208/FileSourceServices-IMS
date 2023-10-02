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

import com.multiple.datasource.entity.orderDetails.OrderDetails;
import com.multiple.datasource.enums.PaymentMode;

public class OrderDetailsExcel {

	public static boolean checkExcelFormat(MultipartFile file) {
		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}

	public static List<OrderDetails> convertExcelToListOfOrderDetails(InputStream is) {
		List<OrderDetails> orderDetailsData = new ArrayList<OrderDetails>();

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheet("order details");
			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				OrderDetails orderDetails = new OrderDetails();
				Iterator<Cell> cells = row.iterator();
				int cid = 0;
				while (cells.hasNext()) {
					Cell cell = cells.next();
					switch (cid) {
					case 0:
						orderDetails.setOrderId((long) cell.getNumericCellValue());
						break;
					case 1:
						orderDetails.setProductId((long) cell.getNumericCellValue());
						break;
					case 2:
						orderDetails.setProductName(cell.getStringCellValue());
						break;
					case 3:
						orderDetails.setPaymentMode(PaymentMode.valueOf(cell.getStringCellValue()));
						break;
					case 4:
						orderDetails.setProductPrice((long) cell.getNumericCellValue());
						break;
					case 5:
						orderDetails.setQuantity((long) cell.getNumericCellValue());
						break;
					case 6:
						orderDetails.setDiscount((long) cell.getNumericCellValue());
						break;
					case 7:
						orderDetails.setTotal((double) cell.getNumericCellValue());
						break;
					case 8:
						orderDetails.setDate(cell.getLocalDateTimeCellValue().toLocalDate());
						break;
					case 9:
						orderDetails.setBillNumber(cell.getStringCellValue());
						break;
					case 10:
						orderDetails.setCustomerId((long) cell.getNumericCellValue());
						break;
					default:
						break;
					}
					cid++;
				}
				orderDetailsData.add(orderDetails);
			}
			return orderDetailsData;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orderDetailsData;
	}
}
