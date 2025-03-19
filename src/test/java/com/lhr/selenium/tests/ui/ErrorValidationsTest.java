package com.lhr.selenium.tests.ui;

import com.lhr.selenium.Base.BaseTest;
import com.lhr.selenium.PageObjects.ManagerOfficerPage;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;

public class ErrorValidationsTest extends BaseTest {

    public void LoginErrorValidation() throws IOException{

        landingPage.loginAction("MarryIND","Test@1234");
        Assert.assertEquals("You have entered an incorrect password...",landingPage.getLoginErrorMessage());
    }


}
