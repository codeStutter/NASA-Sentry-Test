package Nasa.nasa_1;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UIValidationTest {
	 public static void main(String[] args) throws InterruptedException {
	        // declaration and instantiation of objects/variables
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			JavascriptExecutor js = (JavascriptExecutor) driver;
	        String baseUrl = "https://cneos.jpl.nasa.gov/sentry/";
	        driver.manage().window().maximize();
	        driver.get(baseUrl);
	        
	        //List the expected values
	        String[] expectedElements = {"10","25","50","100"};
	        WebElement dropdown = driver.findElement(By.name("riskTable_length"));   
	        Select selectOption = new Select(dropdown); 
	        
	        //To check 25 entries is selected by default
	        String default_value = selectOption.getFirstSelectedOption().getText();
	        Assert.assertEquals(default_value, "25");
	        
	        //To Verify Drop down should display 4 options, i.e. 10, 25, 50 and 75
	        List<WebElement> numOfElements = selectOption.getOptions();
	        for(int j=0; j<numOfElements.size();j++){
	        	for (int i=0; i<expectedElements.length; i++){
	        		Assert.assertTrue((numOfElements.get(j).getText()).contentEquals(expectedElements[i]));
	        		j++;
	        	}
	        }
	        
	        //To validate the des value in the query parameter of the newly opened driver page
	        js.executeScript("window.scrollBy(0,1000)");
	        WebDriverWait wait1 = new WebDriverWait(driver, 100);
	        WebElement link = driver.findElement(By.xpath("//a[contains(text(),\"29075 (1950 DA)\")]"));
	        link.click();
	        String driver_url = driver.getCurrentUrl();
	        Assert.assertTrue(driver_url.contains("29075"));
	       
	        driver.close();
	    }

}
