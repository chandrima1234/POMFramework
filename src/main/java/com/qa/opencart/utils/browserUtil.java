package com.qa.opencart.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class browserUtil {
	
	WebDriver driver;
	
	public WebDriver launchbrowser(String browser) {
		System.out.println("Browser name is" +browser);
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			//System.setProperty("webdriver.chrome.driver", "C:/Users/Chandrima/Downloads/chromedriver_win32/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			//System.setProperty("webdriver.chrome.driver", "C:/Users/Chandrima/Downloads/geckodriver-v0.32.2-win32/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new ChromeDriver();
			break;

		default:
			System.out.println("Please pass the current browser");
			break;
		}
		return driver;
	}

}
