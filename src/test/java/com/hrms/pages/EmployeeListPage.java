package com.hrms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPage {

    @FindBy(id = "empsearch_id")
    public WebElement idBoxEmpList;

    @FindBy(id = "searchBtn")
    public WebElement searchBtnEmpList;


    public EmployeeListPage(WebDriver driver){
        PageFactory.initElements(driver, this);

    }


}
