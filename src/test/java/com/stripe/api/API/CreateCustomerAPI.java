package com.stripe.api.API;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Hashtable;

import com.stripe.api.commonUtiles.BaseClass;

import io.restassured.response.Response;

public class CreateCustomerAPI extends BaseClass {
	
	public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(Hashtable<String,String> data) throws IOException {
		
		Response response =  given().auth().basic(config.getProperty("validSecretKey"), "")
				.formParam("name",data.get("name"))
				.formParam("email",data.get("email"))
				.formParam("description", data.get("description"))
				.post(config.getProperty("endPoint"));  
		
		return response;
		
	}
	
	public static Response sendPostRequestToCreateCustomerAPIWithInValidAuthKey(Hashtable<String,String> data) throws IOException {
		
		Response response =  given().auth().basic(config.getProperty("inValidSecretKey"), "")
				.formParam("name",data.get("name"))
				.formParam("email",data.get("email"))
				.formParam("description", data.get("description"))
				.post(config.getProperty("endPoint"));
		
		return response;
	}

}
