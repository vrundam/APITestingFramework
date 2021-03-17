package com.stripe.api.commonUtiles;

import org.json.JSONObject;

public class TestUtils {
	
	public static boolean jsonHasKey(String json, String key) {
		
		JSONObject jsonObject = new JSONObject(json);
		TestListeners.testReport.get().info("Validating the presence of key; "+key);
		
		return jsonObject.has(key);
	}

	public static String getJsonValue(String json, String key) {
		
		JSONObject jsonObject = new JSONObject(json);
		TestListeners.testReport.get().info("Validating the value  of key; "+key);
		
		return jsonObject.getString(key).toString();
		
	}
}
