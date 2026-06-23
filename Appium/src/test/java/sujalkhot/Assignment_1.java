package sujalkhot;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class Assignment_1 extends BaseTest {

	// This Assignment is done on App/Alert Dialogs
//	@Test
//	public void SimpleOkClick() throws InterruptedException {
//		driver.findElement(AppiumBy.accessibilityId("App")).click();
//		driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
//		driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with a message")).click();
//		driver.findElement(By.id("android:id/button1")).click();
//
//		Thread.sleep(2000);
//	}

	@Test
	public void OkClickWithLongText() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("App")).click();
		driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
		driver.findElement(AppiumBy.accessibilityId("OK Cancel dialog with ultra long message")).click();
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" + ".scrollToEnd(3)"));
		driver.findElement(By.id("android:id/button1")).click();

		Thread.sleep(2000);
	}
}
