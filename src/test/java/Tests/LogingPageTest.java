package Tests;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.methods.Configurable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import Config.UserCredentials;
import static Config.UserCredentials.*;
import static Config.ServicesPaths.*;
public class LogingPageTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		baseUrl = "https://172.20.208.79:4040/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testLoginInAdmin() throws Exception {
		driver.get(baseUrl + "/admin/#/login");
		driver.findElement(By.id("loginUsername")).clear();
		driver.findElement(By.id("loginUsername")).sendKeys(UserCredentials.userName);
		driver.findElement(By.id("loginPassword")).clear();
		driver.findElement(By.id("loginPassword")).sendKeys(UserCredentials.passWord);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.linkText("Email Servers")).click();
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span.ng-binding")).getText(),UserCredentials.userName);

	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.findElement(By.cssSelector("a > span")).click();
		driver.quit();
	}
}
