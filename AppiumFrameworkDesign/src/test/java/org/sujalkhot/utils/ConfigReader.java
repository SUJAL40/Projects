package org.sujalkhot.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	static Properties prop;

	static {

		try {

			prop = new Properties();

			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "//config.properties");

			prop.load(fis);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static String get(String key) {

		return prop.getProperty(key);

	}

}
