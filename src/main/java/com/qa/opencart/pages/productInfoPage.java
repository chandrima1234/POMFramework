package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.appConstant;
import com.qa.opencart.utils.ElementUtil;

public class productInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleutil ;
 	Map<String,String> prodInfoMap;
	
	By prodImage = By.cssSelector("ul .thumbnail img");
	By prodMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][position()=1]");

	public productInfoPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	public String getProductHeader(String prodName) {
		String xpath = "//h1[text()='"+prodName+"']";
		String prodHeader = eleutil.doGetText(By.xpath(xpath));
		return prodHeader;
		
	}
	
	public int getProductImagecount() {
		return eleutil.waitForElementsToBeVisible(prodImage,appConstant.DEFAULT_LARGE_TIMEOUT).size();
	}
	public String getProductTitle(String prodName) {
		return eleutil.waitForTitleIs(prodName,appConstant.DEFAULT_TIMEOUT);
	}
	public String getProductUrl(String searchKey) {
		return eleutil.waitForUrlIs(appConstant.DEFAULT_LARGE_TIMEOUT, searchKey);
	}
	
	public Map<String, String> getProductMetaData() {
		List<WebElement> ProdMeta = eleutil.getElements(prodMetaData);
		prodInfoMap = new HashMap<String,String>();
		for(WebElement e : ProdMeta) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			prodInfoMap.put(metaKey, metaValue);
		}
			prodInfoMap.forEach((k,v) -> System.out.println(k+ ":" +v));
			return prodInfoMap;
			//return Map;
			
			
		
		
	}
	
	

}
