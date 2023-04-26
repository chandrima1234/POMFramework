package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.appConstant;
import com.qa.opencart.utils.ElementUtil;

public class searchResultPage {

	private WebDriver driver;
	private ElementUtil eleutil ;
	
	
	private By searchbox = By.cssSelector("div.product-layout");
	
	public searchResultPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public boolean isSearchSuccessful() {
		List<WebElement> searchElement = eleutil.waitForElementsToBeVisible(searchbox, appConstant.DEFAULT_LARGE_TIMEOUT);
		if(searchElement.size()>0) {
			System.out.println("Elements are present");
			return true;
		}
		else
		{
			System.out.println("elements are not present");
			return false;
		}
	}
	
	public productInfoPage selectProduct(String ProductName) {
		By mainPrdName = By.linkText(ProductName);
		eleutil.doClick(mainPrdName);
		return new productInfoPage(driver);
	}
}
