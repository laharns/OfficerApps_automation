package com.lhr.selenium.AbstractCompoments;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToAppear(By findBy) {
        if (findBy == null) {
            throw new IllegalArgumentException("The locator cannot be null.");
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); // Wait for visibility
        } catch (Exception e) {
            throw new RuntimeException("Failed to wait for the element to appear: " + findBy, e);
        }
    }

    public void waitForWebElementToVisible(WebElement findBy) {
        if (findBy == null) {
            throw new IllegalArgumentException("The WebElement cannot be null.");
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(findBy)); // Wait for visibility
        } catch (Exception e) {
            throw new RuntimeException("Failed to wait for the WebElement to become visible: " + findBy, e);
        }
    }

    public void waitForElementToDisappear(By findBy) {
        if (findBy == null) {
            throw new IllegalArgumentException("The locator cannot be null.");
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy)); // Wait for invisibility
        } catch (Exception e) {
            throw new RuntimeException("Failed to wait for the element to disappear: " + findBy, e);
        }
    }

    public void click(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("The WebElement cannot be null.");
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)); // Ensure element is clickable
            element.click(); // Perform the click action
        } catch (Exception e) {
            throw new RuntimeException("Failed to click on the element: " + element, e);
        }
    }

    public void sendKeys(WebElement element, String text) {
        if (element == null) {
            throw new IllegalArgumentException("The WebElement cannot be null.");
        }
        if (text == null) {
            throw new IllegalArgumentException("The text to send cannot be null.");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element)); // Ensure element is visible
        element.clear(); // Clear any existing text
        element.sendKeys(text); // Send the input text
    }

    public void scrollToElement(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("The WebElement cannot be null.");
        }
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll the element into view, centering it in the viewport
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);

            // Adjust for headers or footers if applicable
            js.executeScript("window.scrollBy(0, -50);"); // Offset by 50px
        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to the element: " + element, e);
        }
    }

    public String getText(WebElement element) {
        if (element == null) {
            throw new IllegalArgumentException("The WebElement cannot be null.");
        }
        waitForWebElementToVisible(element); // Ensure the element is visible
        return element.getText();
    }

    public void mouseHover(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }


    public void selectDropdownByVisibleText(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }
    public void clearTextField(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator)); // Ensure the element is visible
        element.clear(); // Clear the text field
    }

    public void waitForElementToContainText(By locator, String text, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public void navigateToUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("The URL cannot be null or empty.");
        }
        driver.navigate().to(url);
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert(); // Attempt to switch to the alert
            return true;
        } catch (NoAlertPresentException e) {
            return false; // No alert present
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while checking for alert presence", e);
        }
    }

    public void doubleClickElement(By locator) {
        if (locator == null) {
            throw new IllegalArgumentException("The locator cannot be null.");
        }
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(locator)); // Ensure element is clickable
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform(); // Perform double-click
        } catch (Exception e) {
            throw new RuntimeException("Failed to double-click on the element located by: " + locator, e);
        }
    }

    public void rightClickElement(By locator) {
        if (locator == null) {
            throw new IllegalArgumentException("The locator cannot be null.");
        }
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(locator)); // Ensure element is clickable
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform(); // Perform right-click
        } catch (Exception e) {
            throw new RuntimeException("Failed to right-click on the element located by: " + locator, e);
        }
    }

    public List<String> getAllDropdownOptions(By locator) {
        if (locator == null) {
            throw new IllegalArgumentException("The locator cannot be null.");
        }
        try {
            WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator)); // Wait for the dropdown to be visible
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions(); // Get all options
            List<String> optionTexts = new ArrayList<>();
            for (WebElement option : options) {
                optionTexts.add(option.getText()); // Add option text to the list
            }
            return optionTexts;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve dropdown options for locator: " + locator, e);
        }
    }

    public void waitForPageLoadComplete(int timeoutInSeconds) {
        if (timeoutInSeconds <= 0) {
            throw new IllegalArgumentException("Timeout must be greater than zero.");
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(webDriver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete")); // Wait for document readiness
        } catch (Exception e) {
            throw new RuntimeException("Page did not load within " + timeoutInSeconds + " seconds.", e);
        }
    }


}
