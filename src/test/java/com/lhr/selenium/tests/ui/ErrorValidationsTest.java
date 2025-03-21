package com.lhr.selenium.tests.ui;

import com.lhr.selenium.Base.BaseTest;
import com.lhr.selenium.PageObjects.Forgot;
import com.lhr.selenium.PageObjects.ManagerOfficerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class ErrorValidationsTest extends BaseTest {

    @Test
    public void LoginErrorValidation() throws InterruptedException{

        landingPage.loginAction("MarryIND","Test@1234");
        Assert.assertEquals("You have entered an incorrect password.",landingPage.getLoginErrorMessage());

        landingPage.forgotbutton();
        Forgot forgot = new Forgot(driver);
        forgot.sendusername("Test");
        Thread.sleep(3000);
        forgot.clicksubmitforgot();

        Assert.assertEquals("User Does Not Exist.",forgot.getErrorMessage());
        forgot.clickhomebtn();

    }


}
