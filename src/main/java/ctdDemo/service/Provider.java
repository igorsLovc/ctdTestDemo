package ctdDemo.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;



public class Provider {
	
	public static final String SS_PASSWORD_PROP = "service.password";
	private static String propertiesFileName = "application.properties";

	private Properties property = new Properties();
	static Provider provider;
	
	private Provider() {
	}

	
	public static Provider getProvider() {
		if(provider==null) {
			provider = new Provider();
			try {
				provider.property.load(new FileInputStream(getPropertiesFileName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return provider;

	}
	
	public Properties getProperties() {
		return property;
	}
	
	public String getProperty(String prop) {
		return (String) property.get(prop);
	}
	

	public static String getPropertiesFileName() {
		return propertiesFileName;
	}

}
