package com.hrms.utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CommonMethods {

    protected static WebDriver driver;



    /**
     * this method opens up the browser depending on the browser provided in
     * properties file, and navigates to the url
     */
    @BeforeMethod(alwaysRun = true)
    public static void setUp(){

        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch(ConfigsReader.getPropertyValue("browser").toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Browser is Invalid");


        }

        driver.get(ConfigsReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT,TimeUnit.SECONDS);

    }

    /**
     * this method will close any opened browser
     */
    @AfterMethod(alwaysRun = true)
    public static void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

}
