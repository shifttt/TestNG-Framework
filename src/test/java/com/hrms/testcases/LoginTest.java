package com.hrms.testcases;

import com.hrms.pages.LoginPage;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class LoginTest extends CommonMethods {

    @Test
    public void adminLogin(){

        LoginPage login = new LoginPage();

        sendText(login.username, ConfigsReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigsReader.getPropertyValue("password"));
        click(login.loginBtn);

    }
}
