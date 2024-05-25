package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

    

	public  HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    By amountHeader = By.xpath("//table[@id='transactionsTable']//tr//th[@id='amount']");
    By amountColumn=By.cssSelector("table tbody tr");
    By amountCell=By.tagName("td");
    

    public void clickAmountHeader() {
        driver.findElement(amountHeader).click();
    }

	public List<WebElement> getAmountColumn() {
		
		return  driver.findElements(amountColumn);
	}
	

	public List<WebElement> getCell(WebElement row) {
		// TODO Auto-generated method stub
		 return driver.findElements(amountCell);
	}
}
