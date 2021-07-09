package com.hrms.utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CommonMethods {

    public static WebDriver driver;



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

    /**
     * THIS METHOD WILL CLEAR A TEXT BOX AND ENTER TEXT TO IT
     * @param element
     * @param textToSend
     */
    public static void sendText(WebElement element, String textToSend){
        element.clear();
        element.sendKeys(textToSend);
    }

    public static String getValueFromBox(WebElement element){
         String value = element.getText().toString();
        return value;
    }

    /**
     * this method will return an object of Explicit wait with time set to 20 sec
     * @return
     */
    public static WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    /**
     * this method will wait until given element becomes clickable
     * @param element
     */
    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * this method will click on any clickable element
     * @param element
     */
    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    /**
     * this method creates an object of JavaScript Executor
     * @return
     */
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        return js;
    }

    /**
     * this method implements js click on WebElement
     * @param element
     */
    public static void jsClick(WebElement element){


        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    /**
     * this method takes screenshot and saves is to scheenshot directory in our framework root folder
     * @param fileName
     */
    public static void takeScreenshot(String fileName){
        TakesScreenshot ts = (TakesScreenshot)driver;
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try{
        FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + " .png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getTimeStamp(String pattern){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }


}
