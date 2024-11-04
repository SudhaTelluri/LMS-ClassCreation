package com.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private String filePath;
	private String sheetName;

	// Constructor to initialize the Excel file path and sheet name
	public ExcelReader(String filePath, String sheetName) {
		this.filePath = filePath;
		this.sheetName = sheetName;
	}

	// Method to read all test cases from the specified sheet
	public Map<String, Map<String, String>> readAllTestCases() {
		Map<String, Map<String, String>> allTestCases = new HashMap<>();
		FileInputStream fis = null;
		Workbook workbook = null;

		try {
			// Load the Excel file
			fis = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(fis);

			// Get the specified sheet
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IllegalArgumentException("Sheet '" + sheetName + "' does not exist in the file.");
			}

			// Iterate through each row in the sheet, starting from row 1 to skip headers
			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // Start from row 1 if row 0 is
																					// headers
				Row row = sheet.getRow(rowIndex);
				if (row != null) {
					String testCaseName = row.getCell(0).getStringCellValue(); // Assuming the first column has the test
																				// case names
					Map<String, String> testData = new HashMap<>();
					for (int i = 0; i < row.getLastCellNum(); i++) {
						String header = sheet.getRow(0).getCell(i).getStringCellValue(); // Header as key
						String value = row.getCell(i) != null ? row.getCell(i).toString() : ""; // Cell value as value
						testData.put(header, value);
					}
					allTestCases.put(testCaseName, testData); // Store data in the Map
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return allTestCases;
	}
}
