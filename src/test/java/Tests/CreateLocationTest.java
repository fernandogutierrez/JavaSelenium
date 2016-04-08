package Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateLocationTest {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeClass
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    driver.manage().window().maximize(); 
	    baseUrl = "https://172.20.208.79:4040/";
	    driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
	    driver.get(baseUrl + "/admin/#/login");
	    driver.findElement(By.id("loginUsername")).clear();
	    driver.findElement(By.id("loginUsername")).sendKeys("JavierGutierrez");
	    driver.findElement(By.id("loginPassword")).clear();
	    driver.findElement(By.id("loginPassword")).sendKeys("Control123");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	  }

	  @Test
	  public void testCreateALocationWithAssociation() throws Exception {
		//String location = utils.stringGenerator.randomName();
		  String location = "location";
		  
	    driver.findElement(By.linkText("Email Servers")).click();
	    driver.findElement(By.linkText("Locations")).click();
	    driver.findElement(By.xpath("//div[4]/div/button")).click();
	    driver.findElement(By.id("location-add-name")).sendKeys(location);
	    driver.findElement(By.id("location-add-display-name")).sendKeys(location);
	    driver.findElement(By.linkText("Location Associations")).click();
	    driver.findElement(By.xpath("//div[2]/button")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    driver.findElement(By.linkText("Email Servers")).click();
	    driver.findElement(By.linkText("Locations")).click();
	   
	    List<WebElement> rows = driver.findElements(By.xpath("//div[@class='ngCanvas']/div[@ng-style='rowStyle(row)']/div[2]"));
	    
	    WebElement myDynamicElement = new WebDriverWait(driver,5)
	       .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ngCanvas']/div[@ng-style='rowStyle(row)']/div[2]")));

	    boolean res = false;
	    for(WebElement row : rows){
	    	String text = row.getText();
	    	text = text.replaceAll("\\s+","");
	    	if(text.equals(location)){
	    		res = true;
	    		row.click();
	    	}
	    }
	    Assert.assertTrue(res);
	  }

	  @AfterClass
	  public void tearDown() throws Exception {
		driver.findElement(By.xpath("//button[2]")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();  
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
