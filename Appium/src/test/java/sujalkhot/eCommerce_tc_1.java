package sujalkhot;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class eCommerce_tc_1 extends BaseTest {

	@Test
	public void FillForm() throws InterruptedException {

//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sujal Khot");

		// to hide keyboard
		driver.hideKeyboard();

		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("android:id/text1")).click();

		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
		driver.findElement(
				By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Argentina\"]"))
				.click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

		// get first toast message present on screen
		// in toast what coming on screen like Please Enter your name that is in
		// attribute name
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");

		Thread.sleep(3000);

	}
}
