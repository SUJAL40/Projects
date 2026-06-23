package sujalkhot;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileBrowserTest extends BrowserBaseTest {

	@Test
	public void browserTest() throws InterruptedException {

//		driver.get("http://google.com");
//		System.out.println(driver.getTitle());
//		driver.findElement(By.name("q")).sendKeys("rahul shetty academy");
//		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);

		driver.get("https://rahulshettyacademy.com/angularAppdemo/");

		driver.findElement(By.xpath("//button[@aria-label='Toggle navigation']")).click();
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("a[routerlink*='products']")).click();
		Thread.sleep(3000); // wait for Angular to render products

		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)", "");
		Thread.sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Devops']")));

		String text = driver.findElement(By.xpath("//a[text()='Devops']")).getText();
		System.out.println("Found: " + text);
		Assert.assertEquals(text, "Devops");
	}
}
