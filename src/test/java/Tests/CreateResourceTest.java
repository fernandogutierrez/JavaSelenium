package Tests;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import Config.ServicesPaths;
import Config.UserCredentials;
import static Config.UserCredentials.*;
import static Config.ServicesPaths.*;
import Utils.StringGenerator;

public class CreateResourceTest {

	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeClass
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
		
		driver.get(ServicesPaths.login);
		driver.findElement(By.id("loginUsername")).sendKeys(UserCredentials.userName);
		driver.findElement(By.id("loginPassword")).sendKeys(UserCredentials.passWord);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test
	public void testCreateANewResource() throws Exception {
		//String resource = StringGenerator.randomName();
		String resource = "room";

		driver.findElement(By.linkText("Email Servers")).click();
		driver.findElement(By.linkText("Resources")).click();

		(new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath("//div[@class='pull-left']/button[@class='btn btn-default btn-sm']")));

		driver.findElement(
				By.xpath("//div[@class='pull-left']/button[@class='btn btn-default btn-sm']"))
				.click();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys(
				resource);
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys(
				resource);
		driver.findElement(By.cssSelector("button.info")).click();
		driver.navigate().refresh();

		(new WebDriverWait(driver, 10)).until(ExpectedConditions
				.elementToBeClickable(By.linkText("Email Servers")));

		driver.findElement(By.linkText("Email Servers")).click();
		driver.findElement(By.linkText("Resources")).click();

		(new WebDriverWait(driver, 10))
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//div[@class='ngHeaderText ng-binding colt1']")));

		List<WebElement> monitorRows = driver
				.findElements(By
						.xpath("//div[@class='ngCanvas']/*/div[@class='ngCell centeredColumn col2 colt2']"));

		boolean res = false;
		for (WebElement row : monitorRows) {
			String rowsText = row.getText();
			rowsText = rowsText.replaceAll("\\s+", "");
			if (rowsText.equals(resource)) {
				row.click();
				res = true;
			}
		}
		Assert.assertTrue(res);

	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.findElement(By.id("btnRemove")).click();
		driver.findElement(By.cssSelector("button.info")).click();
		driver.quit();
	}

}
