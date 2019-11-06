package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.Utilities.Readconfig;
public class BaseClass {
	Readconfig readConfig=new Readconfig();
	public String baseURL=readConfig.getApplicationURL();
	public String username=readConfig.getUsername();
	public String password=readConfig.getPassword();
	public String expText="Guru99 Bank Manager HomePage";
	public  WebDriver driver;
	public static Logger logger;
	@Parameters("browser")
	@BeforeClass
	public void  setUp(String br) {
	logger=Logger.getLogger("ebanking");
	PropertyConfigurator.configure("Log4j.properties");
	if(br.equals("chrome")){
	System.setProperty("webdriver.chrome.driver", readConfig.getChropath()); 
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	
	}else if(br.equals("firefox")){
		System.getProperty("webdriver.gecko.driver", readConfig.getFirepath());
		driver=new FirefoxDriver();
	  }
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(baseURL);
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	public String randomString() {
		String generatedString=RandomStringUtils.randomAlphabetic(8);
		return generatedString;
	
	}
	public static String randomNumber() {
		String generatedString2=RandomStringUtils.randomNumeric(4);
		return generatedString2;
}
}
