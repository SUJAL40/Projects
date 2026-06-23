package org.sujalkhot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.opentelemetry.sdk.trace.internal.ExtendedSpanProcessor;

public class ExtendReportsDemo {

	ExtentReports extent;

	@BeforeTest
	public void config() {

		// ExtentReports,ExtentSparkReporter

		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Text Results");

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sujal Khot");
	}

	@Test
	public void initialDemo() {

		ExtentTest test = extent.createTest("Initial Demo");
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\sujal\\OneDrive\\Desktop\\Testing\\Resources\\resources\\chromedriver");

//		Since you're on Selenium 4.39, just remove the System.setProperty line completely.
//		Selenium Manager will auto-detect and download the right chromedriver:

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();
//		test.fail("Result do not match"); // it is used to fail your test by programmatically

		extent.flush();
	}

}
