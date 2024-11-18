package com.excel.utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.params.provider.Arguments;

//Newly added line
public class ExcelReadUtils {

	private static Workbook wbook;
	private static Sheet sheet;

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

	// read test data for Contact form
	public static Stream<Arguments> readContactForm(){
		
		List<Arguments> args = new ArrayList<>();
		//featch number of rows
		Row row = sheet.getRow(0);
		Cell cell = row.getCell(0);
		int no_of_test_rows  =(int) cell.getNumericCellValue();
		//iterate above number of times and put each set of Test data into a list
		for(int i=1;i<(no_of_test_rows+1);i++) {
			row = sheet.getRow(i);
			Cell tcase_id_cell = row.getCell(0);
			String tcase_id = tcase_id_cell.getStringCellValue();
			
			
			Cell name_cell = row.getCell(1);
			String name = name_cell.getStringCellValue();
			
			Cell email_cell = row.getCell(2);
			String email = email_cell.getStringCellValue();
			
			Cell details_cell = row.getCell(3);
			String details = details_cell.getStringCellValue();
			
			args.add(Arguments.of(tcase_id,name,email,details));
			
					
			
			
		}
		//convert above List to Stream,
		//and return Stream
		
		return args.stream();
		
		
		
	}
}
