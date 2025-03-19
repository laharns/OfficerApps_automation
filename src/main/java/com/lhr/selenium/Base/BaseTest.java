package com.lhr.selenium.Base;

import com.lhr.selenium.PageObjects.LandingPage;
import com.lhr.selenium.utils.PropertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;
    protected PropertyFileReader propertyFileReader;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    public WebDriver initializeDriver() throws IOException {

        propertyFileReader = new PropertyFileReader("src/main/resources/GlobalData.properties");

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : propertyFileReader.getProperty("browser");
        boolean isHeadless = Boolean.parseBoolean(propertyFileReader.getProperty("headless"));
        boolean maximizeWindow = Boolean.parseBoolean(propertyFileReader.getProperty("maximizeWindow"));
        int implicitWait = Integer.parseInt(propertyFileReader.getProperty("implicitWait"));
        if(browserName.contains("chrome")){
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs=new HashMap<String,Object>();
            prefs.put("profile.default_content_setting_values.notifications", 1);
            options.setExperimentalOption("prefs",prefs);
            WebDriverManager.chromedriver().setup();
            if(isHeadless){
                options.addArguments("headless");
                options.addArguments("--disable-gpu");
            }
            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440,900));
        }else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        if (maximizeWindow && !isHeadless) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    //@AfterMethod
//    public void tearDown(){
//        if (driver != null) {
//            System.out.println("Not Null");
//            driver.close();
//        }else{
//            System.out.println("Null");
//        }
   // }
}
