package Tests;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class EditResourceTest {
  
	 private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private Actions action;
	  
	  @BeforeClass
	  public void setUp() throws Exception {
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
		  baseUrl = "https://172.20.208.79:4040/";
		  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		  driver.get(baseUrl + "/admin/#/login");
		  driver.findElement(By.id("loginUsername")).sendKeys("JavierGutierrez");
		  driver.findElement(By.id("loginPassword")).sendKeys("Control123");
		  driver.findElement(By.xpath("//button[@type='submit']")).click();
		  action = new Actions(driver);
	  }

	  @Test
	  public void testEditAresource() throws Exception {
		//String editName = utils.stringGenerator.randomName();
		  String editName = "RoomEdited";  
		driver.findElement(By.linkText("Email Servers")).click();
		driver.findElement(By.linkText("Resources")).click();
		
	    (new WebDriverWait(driver, 10))
	     .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='pull-left']/button[@class='btn btn-default btn-sm']")));
	   
	    List<WebElement> resourcesRows = driver.findElements(By.xpath("//div[@class='ngCanvas']/*/div[@class='ngCell centeredColumn col2 colt2']"));
	    
	    for(WebElement row : resourcesRows){
	    	String rowsText = row.getText();
	    	rowsText = rowsText.replaceAll("\\s+","");
	    	System.out.print(rowsText);
	    }
	    
	    String resourceToEdit = resourcesRows.get(2).getText();
	    System.out.print(resourceToEdit);
	    resourcesRows.get(2).click();
	    action.doubleClick(resourcesRows.get(2)).build().perform();
	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(editName);
	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
	    driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(editName);
	    driver.findElement(By.id("convert")).click();
	    driver.findElement(By.xpath("//button[@value='fa-fire']")).click();
	    driver.findElement(By.cssSelector("button.info")).click();
	    driver.findElement(By.linkText("Resources")).click();
	  }

	  @AfterClass
	  public void tearDown() throws Exception {
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
