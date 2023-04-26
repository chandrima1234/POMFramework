
package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.driverfactory;

public class ElementUtil {
	
	
	public static final String ELEMENT_NOT_FOUND_ERROR = "Element is present =";
	
	//by default driver is null. so to avoid unncesary null > make it private if you create the object of 
	//elementutil
	
	  private WebDriver driver;
	  private javascriptUtils jsUtil;
//avoiding static unnecessay so that parallel execution can be done
		  public ElementUtil(WebDriver driver) {
			  this.driver =driver;
			  jsUtil = new javascriptUtils(driver);
		  }
			
		  public List<WebElement> getElements(By locator) {
			  return driver.findElements(locator);
		  }
		  public  WebElement getElement(By locator) {
			  WebElement ele =  driver.findElement(locator);
				if(Boolean.parseBoolean(driverfactory.highlight)){
					jsUtil.flash(ele);
				}
				return ele;
			    }
			
		  public  void doSendkeys(By locator, String value) {
			  WebElement ele = getElement(locator);
			  ele.clear();
			  ele.sendKeys(value);
			    }
			
		  public void doClick(By locator) {
				getElement(locator).click();
			}
			
		  public String doGetText(By locator) {
				return getElement(locator).getText();
			}
			
		  public String doGetAttribute(By locator,String value) {
			  
				return getElement(locator).getAttribute(value);
			}
		  
			
			public boolean isdisplayed(By locator) {
				return getElement(locator).isDisplayed();
			}
			
		    public  ArrayList<String> getElementText(By locator) {
				List<WebElement>linkText = getElements(locator);
				ArrayList<String> text= new ArrayList<String>();
		        for(WebElement e: linkText) {
		        	  String er = e.getText();
		       	     if(er.length()!=0) {  
		       	        		text.add(er);
		                     }
		       	        }
				   return text;
			}
		    
		    public int getElementcount(By locator) {
		    	return getElements(locator).size();
		    }
		    
		    /******************dropdown handle********************/
		    public  String doSelectDropdown(By locator, String value) {
		   	 Select sel = new Select(getElement(locator));
		   		sel.selectByIndex(5);
		   		sel.selectByValue(value);
		   		return value;
		   		
		    }
		   public  ArrayList<String> Countrylist(By locator){
		           Select sel = new Select(getElement(locator));
		   		List<WebElement> options = sel.getOptions();
		   		ArrayList<String> countrylist = new ArrayList<String>();
		   		int count =options.size();
		   		System.out.println(count);
		   		for(WebElement e: options) {
		   			  String text = e.getText();
		   			 countrylist.add(text);
		   		}
		   		return countrylist;
		    }
		   public  void doDropdownWithoutSelect(By locator,String value) {
				List<WebElement> country = getElements(locator);
				for(WebElement e:country) {
					String text = e.getText();
					if(text.equals(value)) {
						e.click();
					}
				}
			}
		   
		   public  void googleSearch(By locator, By sugglist,String searchitem,String clickeditem) throws InterruptedException {
				getElement(locator).sendKeys(searchitem);
				    Thread.sleep(3000);
				    List<WebElement> options = getElements(sugglist);
				    for(WebElement e:options) {
				    	String text = e.getText();
				    	
				    	
				    	
				    	System.out.println(text);
					    if(text.equals(clickeditem)) {
					         e.click();
					         break;
					    }
				    }
			}
		   
		   /*----------------Wait utils------------------------*
		    * 
		    * An expectation for checking that an element is present on the DOM of a page. 
		    * This does notnecessarily mean that the element is visible
		    */
		    public  WebElement waitForElementPresence(By locator, int timeout) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			}
		    //An expectation for checking that an element is present on the DOM of a page and visible.Visibility means 
		    //that the element is not only displayed but also has a height and width that isgreater than 
		    public  WebElement waitForElementPresencenew(By locator, int timeout) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
		    public  WebElement waitForElementPresencenew(By locator, int timeout, int pollingTime) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout), Duration.ofSeconds(pollingTime));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
		    public  void clickWhenReady(By locator, int timeout, int pollingTime) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout), Duration.ofSeconds(pollingTime));
				 wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			}
		    public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			}
		    
			public  void doSendkeyswithWait(By locator, int timeout, String value) {
				waitForElementPresence(locator,5).sendKeys(value);
			}
			public  void doClickwitWait(By locator, int timeout) {
				waitForElementPresence(locator,5).click();
			}
			public  void getElementTextWait(By locator, int timeout) {
				waitForElementPresence(locator,5).getText();
			}
			public String waitForTitleContains(int timeOut, String titleFraction) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
					return driver.getTitle();
				} else {
					return null;
				}
			}

			public String waitForTitleIs(String titleValue, int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.titleIs(titleValue))) {
					return driver.getTitle();
				} else {
					return null;
				}
			}

			public String waitForUrlContains(int timeOut, String urlFraction) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
					return driver.getCurrentUrl();
				} else {
					return null;
				}
			}

			public String waitForUrlIs(int timeOut, String urlValue) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
					return driver.getCurrentUrl();
				} else {
					return null;
				}
			}
			
			
			/*** alert Waits *///
			public Alert waitForAlert(int timeout) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeout));
		        return wait.until(ExpectedConditions.alertIsPresent());
			}
			
			public String getTextAlert(int timeout) {
				return waitForAlert(timeout).getText();
			}
			
			public void alertAceept(int timeout) {
				  waitForAlert(timeout).accept();
			}
			public void waitForFrame(int timeOut, int frameIndex) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			}

			public void waitForFrame(int timeOut, String nameOrID) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
			}

			public void waitForFrame(int timeOut, WebElement FrameElement) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
			}

			public void waitForFrame(int timeOut, By FrameLocator) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameLocator));
			}
			
			public WebElement waitForElementToBeVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
						.withTimeout(Duration.ofSeconds(timeOut))
							.pollingEvery(Duration.ofSeconds(pollingTime))
								.ignoring(NoSuchElementException.class)
									.ignoring(StaleElementReferenceException.class)
										.ignoring(ElementNotInteractableException.class)
									.withMessage(ElementUtil.ELEMENT_NOT_FOUND_ERROR + locator);
				
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
			}
			
			public WebElement retryingElement(By locator, int timeOut) {

				WebElement element = null;

				int attempts = 0;

				while (attempts < timeOut) {

					try {
						element = getElement(locator);
						System.out.println("element is found in attempt: " + attempts);
						break;
					} catch (NoSuchElementException e) {
						System.out.println("element is not found in attempt : " + attempts + " : " + " for " + locator);

						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					attempts++;

				}
				
				if (element == null) {
					try {
						throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
					} catch (Exception e) {
						System.out.println("element is not found exception ...tried for : " + timeOut + " secs"
								+ " with the interval of : " + 500 + " ms");

					}

				}

				return element;

			}
			
			public WebElement retryingElement(By locator, int timeOut, int pollingTime) {

				WebElement element = null;

				int attempts = 0;

				while (attempts < timeOut) {

					try {
						element = getElement(locator);
						System.out.println("element is found in attempt: " + attempts);
						break;
					} catch (NoSuchElementException e) {
						System.out.println("element is not found in attempt : " + attempts + " : " + " for " + locator);

						try {
							Thread.sleep(pollingTime);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					attempts++;

				}
				
				if (element == null) {
					try {
						throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
					} catch (Exception e) {
						System.out.println("element is not found exception ...tried for : " + timeOut + " secs"
								+ " with the interval of : " + pollingTime + " ms");

					}

				}

				return element;

			}
}

