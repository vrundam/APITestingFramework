package com.stripe.api.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.stripe.api.API.CreateCustomerAPI;
import com.stripe.api.commonUtiles.BaseClass;
import com.stripe.api.commonUtiles.DataProviderUtiles;
import com.stripe.api.commonUtiles.PropertiesUtiles;
import com.stripe.api.commonUtiles.TestListeners;

import org.testng.Assert;

import io.restassured.response.Response;
//import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Hashtable;

	
public class CretaeCustomerTest extends BaseClass {
	
	//SoftAssert sa = new SoftAssert();
	
	
	
	
	@Test(dataProviderClass=DataProviderUtiles.class,dataProvider="testdata", priority=1)
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String,String> data ) throws IOException {
		
		Response response =  CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
		
		TestListeners.testReport.get().info(data.toString());
		
		response.prettyPrint();
		System.out.println("Code Status: " + response.statusCode());
		
		//sa.assertEquals(response.statusCode(), 200, "Code is not 200");
		Assert.assertEquals(response.statusCode(), 200);
		
	}  
	
	
	@Test(dataProviderClass=DataProviderUtiles.class,dataProvider="testdata",priority=2)
	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String,String> data) throws IOException {
		
		Response response =  CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
			
		TestListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		System.out.println("Code Status: " + response.statusCode());
		
	//sa.assertEquals(response.statusCode(), 200, "Cose is not 200");
	
	Assert.assertEquals(response.statusCode(), 200, "Status code is not 200, getting status code as "+response.statusCode());
		
		
	}   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
