package org.sujalkhot.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {

	public AppiumDriverLocalService service;
	AppiumDriver driver;

	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\sujal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		return service;
	}

	// it similar functionality in android and ios
	public Double getFormattedAmount(String amountString) {
		Double price = Double.parseDouble(amountString.substring(1));
		return price;
	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		// System.getProperty("user.dir") +
		// "\\src\\test\\java\\org\\sujalkhot\\textData\\eCommerse.json"
		// Convert JSON file content to String
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		// Convert JSON String to Java Object
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
	}

	public String getScreenShotPath(String testCaseName, AppiumDriver driver) throws IOException {

		try {
			File source = driver.getScreenshotAs(OutputType.FILE); // capture screenshot
			String destinationFile = System.getProperty("user.dir") + "//reports" + testCaseName + ".png"; // this path
																											// screenshot
																											// will
																											// copied
			FileUtils.copyFile(source, new File(destinationFile)); // copy screenShot in destination file
			return destinationFile;
		} catch (Exception e) {
			System.out.println("Screenshot capture failed: " + e.getMessage());
			return "";
		}
	}
}
