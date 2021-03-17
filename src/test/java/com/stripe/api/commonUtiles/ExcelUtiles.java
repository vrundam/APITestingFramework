package com.stripe.api.commonUtiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtiles {
	
	
	//public static String excelFilePath = "src/test/resources/testdata.xlsx";
	public static String excelFilePath;
	public static  FileInputStream fis = null;
	public static  FileOutputStream fileOut =null;
	
	private static Workbook workbook = null;
	private static  Sheet sheet = null;
	private static Row row   =null;
	private static Cell cell = null;
	
	public ExcelUtiles(String excelFilePath)  {
		
		this.excelFilePath = excelFilePath;
	/*	try {
		fis = new FileInputStream(excelFilePath);
		fis.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}  */
				
		//fis = new FileInputStream(excelFilePath);
		//workbook = WorkbookFactory.create(fis);
		//sheet = workbook.getSheet(sheetName);
		//fis.close();
		
	}
	
	
public static Object[][] fetchDataFromSheet(String sheetName) throws EncryptedDocumentException, IOException {
		
		
		
		DataFormatter fmt = new DataFormatter();
		int ri=0, ci=0;
		
		fis = new FileInputStream(excelFilePath);
		 workbook = WorkbookFactory.create(fis);  
		sheet = workbook.getSheet(sheetName);  
		int totalRows = sheet.getLastRowNum();
		int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells();
		
		Object[][] obj = new Object[totalRows-1][totalColumns];
		for(int i=1; i<=totalRows-1; i++,ri++) {
			ci=0;
			System.out.println("i::  "+ i +", ri:: "+ ri);
			for(int j=0; j<totalColumns; j++,ci++) {
				System.out.println("j::  "+j +",  ci:: "+ci);
				obj[ri][ci] = fmt.formatCellValue(sheet.getRow(i).getCell(j)).toString();
				System.out.println("Data::   "+obj[ri][ci]);
			}
		}
		
		return obj;
				
		

	}

public static void writeExcel(String sheetName, int rowNum, int cellNum, String cellData) throws EncryptedDocumentException, IOException {

	
	fis = new FileInputStream(excelFilePath);		
	// Create WorkBook object
		 workbook = WorkbookFactory.create(fis);
		sheet = workbook.getSheet(sheetName);
			
				
	// Point to specific row		
			 row = sheet.getRow(rowNum);
			
	// Write data to first row first cell (in RAM)
			row.createCell(cellNum).setCellValue(cellData);
	
					
	// Create fso file for the excel
			FileOutputStream fso = new FileOutputStream(excelFilePath);
			workbook.write(fso);
			
			workbook.close();
			fso.close();
}


}
