package com.stripe.api.API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Hashtable;

import com.stripe.api.commonUtiles.BaseClass;

import io.restassured.response.Response;

public class DeleteCustomerAPI extends BaseClass{
	
public static Response sendDeleteRequestToDeleteCustomerAPIWithValidID(Hashtable<String,String> data) throws IOException {
		
		Response response =  given().auth().basic(config.getProperty("validSecretKey"), "")
				.delete(config.getProperty("endPoint")+"/"+data.get("id"));  
		
		return response;
		
	}

}
