package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class driverfactory {
	
	//method is used to initlaise the driver
	
	
	// evertything will be added here to maintain single responsibilty

	
	
	public WebDriver driver;
	Properties prop;
	
	private static final Logger LOG = Logger.getLogger(driverfactory.class);
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static String highlight;
//	public OptionsManager optionsManager;
	
	public WebDriver initDriver(Properties prop) {
		String BrowserName =prop.getProperty("browser");
		
		highlight =prop.getProperty("highlight");
		optionsManager optionsManager = new optionsManager(prop);
		 
		//System.out.println("browser name is " +BrowserName);
		if(BrowserName.equals("chrome")) {
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			driver = new ChromeDriver();
		}
		else if(BrowserName.equals("firefox")) {
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver()); // thread local use for multi thread parallel testing
			//driver = new FirefoxDriver();
		}		
		else if (BrowserName.equals("edge")) {
			System.setProperty("webdriver.http.factory", "jdk-http-client");
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
			//driver = new EdgeDriver();
		}		
		else {
			LOG.error("Please pass the current browser:");
			
			System.out.println("Please pass the current browser");
		}
		getdriver().manage().window().maximize();
		getdriver().manage().deleteAllCookies();
		getdriver().get(prop.getProperty("url"));
		return getdriver();
   
		}
	
	public static synchronized WebDriver getdriver() { ///each thread is in sync, no deadlock happen
		return tlDriver.get();
	}
	
	
	//init the config properties
	public Properties initProp() {
		 prop = new Properties();
		 
			FileInputStream ip = null;
		//mvn clean install -Denv="dev"
					// mvn clean install

					// String envName = System.getenv("env");// stage/uat/qa/dev
					String envName = System.getProperty("env"); // system is used to read fromt he maven from command line
					System.out.println("-----> Running test cases on environment: ----->" + envName);
					if (envName == null) {
						System.out.println("No env is given..hence running it on QA env.....");
						try {
							ip = new FileInputStream("./src/test/resources/config/config.properties");
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}

					else {
						try {
							switch (envName) {
							case "qa":
								ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
								break;
							case "dev":
								ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
								break;
							case "stage":
								ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
								break;
							case "prod":
								ip = new FileInputStream("./src/test/resources/config/config.properties");
								break;

							default:
								System.out.println("please pass the right env name...." + envName);
							
							}

						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}

					}
		 

					try {
						prop.load(ip);
					} catch (IOException e) {
						e.printStackTrace();
					}

					return prop;
		     
	     }
	
	
	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getdriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}
}
