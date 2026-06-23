package org.sujalkhot.pageObject.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.sujalkhot.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		// this is used to initialize elements in class
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;

	// driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;

	// driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"))
	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;

	@AndroidFindBy(className = "android.widget.CheckBox")
	public WebElement checkBox;

	public List<WebElement> getProductList() {
		return productList;
	}

	public double getProductsSum() {
		int count = productList.size();
		double totalSum = 0;

		for (int i = 0; i < count; i++) {
			String amountString = productList.get(i).getText();
			Double price = getFormattedAmount(amountString);
			totalSum += price;
		}

		return totalSum;
	}

	public Double getTotalAmountDisplyed() {
		return getFormattedAmount(totalAmount.getText());
	}

	public void acceptTermCondition() {
		longPressAction(terms);
		acceptButton.click();
	}

	public Double getFormattedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1));
		return price;
	}

	public void submitOrder() {
		checkBox.click();
		proceed.click();
	}

}
