package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LogInTest001 extends BaseClass {
	
	@Test
	public void logInTest() throws IOException {
		logger.info("URL is Opened");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered UserName");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickSubmit();
		if (driver.getTitle().equals(expText)) {
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
		}else {
			captureScreen(driver, "logInTest");
			Assert.assertTrue(false);
			logger.info("Login Test Failled");
		}
	}

}
