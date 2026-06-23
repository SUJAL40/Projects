package sujalkhot;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_4_Hybrid extends BaseTest {

	@Test
	public void FillForm() throws InterruptedException {

		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Sujal Khot");

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

		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
//		driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();	//this and above line is same
		// now after we perform add to cart text changes to added to cart so here only
		// one text will present as add to cart that why
		driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

		// but here id of product is same in cart page and product page , so we need to
		// stop until page is move
		// product page to cart page

		// Here we need to write explicit wait
		// for this we required selenium support we get form mvn repository and store in
		// pom.xml

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"),
				"text", "Cart"));

		List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
		int countProduct = productPrices.size();

		double totalSum = 0;

		for (int i = 0; i < countProduct; i++) {
			String amountString = productPrices.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum += price;
		}

		String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
		double displayFomattedSum = getFormattedAmount(displaySum);

		Assert.assertEquals(totalSum, displayFomattedSum);

		WebElement ele = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
		longPressAction(ele);

		driver.findElement(By.id("android:id/button1")).click();

		driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();

		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(6000);

		// Hybrid - google page
		// So, Here we need to context switch to work hybrid environment

		Set<String> contextHandles = driver.getContextHandles();

		for (String s : contextHandles) {
			System.out.println(s);
		}

		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		//to execute anything in chrome driver we need chrome driver
		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		//now this driver is on webview switch to native app to perform operation on native app
		driver.context("NATIVE_APP");
		

	}
}
