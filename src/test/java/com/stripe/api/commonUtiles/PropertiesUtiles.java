
package com.stripe.api.commonUtiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtiles {
	
	static FileInputStream fis;
	static String propertyFilePath = "src/test/resources/config.Properties";
	static Properties propertyObj = new Properties();
	
	
	static {
		try {
			fis = new FileInputStream(propertyFilePath);
			propertyObj.load(fis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public  String  getProperty(String key) throws IOException {
		
		if(propertyObj.getProperty(key)==null) {
			throw new FileNotFoundException("Improper Property");
		}
		
		return  propertyObj.getProperty(key);
	}

}
