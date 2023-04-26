package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class optionsManager {
	
	



		private Properties prop;
		private ChromeOptions co;
	

		public optionsManager(Properties prop) {
			this.prop = prop;
		}
		
		public ChromeOptions getChromeOptions() {
			 co = new ChromeOptions();
			 if(Boolean.parseBoolean(prop.getProperty("headless"))){
				    co.addArguments("--headless=new");
					co.addArguments("--incognito");
		}
			 
			 return co;

     }

}
