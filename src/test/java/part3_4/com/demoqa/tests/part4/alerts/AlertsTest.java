package part3_4.com.demoqa.tests.part4.alerts;

import org.testng.Assert;
import org.testng.annotations.Test;
import part3_4.com.demoqa.base.BaseTest;

import static utilities.SwitchToUtility.acceptAlert;
import static utilities.SwitchToUtility.getAlertText;

public class AlertsTest extends BaseTest {

    @Test
    public void testInformationAlert() {
        String expectedAlertText = "You clicked a button";
        var alertsPage = homePage.goToAlertsWindows().clickAlerts();
        alertsPage.clickInformationAlertButton();
        Assert.assertEquals(getAlertText(), expectedAlertText,
                "\n Actual & Expected Messages Do Not Match \n");
        acceptAlert();
    }
}
