package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class day2 {

	@Test
	public void Demo1() {
		System.out.println("Hello TestNG.... Day-2");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test .....");
	}

//	@Parameters({ "URL", "TOPURL" })
//	@Test
//	public void Demo2(String url, String mainUrl) {
//		System.out.println("Hello TestNG.... Day-2 " + url + " " + mainUrl);
//	}

	@Test(dataProvider = "getData") // here we catching two values
	public void Demo3(String userName, String pwd) {
		System.out.println("Hello TestNG.... Day-2 " + userName);

		System.out.println("Hello TestNG.... Day-2 " + pwd);
	}

	@DataProvider
	public Object[][] getData() {

		// how many time we need to run test that many rows
		// how many parameter we need to pass at each test that define column
		// userName and password
		Object[][] data = new Object[3][2];
		data[0][0] = "sujal";
		data[1][0] = "sk";
		data[2][0] = "ssk";

		data[0][1] = "123";
		data[1][1] = "1234";
		data[2][1] = "1238";

		return data;
	}

	@org.testng.annotations.BeforeSuite
	public void BeforeSuite() {
		System.out.println("Before Suite");
	}
}
