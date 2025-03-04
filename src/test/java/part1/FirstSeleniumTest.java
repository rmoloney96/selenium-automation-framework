package part1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstSeleniumTest {

    WebDriver driver;

    // BeforeClass will run the below function before your actual main code
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    // Cleans up test and acts like a post condition
    @AfterTest
    public void tearDown() {
        //driver.quit(); // quit closes the windows and the driver
        //driver.close(); // only closes the current window
    }

    // Test annonation identifies our test method
    @Test
    public void testLoggingIntoApplication() throws InterruptedException {
        Thread.sleep(2000);
        // Find web element
        // By is a class that is used to find an element using selenium locators
        // Use By.ccsSelector and not By.ByCssSelector just as example
        // By.id, By.className, By.cssSelector, By.linkText, By.name, By.partialLinkText, By.tagName, By.xpath
        WebElement username = driver.findElement(By.name("username"));
        // Perform action on the element
        username.sendKeys("Admin");

        // var infers the datatype based on driver.findElement
        var password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        driver.findElement(By.tagName("Button")).click();
        // Dont use in test scripts as thread.sleep is a hard stop
        Thread.sleep(2000);
        String actualResult = driver.findElement(By.tagName("h6")).getText();
        String expectedResult = "Dashboard";
        Assert.assertEquals(actualResult, expectedResult);
    }

}
