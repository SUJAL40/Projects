package sujalkhot;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class WifiName extends BaseTest {

	@Test
	public void WifiSettingsName() throws MalformedURLException, URISyntaxException, InterruptedException {

		
		
		//Actual automation
		
		driver.findElement(AppiumBy.accessibilityId("Preference")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
//		driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='WiFi settings']")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		
		String alertTitle = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertEquals(alertTitle,"WiFi settings");
		driver.findElement(By.id("android:id/edit")).sendKeys("Sujal Wifi");
		driver.findElement(By.id("android:id/button1")).click();
		
//		Thread.sleep(5000);
		
		//set Wifi Name

		
	}
}
