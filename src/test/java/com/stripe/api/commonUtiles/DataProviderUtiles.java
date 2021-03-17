package com.stripe.api.commonUtiles;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class DataProviderUtiles extends BaseClass {

	
	@DataProvider(name="createCustomerData",parallel=true)
	public Object[][] getData(Method m){
		
		
		String sheetName = m.getName();
				
		int rows = excel.getRowCount(sheetName);
		int columns = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][columns];
		
		for(int rowNum=2; rowNum<=rows; rowNum++) {
			for(int colNum=0; colNum<columns; colNum++ ) {
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		return data;
		
	} 
	
	@DataProvider(name="testdata")
	public Object[][] readDataFromExcel(Method m) throws IOException {
		
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata.xlsx");
		
		//int rows = excel.getRowCount(Constants.SHEET_NAME);
		int rows = excel.getRowCount(config.getProperty("testDataSheetName"));
		System.out.println("Total rows are:  "+ rows);
		
		
		//String testName = "validateCreateCustomerAPIWithValidSecretKey";
		String testName = m.getName();
		System.out.println("Test Case Name is " + testName);
		
		
		//Find testcase starts
		int testCaseRowNum = 1;
		
		for(testCaseRowNum=1; testCaseRowNum<=rows; testCaseRowNum++) {
			String testCaseName = excel.getCellData(config.getProperty("testDataSheetName"), 0, testCaseRowNum);
			
			if(testCaseName.equalsIgnoreCase(testName)) 
				break;
						
		}
		System.out.println("Test case starts from rownumber: " + testCaseRowNum);
		
		
		//Find total rows in test case
		int dataStartRowNum = testCaseRowNum+2;
		
		int testRows = 0;
		while(!excel.getCellData(config.getProperty("testDataSheetName"), 0, dataStartRowNum+testRows).equals("")) {
			testRows++;
		}
		System.out.println("Total rows of data are: "+ testRows);
		
		
		//Checking total columns in testcase
		int colStartColNum = testCaseRowNum+1;
		int testCols = 0;
		while(!excel.getCellData(config.getProperty("testDataSheetName"), testCols, colStartColNum).equals("")) {
			testCols++;
		}
		
		System.out.println("Total columns "+ testCols);
	
		//Object[][] data = new Object[testRows][testCols];
		// If we use HastTable to put all columns value in excel then make it column num as 1 to match with hashtable argument
		Object[][] data = new Object[testRows][1];
		//Print data
		int i=0;
		for(int rNum=dataStartRowNum; rNum<(dataStartRowNum+testRows);rNum++) {
			
			Hashtable<String,String> table = new Hashtable<String,String>();
			
			for(int cNum=0; cNum<testCols; cNum++) {
				//System.out.println(excel.getCellData(Constants.SHEET_NAME, cNum, rNum));
				//data[rNum-dataStartRowNum][cNum] = excel.getCellData(Constants.SHEET_NAME, cNum, rNum);
				
				String testData = excel.getCellData(config.getProperty("testDataSheetName"), cNum, rNum);
				String colName = excel.getCellData(config.getProperty("testDataSheetName"), cNum, colStartColNum);
				
				table.put(colName, testData);
			}
			data[i][0] = table;
			i++;
		}
		
		return data;
	}
}
