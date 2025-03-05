package part3_4.com.demoqa.base;

import com.demoqa.pages.HomePage;
import com.base.BasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import org.apache.commons.io.FileUtils;


import static com.base.BasePage.delay;
import static utilities.Utility.setUtilityDriver;

public class BaseTest {

    private WebDriver driver;
    protected BasePage basePage;
    protected HomePage homePage;
    private String DEMOQA_URL = "https://demoqa.com/";

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // Generate a unique user data directory
        try {
            Path tempProfileDir = Files.createTempDirectory("chrome-profile");
            options.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Other recommended options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless=new");  // Run Chrome in headless mode for Jenkins
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadApplication() {
        driver.get(DEMOQA_URL); // Opens the WebApp
        basePage = new BasePage();  // Creates a BasePage Object instance
        basePage.setDriver(driver); // Sets the WebDriver inside the BasePage instance
        setUtilityDriver(); // Ensures that driver in the test class references the same driver instance from BasePage.
        homePage = new HomePage();
    }

    // ITestResult is an interface from TestNG
    // TakeScreenshot is an interface from TestNG
    @AfterMethod
    public void takeFailedResultScreenshot(ITestResult testResult) {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;  // This casts the driver to TakesScreenshot, so we can call screenshot methods on it.
            File source = screenshot.getScreenshotAs(OutputType.FILE);    // Captures and stores screenshot
            File destination = new File(System.getProperty("user.dir") +
                    "/resources/screenshots/(" +
                    LocalDate.now() + ")" +
                    testResult.getName() + ".png");
            try {
                FileUtils.copyFile(source, destination);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
            System.out.println("Screenshot Located At " + destination);

        }
    }

    @AfterClass
    public void tearDown() {
        delay(3000);
        driver.quit();
    }
}
