package part2.com.saucedemo.base;

import com.base.BasePage;
import com.saucedemo.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {

    protected WebDriver driver;
    protected BasePage basePage;
    protected LoginPage loginPage;
    private String url = "https://www.saucedemo.com";

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
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        loginPage = new LoginPage();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
