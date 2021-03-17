package com.stripe.api.commonUtiles;

import java.io.FileInputStream;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseClass {
	
	public static PropertiesUtiles config = new PropertiesUtiles();
	//public  ExcelUtiles excel = new ExcelUtiles("src/test/resources/testdata.xlsx");
	
	private FileInputStream fis;
	public static ExcelReader excel = new ExcelReader("src/test/resources/testdata.xlsx");
	
	@BeforeSuite
	public void setUp() throws IOException {
		
		System.out.println("Before Suite");
		RestAssured.baseURI = config.getProperty("baseURI");
		RestAssured.basePath = config.getProperty("basePath");
		
	}

	@AfterSuite
	public void tearDown() {
		System.out.println("After Suite");
	}
}
