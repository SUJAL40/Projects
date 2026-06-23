package test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//rahulonlinetutor@gmail.com

public class day3 {

	@Parameters({ "URL" })
	@Test
	public void Demo1(String url) {
		System.out.println("Login..." + url);
	}

	@Test(dependsOnMethods = { "Demo1", "Demo3" })
	public void Demo2() {
		System.out.println("App Start");
	}

	@Parameters({ "TOPURL" })
	@Test
	public void Demo3(String mainUrl) {
		System.out.println("Hello car2.... Day-3 " + mainUrl);
	}

	@org.testng.annotations.AfterSuite
	public void AfterSuite() {
		System.out.println("After Suite");
	}
}
