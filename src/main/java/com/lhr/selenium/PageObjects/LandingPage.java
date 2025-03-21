package com.lhr.selenium.PageObjects;

import com.lhr.selenium.AbstractCompoments.AbstractComponent;
import com.lhr.selenium.utils.PropertyFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LandingPage extends AbstractComponent {

    private final String baseUrl;
    PropertyFileReader propertyFileReader;
    WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Enter Username']")
    WebElement userEmailEle;

    @FindBy(xpath = "//input[@placeholder=' Enter Password']")
    WebElement passwordEle;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginBtnEle;

    @FindBy(xpath = "//div[@class='p-toast-detail ng-tns-c21-1']")
    WebElement errorMessageEle;

    @FindBy(xpath = "//u[normalize-space()='Forgot Password']")
    WebElement forgotlink;

    @FindBy(xpath = "//h3[normalize-space()='Forgot Password']")
    WebElement Forgottext;


    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        try {
            propertyFileReader = new PropertyFileReader("src/main/resources/GlobalData.properties");
            this.baseUrl = propertyFileReader.getProperty("baseUrl");
        } catch (Exception e) {
            throw new RuntimeException("Failed to load base URL from GlobalData.properties", e);
        }
    }
    public void goTo(){
        driver.get(baseUrl);
        waitForPageLoadComplete(10);

    }
    public ManagerOfficerPage loginAction(String user, String password){
        sendKeys(userEmailEle,user);
        sendKeys(passwordEle, password);
        click(loginBtnEle);
      //  Assert.assertEquals("");
        return new ManagerOfficerPage(driver);
    }

    public String getLoginErrorMessage(){
        waitForWebElementToVisible(errorMessageEle);
        return errorMessageEle.getText();
    }

    public String forgotbutton(){
        click(forgotlink);
        return Forgottext.getText();
    }
}
