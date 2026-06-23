package org.sujalkhot.TestUtils;

import java.io.IOException;

import org.sujalkhot.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	AppiumDriver driver;

	@Override
	public void onTestStart(ITestResult result) {

		// result is hold all information about test which are running
		System.out.println("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed: " + result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed: " + result.getName());
		// if test is fail get error why test is fail
		test.fail(result.getThrowable());

		try {
			driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			// using above code we can get any field inside our test is running like
			// totalSum , DisplaySum etc
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Could not get driver from test instance: " + e.getMessage());

		}

		// ✅ Take screenshot only if driver is alive
		if (driver != null) {
			try {
				String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), driver);
				test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
			} catch (IOException e) {
				System.out.println("Screenshot attach failed: " + e.getMessage());
			} catch (Exception e) {
				// ✅ Catches crash when UiAutomator2 process is dead
				System.out.println("Screenshot skipped - driver session crashed: " + e.getMessage());
				test.info("Screenshot not available - driver crashed during test");
			}
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed But Within Success Percentage: " + result.getName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("Test Failed Due To Timeout: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Execution Started");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Execution Finished");
		extent.flush();
	}

}
