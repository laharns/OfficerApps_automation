package com.lhr.selenium.tests.ui;

import com.lhr.selenium.Base.BaseTest;
import com.lhr.selenium.PageObjects.AddMangerOfiicer;
import com.lhr.selenium.PageObjects.EditMangerOfficer;
import com.lhr.selenium.PageObjects.ManagerOfficerPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;


public class SubmitOrderTest extends BaseTest {

    @Test
    public void submitOrder() throws InterruptedException {

            landingPage.loginAction("MarryIND", "Test@123");
        Thread.sleep(3000);

        ManagerOfficerPage managerOfficerPage = new ManagerOfficerPage(driver);
        managerOfficerPage.goTo();
        List<WebElement> officerList = managerOfficerPage.getOfficerList();
        WebElement officer = managerOfficerPage.getOfficerByName("userMarrymodh1234");
        managerOfficerPage.clickonOfficer();

        AddMangerOfiicer addMangerOfiicer = new AddMangerOfiicer(driver);
        addMangerOfiicer.addOfficer("MarryIND1234","MarryINDlast","1234567890","userMarrymodh1234","Test@1234","Test@1234");
        Thread.sleep(3000);
        addMangerOfiicer.scrollToElement(addMangerOfiicer.getcancelBtn());
        addMangerOfiicer.clickSubmitBtn();

        //Edit officer
        managerOfficerPage.clickonEditOfficerBtn();
        EditMangerOfficer editMangerOfficer = new EditMangerOfficer(driver);
        editMangerOfficer.Editoffcer("MarryINd","MarryINDlastUpdate","123456789000");
        Thread.sleep(3000);
        addMangerOfiicer.scrollToElement(addMangerOfiicer.getcancelBtn());
        addMangerOfiicer.clickSubmitBtn();

        //Delete
        managerOfficerPage.clickonDeleteOfficerBtn(false); // Cancel deletion

        managerOfficerPage.clickonResetPasswordBtn("Test@123","Test@1234");
        managerOfficerPage.newpassowrdsubmitclick();

    }
}
