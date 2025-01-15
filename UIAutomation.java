package FIS.Assessment;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class UIAutomation {

    /**
     * Rigorous Test :-)
     * @throws InterruptedException 
     */
    @Test
    public void login() throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.ebay.com/");
        WebElement searchBox=driver.findElement(By.xpath("//*[@aria-label='Search for anything']"));
        searchBox.clear();
        searchBox.sendKeys("book");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ARROW_DOWN);
        searchBox.sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("(//*[text()='Brand New']/../preceding-sibling::a)[3]")).click();
        
        String parentWindowId=driver.getWindowHandle();
        Set<String> allWindows=driver.getWindowHandles();
        Iterator<String> switchToNestWindow= allWindows.iterator();
        while(switchToNestWindow.hasNext())
        {
        String childWindow=switchToNestWindow.next();
        if(!parentWindowId.equals(childWindow))
        {
        driver.switchTo().window(childWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Add to cart']")));
        driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
        Thread.sleep(2000);
        String actual=driver.findElement(By.xpath("//*[contains(@aria-label,'Your shopping cart')]")).getText();
        String expected="1";
        assertEquals(actual, expected, "Cart is not updating properly");

        }

        }
        
        driver.switchTo().window(parentWindowId); 
        driver.quit();
        
  
    }
}
