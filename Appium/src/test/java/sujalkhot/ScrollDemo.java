package sujalkhot;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

public class ScrollDemo extends BaseTest {

	@Test
	public void ScrollDemoTest() {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		// 2 types of scrolling

		// Where to scroll is known prior
//		driver.findElement(
//				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

		// No idea from which element to scroll
		// all ImmutableMap are in key value pairs
		scrollToEndAction();

	}
}
