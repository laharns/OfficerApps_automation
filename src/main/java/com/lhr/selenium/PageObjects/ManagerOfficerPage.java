package com.lhr.selenium.PageObjects;

import com.lhr.selenium.AbstractCompoments.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import org.testng.Assert;
import java.util.List;

public class ManagerOfficerPage extends AbstractComponent {

@FindBy(xpath = "//div[@id='sidebar-wrapper']//ul[@class='sidebar-nav']/li[5]")
private WebElement officermenu;

@FindBy(xpath = "//a[normalize-space()='Manage Officers']")
private WebElement managerofficer;

By officerTable = By.xpath("//table/tbody/tr");

@FindBy(css = "table tbody tr td:nth-child(2)")
private List<WebElement> officerNames;

@FindBy(xpath = "//button[@routerlink='/officers/manage-officer/add-officer']")
private WebElement addOfficerBtn;

@FindBy(xpath = "//i[@class='fa fa-pencil']")
private WebElement editOfficerBtn;

@FindBy(xpath = "//i[@class='fa fa-trash']")
private WebElement deleteOfficerBtn;

@FindBy(xpath = "//i[@class='fa fa-key']")
private WebElement resetPasswordBtn;

@FindBy(xpath = "//input[@formcontrolname='newPassword']")
private WebElement newPassword;

@FindBy(xpath = "//input[@formcontrolname='confirmPassword']")
private WebElement confirmPassword;

@FindBy(xpath = "//button[@type='button'][normalize-space()='Submit']")
private WebElement passwordSubmitBtn;

@FindBy(xpath = "//button[normalize-space()='Cancel']")
private WebElement passwordCancelBtn;

@FindBy(xpath = "alert_ok=xpath://button[text()=' Ok ']")
WebElement alert_ok;

@FindBy(xpath = "//button[text()=' Cancel ']")
WebElement alert_cancel;

WebDriver driver;

public ManagerOfficerPage(WebDriver driver)  {
    super(driver);
    this.driver = driver;
    PageFactory.initElements(driver, this);
}

public void goTo() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngx-overlay")));
    wait.until(ExpectedConditions.elementToBeClickable(officermenu));
    click(officermenu);
    click(managerofficer);
}

    public List<WebElement> getOfficerList() {
        waitForElementToAppear(officerTable);
        Assert.assertFalse(officerNames.isEmpty(), "Officer list is empty!");
        return officerNames;
    }

    public WebElement getOfficerByName(String name) {
        WebElement officer = officerNames.stream()
                .filter(officerName -> officerName.getText().trim().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        Assert.assertNotNull(officer, "Officer with name '" + name + "' not found!");
        return officer;
    }

    public void clickonOfficer() {
        click(addOfficerBtn);
    }

    public void clickonEditOfficerBtn() {
    click(editOfficerBtn);

    }

    public void clickonDeleteOfficerBtn(boolean confirmDelete) {
        click(deleteOfficerBtn); // Click on the delete officer button

        if (confirmDelete) {
            click(alert_ok); // Click OK on the confirmation alert
            System.out.println("Officer deleted successfully.");
        } else {
            click(alert_cancel); // Click Cancel on the confirmation alert
            System.out.println("Officer deletion canceled.");
        }
    }

    public void clickonResetPasswordBtn(String password, String confirmPassword) {
        click(resetPasswordBtn);
        waitForPageLoadComplete(10);
        sendKeys(newPassword, password);
        sendKeys(this.confirmPassword, confirmPassword);
    }
        public void newpassowrdsubmitclick(){
            click(passwordCancelBtn);

    }

}
