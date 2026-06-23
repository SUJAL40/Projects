package test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class day1 {

	@Parameters({ "URL", "username" })
	@Test
	public void Demo(String url, String userName) {
		System.out.println("Hello TestNG.... Day-1 " + url + " " + userName);
	}

	@AfterTest
	public void AfterTest() {
		System.out.println("After Test.....");
	}

}
