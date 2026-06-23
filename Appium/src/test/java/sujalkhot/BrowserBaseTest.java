package sujalkhot;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BrowserBaseTest {

	public AppiumDriverLocalService service;
	public AndroidDriver driver;

	@BeforeClass
	public void ConfigureAppium() throws MalformedURLException, URISyntaxException {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\sujal\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();

		UiAutomator2Options option = new UiAutomator2Options();
		option.setDeviceName("Myemulator");
//		option.setApp(
//				"C:\\Users\\sujal\\OneDrive\\Documents\\Projects\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		option.setChromedriverExecutable(
				"C:\\Users\\sujal\\OneDrive\\Desktop\\Testing\\Resources\\resources\\chromedriver-win64\\chromedriver.exe");
		// here we are using chrome browser
		option.setCapability("browserName", "Chrome");

		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), option);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public Double getFormattedAmount(String amountString) {
		Double price = Double.parseDouble(amountString.substring(1));
		return price;
	}

	@AfterClass
	public void tearDown() {
		driver.quit(); // quite driver
		service.stop(); // stop Service
	}
}
