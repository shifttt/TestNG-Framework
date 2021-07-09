package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage  {

    @FindBy(id = "firstName")
    public WebElement firstNameTextBox;

    @FindBy(id = "middleName")
    public WebElement middleNameTextBox;

    @FindBy(id = "lastName")
    public WebElement lastNameTextBox;

    @FindBy(id = "employeeId")
    public WebElement empIDTextbox;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(id = "chkLogin")
    public WebElement createLoginDetails;

    @FindBy(id = "photofile")
    public WebElement photograph;

    @FindBy(id = "user_name")
    public WebElement usernameCreate;

    @FindBy(id = "user_password")
    public  WebElement userPassword;

    @FindBy(id = "re_password")
    public WebElement rePassword;





    public AddEmployeePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
}
