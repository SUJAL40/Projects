package org.sujalkhot.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {	//This class is used to read values from a config.properties file.

	static Properties property;		//Because it is static, only one copy exists for the entire application.

	static {		// A static block executes only once when the class is loaded.
				// Without a static block, every call would read the file again:

		try {

			property = new Properties();

			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "//config.properties");	//	Used to read data from a file.
			
			// System.getProperty("user.dir") : Returns the current project directory.

			property.load(fis);	//Load Properties - Now all values are stored in memory.

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static String get(String key) {

		return property.getProperty(key);	//This method returns the value associated with a key.

	}

}
