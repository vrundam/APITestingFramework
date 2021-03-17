package com.stripe.api.testcases;

//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;

import com.stripe.api.API.CreateCustomerAPI;
import com.stripe.api.API.DeleteCustomerAPI;
import com.stripe.api.commonUtiles.BaseClass;
import com.stripe.api.commonUtiles.DataProviderUtiles;
//import com.stripe.api.commonUtiles.PropertiesUtiles;
import com.stripe.api.commonUtiles.TestListeners;
import com.stripe.api.commonUtiles.TestUtils;

import org.json.JSONObject;
import org.testng.Assert;

import io.restassured.response.Response;
//import junit.framework.Assert;

//import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Hashtable;

	
public class DeleteCustomerTest extends BaseClass {
	
	//SoftAssert sa = new SoftAssert();
	
	
	
	
	@Test(dataProviderClass=DataProviderUtiles.class,dataProvider="testdata",priority=3)
	public void deleteCustomer(Hashtable<String,String> data ) throws IOException {
		
		Response response =  DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);
		
		TestListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		System.out.println("Code Status: " + response.statusCode());
		
		//sa.assertEquals(response.statusCode(), 200, "Code is not 200");
		Assert.assertEquals(response.statusCode(), 200);
		
		/*JSONObject jsonObject = new JSONObject(response.toString());
		System.out.println(jsonObject.has("id"));
		Assert.assertTrue(jsonObject.has("id"), "ID key is not present"); */
		
		System.out.println("Presence check for Deleted key: "+ TestUtils.jsonHasKey(response.asString(), "deleted"));
		Assert.assertTrue(TestUtils.jsonHasKey(response.asString(), "id"), "ID key is not present");
		
		/*String actualID = jsonObject.get("id").toString();
		System.out.println(actualID);
		Assert.assertEquals(actualID, data.get("id"), "ID not matching"); */
		
		String actualID = TestUtils.getJsonValue(response.asString(), "id");
		System.out.println(actualID);
		Assert.assertEquals(actualID, data.get("id"), "ID not matching");
	}  
	
	
}
