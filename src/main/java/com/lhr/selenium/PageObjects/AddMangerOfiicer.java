package com.lhr.selenium.PageObjects;

import com.lhr.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AddMangerOfiicer extends AbstractComponent {

    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    WebElement FirstName;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    WebElement LastName;

    @FindBy(xpath = "//input[@formcontrolname='phone']")
    WebElement Phone;

    @FindBy(xpath = "//input[@formcontrolname='userName']")
    private WebElement UserName;

    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement Password;

    @FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
    private WebElement ConfirmPassword;

    //@FindBy(xpath = "//div[@class='ml-2 row show_btn']//button[@type='button'][normalize-space()='Submit']")

    @FindBy(xpath = "//div[@class='col-md-12 float-right show_btn']//button[@type='button'][normalize-space()='Submit']")
    private WebElement SubmitBtn;

    @FindBy(xpath = "//div[@class='col-md-12 float-right show_btn']//button[@type='button'][normalize-space()='Cancel']")
    private WebElement CancelBtn;
  //  @FindBy(xpath = "//div[@class='ml-2 row show_btn']//button[@type='button'][normalize-space()='Cancel']")


    @FindBy(xpath = "//a[@id='p-tabpanel-1-label']")
    private WebElement payrollTab;

    @FindBy(xpath = "//select[@formcontrolname='timeZone']")
    private WebElement timezoneDropdown;

    @FindBy(xpath = "//input[@formcontrolname='maxWeeklyHrs']")
    private WebElement officerMaxHours;

    @FindBy(xpath ="//input[@formcontrolname='overtimeWeeklyAfterHrs']")
    private WebElement overtimeWeeklyAfterHrs;


    WebDriver driver;
    public AddMangerOfiicer(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void addOfficer(String firstName, String lastName, String phone, String userName, String password, String confirmPassword){
        waitForPageLoadComplete(10);
        sendKeys(FirstName, firstName);
        sendKeys(LastName, lastName);
        sendKeys(Phone, phone);
        sendKeys(UserName, userName);
        sendKeys(Password, password);
        sendKeys(ConfirmPassword, confirmPassword);
        waitForPageLoadComplete(20);

    }

    public void clickPayrollTab(String timezone) {
        click(payrollTab);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(timezoneDropdown));

        selectDropdownByVisibleText(timezoneDropdown, timezone);
    }
    public void enterMaxHours(String hours ,String overtimeHours) {
        officerMaxHours.clear();
        officerMaxHours.sendKeys(hours);
        overtimeWeeklyAfterHrs.clear();
        overtimeWeeklyAfterHrs.sendKeys(overtimeHours);
    }

    public WebElement getcancelBtn() {
        return CancelBtn;
    }

    public void clickSubmitBtn(){
        click(CancelBtn);

    }

}
