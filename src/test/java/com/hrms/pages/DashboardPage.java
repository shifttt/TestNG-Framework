package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends CommonMethods {

    @FindBy(id="welcome")
    WebElement welcomeMessage;


    DashboardPage(){
        PageFactory.initElements(driver, this);
    }

}
