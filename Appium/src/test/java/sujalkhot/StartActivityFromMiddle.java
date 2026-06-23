package sujalkhot;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class StartActivityFromMiddle extends BaseTest {

	@Test
	public void startActivitFromMiddle() throws MalformedURLException, URISyntaxException, InterruptedException {

		// adb shell dumpsys window | find "mCurrentFoucs" to start from any position
		Activity activity = new Activity("io.appium.android.apis",
				"io.appium.android.apis.preference.PreferenceDependencies");

		((JavascriptExecutor) driver).executeScript("mobile: startActivity",
				ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
		
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
