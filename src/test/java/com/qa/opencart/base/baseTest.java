package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.driverfactory;
import com.qa.opencart.pages.accountPage;
import com.qa.opencart.pages.loginPage;
import com.qa.opencart.pages.productInfoPage;
import com.qa.opencart.pages.registrationPage;
import com.qa.opencart.pages.searchResultPage;



public class baseTest {
	
	 public WebDriver driver;
	 driverfactory df;
	 public loginPage loginpage;
	 public accountPage accpage;
	 public searchResultPage searchResultsPage;
	 public productInfoPage productInfoPage;
	 public Properties prop;
	 public registrationPage registrationPage;
		
		@BeforeTest
		   public void setup() {
			df= new driverfactory();
			prop = df.initProp();
			driver = df.initDriver(prop);
			 loginpage = new loginPage(driver);
			
		    
			
		}
		
		@AfterTest
		   public void tearDown() {
			 driver.quit();
		}

}
