package Tests;

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

import Utils.StringGenerator;

public class LogingTabletTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @BeforeClass
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    driver.manage().window().maximize();
	    baseUrl = "https://172.20.208.79:4040/";
	    driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testLoginWithTablet() throws Exception {
		String room; 
		  
	    driver.get(baseUrl + "/tablet/#/register");
	    driver.findElement(By.id("service-url-input")).clear();
	    driver.findElement(By.id("service-url-input")).sendKeys("https://172.20.208.79:4040");
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("JavierGutierrez");
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("Control123");
	    driver.findElement(By.xpath("//div[@type='submit']")).click();
	    driver.findElement(By.xpath("//div[@type='submit']")).click();
	    driver.findElement(By.cssSelector("span.fa.fa-caret-left")).click();
	    
	    List<WebElement> roomsRows = driver.findElements(By.xpath("//div[@class='item-box']/*"));
	    
	    StringGenerator strGen = new StringGenerator();
	    room = strGen.randomRoomName(roomsRows);
	    
	    for(WebElement row : roomsRows){
	    	String textRoom2 = row.getText();
	    	textRoom2 = textRoom2.replaceAll("\\s+","");
	    	if(textRoom2.contains(room)){
	    		row.click();
	    			    		
	    		break;
	    	}
	    }

	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
	    
	    
	    (new WebDriverWait(driver, 10))
	      .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='room-name']")));
	    Assert.assertEquals(driver.findElement(By.xpath("//div[@class='room-name']")).getText(),room);
	    
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
