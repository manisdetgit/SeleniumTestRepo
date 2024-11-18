package com.excel.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriteUtils {
	private static Workbook wbook;
	private static Sheet sheet;
	private static int row_num;

	// open excel file, workbook sheet
	public static void init() {
		try {
			// open excel file
			FileInputStream fis = new FileInputStream(".\\testdata.xlsx");

			// create workbook
			wbook = new XSSFWorkbook(fis);

			// create sheet
			sheet = wbook.getSheetAt(0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeTCResult(String testcaseid, String tcresult, String message) {
		Row row = sheet.createRow(row_num++);
		
		Cell tcid_cell = row.createCell(0,CellType.STRING);
		tcid_cell.setCellValue(testcaseid);
		
		Cell tcresult_cell = row.createCell(1,CellType.STRING);
		tcresult_cell.setCellValue(tcresult);
		
		Cell message_cell = row.createCell(2,CellType.STRING);
		message_cell.setCellValue(message);
	}
	
	public static void generateExcel() {
		
		try {
			FileOutputStream fos = new FileOutputStream("./testreport.xlsx");
			wbook.write(fos);
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
