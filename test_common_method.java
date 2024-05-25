package common_method;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import dev.failsafe.internal.util.Assert;

    public class test_common_method{
	WebDriver driver;

	@Test(priority=0)
	public void LaunchAUT() {
		driver = new ChromeDriver();
		driver.get("https://sakshingp.github.io/assignment/login.html");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();

	}

// Perform login steps if required
	@Test(priority=1)
	public  void login() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("anilyadavcse104@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("password")).sendKeys("Anilyadav@1990");
		Thread.sleep(2000);
		driver.findElement(By.id("log-in")).click();
		Thread.sleep(2000);
	}

	@Test(priority=2)
	public void AmountsSorted(){
		
		// Click the AMOUNT header to sort the table
		WebElement amountHeader = driver
				.findElement(By.xpath("//table[@id='transactionsTable']//tr//th[@id='amount']"));
		amountHeader.click();

		// Wait for the table to be sorted
		try {
			Thread.sleep(2000); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Get all rows from the table after sorting
		List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
		List<Double> amounts = new ArrayList<>();

		// Extract the amount from each row and print it
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			if (cells.size() > 4) {
				String amountText = cells.get(4).getText().replaceAll("[^\\d.-]", "");
				double amount = Double.parseDouble(amountText);
				amounts.add(amount);
				System.out.println(amount); 
			}
		}

		// Check if the list is sorted
		boolean isSorted = isSorted(amounts);
		Assert.isTrue(isSorted, "The amounts are not sorted");
	}

	private boolean isSorted(List<Double> amounts) {
		for (int i = 0; i < amounts.size() - 1; i++) {
			if (amounts.get(i) > amounts.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

	@AfterClass
	public void tearDown() {
		// Close the browser
		if (driver != null) {
			driver.quit();
		}
	}
}
		

			

