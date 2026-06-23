package sujalkhot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LongPress extends BaseTest {

	@Test
	public void LongPressEvent() throws InterruptedException {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

		// Google it : Appium github gesture
		// Long press Gesture Code
		WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));

		// log press Gesture code is in BaseTest Class
		longPressAction(ele);

		String textAfterLongPress = driver
				.findElement(
						By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Sample menu']"))
				.getText();

		Assert.assertEquals(textAfterLongPress, "Sample menu");

	}
}
