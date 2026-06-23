package org.sujalkhot;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sujalkhot.TestUtils.AndroidBaseTest;
import org.sujalkhot.pageObject.android.CartPage;
import org.sujalkhot.pageObject.android.FormPage;
import org.sujalkhot.pageObject.android.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {

	@BeforeMethod(alwaysRun=true)
	public void preSetUp() {

		formPage.SetActivity();

	}

	@Test(dataProvider = "getData", groups = { "Smoke" })
	public void FillForm(HashMap<String, String> input) throws InterruptedException {

		FormPage formPage = new FormPage(driver);
		formPage.setName(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountry(input.get("country"));
		ProductCatalogue productCatalogue = formPage.submitForm();

		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		CartPage cartPage = productCatalogue.goToCartPage();

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplyed();

		Assert.assertEquals(totalSum, displayFormattedSum);
		cartPage.acceptTermCondition();
		cartPage.submitOrder();

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\org\\sujalkhot\\textData\\eCommerse.json");
//		return new Object[][] { { "sujal khot", "male", "Argentina" },{ "ssk", "female", "Australia" } };
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
