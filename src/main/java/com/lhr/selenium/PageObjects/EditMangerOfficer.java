package com.lhr.selenium.PageObjects;

import com.lhr.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditMangerOfficer extends AbstractComponent {
    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    WebElement FirstName;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    WebElement LastName;

    @FindBy(xpath = "//input[@formcontrolname='phone']")
    WebElement Phone;

    @FindBy(xpath = "//div[@class='ml-2 row show_btn']//button[@type='button'][normalize-space()='Submit']")
    private WebElement SubmitBtn;

    @FindBy(xpath = "//div[@class='ml-2 row show_btn']//button[@type='button'][normalize-space()='Cancel']")
    private WebElement CancelBtn;

    WebDriver driver;
    public EditMangerOfficer(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void Editoffcer(String updatefirstName, String updatelastName, String updatephone) throws InterruptedException {
        waitForPageLoadComplete(10);
        sendKeys(FirstName, updatefirstName);
        Thread.sleep(3000);
        sendKeys(LastName, updatelastName);
        sendKeys(Phone, updatephone);
        waitForPageLoadComplete(20);

    }

    public WebElement getcancelBtn() {
        return CancelBtn;
    }

    public void clickSubmitBtn(){
        click(CancelBtn);

    }

}
