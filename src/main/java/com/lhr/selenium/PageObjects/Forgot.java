package com.lhr.selenium.PageObjects;

import com.lhr.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Forgot extends AbstractComponent {

    @FindBy(xpath = "//input[@placeholder='Enter Username'][1]")
    WebElement Username;

    @FindBy(xpath = "//h3[normalize-space()='Forgot Password']")
    WebElement Forgot;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement Submit;

    @FindBy(xpath = "//div[@class='p-toast-detail ng-tns-c21-2']")
     WebElement ErrorMessage;

    @FindBy(xpath = "//button[@id='login_signup_cancel']")
     WebElement Homebtn;

    WebDriver driver;
    public Forgot(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void sendusername(String username){
        sendKeys(Username,username);
    }

    public void clicksubmitforgot(){
        click(Submit);
    }

    public String getErrorMessage(){
        waitForWebElementToVisible(ErrorMessage);
        return ErrorMessage.getText();
    }

    public void clickhomebtn(){
        click(Homebtn);
    }
}
