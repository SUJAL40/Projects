package sujalkhot;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Miscell_Rotation_Action extends BaseTest {

	@Test
	public void WifiSettingsName() throws MalformedURLException, URISyntaxException, InterruptedException {

		// Actual automation
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();

		// Rotate device into landscape
		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);

//		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();

		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle, "WiFi settings");

		// copy paste
		// copy to clickboard - paste it clipboard
		driver.setClipboardText("Sujal Wifi");

		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.findElement(By.id("android:id/button1")).click();

		DeviceRotation Portrate = new DeviceRotation(0, 0, 0);
		driver.rotate(Portrate);

		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));

		Thread.sleep(5000);

		// set Wifi Name

	}
}
