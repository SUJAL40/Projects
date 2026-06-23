package org.sujalkhot.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.sujalkhot.pageObject.android.FormPage;
import org.sujalkhot.utils.AppiumUtils;
import org.sujalkhot.utils.VideoRecorder;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBaseTest extends AppiumUtils {

	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true) // always need to run
	public void ConfigureAppium() throws URISyntaxException, IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\org\\sujalkhot\\resource\\data.properties");
		prop.load(fis);
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress")
				: prop.getProperty("ipAddress");

//		String ipAddress = prop.getProperty("ipAddress");

		String port = prop.getProperty("port");

		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		UiAutomator2Options option = new UiAutomator2Options();
		option.setDeviceName(prop.getProperty("AndroidDeviceName"));
//		option.setApp(
//				"C:\\Users\\sujal\\OneDrive\\Documents\\Projects\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		option.setChromedriverExecutable(
				"C:\\Users\\sujal\\OneDrive\\Desktop\\Testing\\Resources\\resources\\chromedriver-win64\\chromedriver.exe");

		option.setApp(
				System.getProperty("user.dir") + "\\src\\test\\java\\org\\sujalkhot\\resources\\General-Store.apk");

		driver = new AndroidDriver(service.getUrl(), option);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		formPage = new FormPage(driver);
	}

	@BeforeMethod
	public void startVideo() {
		VideoRecorder.startRecording(driver);
	}

	@AfterMethod // TestNG runner engine automatically pass ITestResult object to @AfterMethod
	public void stopVideo(ITestResult result) throws Exception {

		String testName = result.getName();

		String videoPath = VideoRecorder.stopAndSaveVideo(driver, testName);

		// This code checks whether a TestNG test has passed successfully.
		// If it has, it deletes the recorded video file.
		if (result.getStatus() == ITestResult.SUCCESS) {

			File file = new File(videoPath); // Creates a File object representing the video file.

			boolean deleted = file.delete(); // Creates a File object representing the video file.
			
			System.out.println("Deleted: " + deleted);
		}

		else {

			System.out.println("Video saved");
		}

	}

	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
	}

	public void scrollToEndAction() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
		} while (canScrollMore);
	}

	public void swipeAction(WebElement ele, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) ele).getId(), "direction", direction, "percent", 0.75));
	}

	public Double getFormattedAmount(String amountString) {
		Double price = Double.parseDouble(amountString.substring(1));
		return price;
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit(); // quite driver
		service.stop(); // stop Service
	}
}
