package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static String CONFIG_FILE_PATH = ".\\src\\test\\resources\\config.properties";
	private static Properties properties = new Properties();
	
	static {
		try(FileInputStream file = new FileInputStream(CONFIG_FILE_PATH)){
		    properties.load(file);
		} catch(IOException e) {
		    e.printStackTrace();
		}
		}
	
	// String value — you already wrote this correctly!
	public static String getPropertyAsString(String key) {
	    return properties.getProperty(key);
	}

	// Int value — just convert String to int
	public static int getPropertyAsInt(String key) {
	    return Integer.parseInt(properties.getProperty(key));
	}
}
