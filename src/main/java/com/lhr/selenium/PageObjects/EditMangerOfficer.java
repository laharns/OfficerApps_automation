package com.lhr.selenium.PageObjects;

import com.lhr.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditMangerOfficer extends AbstractComponent {
    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    WebElement FirstName;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    WebElement LastName;

    @FindBy(xpath = "//input[@formcontrolname='phone']")
    WebElement Phone;

    @FindBy(xpath = "//div[@class='ml-2 row show_btn']//button[@type='button'][normalize-space()='Submit']")
    private WebElement SubmitBtn;

  //@FindBy(xpath = "//div[@class='ml-2 row show_btn']//button[@type='button'][normalize-space()='Cancel']")
    @FindBy(xpath = "//div[@class='col-md-12 float-right show_btn']//button[@type='button'][normalize-space()='Cancel']")
    private WebElement CancelBtn;


    @FindBy(xpath = "//li[@class='ng-star-inserted']//a[contains(.,'Payroll Settings')]")
    private WebElement updatepayrollTab;

    @FindBy(xpath = "//select[@formcontrolname='timeZone']")
    private WebElement timezoneDropdown;

    @FindBy(xpath = "//input[@formcontrolname='maxWeeklyHrs']")
    private WebElement officerMaxHours;

    @FindBy(xpath ="//input[@formcontrolname='overtimeWeeklyAfterHrs']")
    private WebElement overtimeWeeklyAfterHrs;

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

    public void EditclickPayrollTab(String timezone) {
        click(updatepayrollTab);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(timezoneDropdown));

        selectDropdownByVisibleText(timezoneDropdown, timezone);
    }
    public void EditenterMaxHours(String hours ,String overtimeHours) {
        officerMaxHours.clear();
        officerMaxHours.sendKeys(hours);
        overtimeWeeklyAfterHrs.clear();
        overtimeWeeklyAfterHrs.sendKeys(overtimeHours);
    }


    public WebElement getcancelBtn() {
        return CancelBtn;
    }

    public void editclickSubmitBtn(){
        click(CancelBtn);

    }

}
