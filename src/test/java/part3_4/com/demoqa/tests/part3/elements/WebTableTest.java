package part3_4.com.demoqa.tests.part3.elements;

import org.testng.Assert;
import org.testng.annotations.Test;
import part3_4.com.demoqa.base.BaseTest;

public class WebTableTest extends BaseTest {

    @Test
    public void testWebTable() {
        var webTablePage = homePage.goToElements().clickWebTables();
        String email = "alden@example.com";
        String expectedAge = "97";
        webTablePage.clickEdit(email);
        webTablePage.setAge("97");
        webTablePage.clickSubmitButton();
        String actualAge = webTablePage.getTableAge(email);
        Assert.assertEquals(actualAge, expectedAge, "\n Actual age and Expected Age do not match \n");
    }
}
