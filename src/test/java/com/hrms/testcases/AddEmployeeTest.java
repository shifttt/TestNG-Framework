package com.hrms.testcases;

import com.hrms.pages.*;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelReading;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {

    @Test(groups = "smoke")
    public void addEmployee() throws InterruptedException {
        //lig to hrms
        LoginPage login = new LoginPage();
        login.login(ConfigsReader.getPropertyValue("username"), ConfigsReader.getPropertyValue("password"));

        //navigate to add employee page
        DashboardPage dash = new DashboardPage();

        waitForClickability(dash.PIMbutton);
        dash.PIMbutton.click();

        waitForClickability(dash.addEmployeeBtn);
        dash.addEmployeeBtn.click();
        //jsClick(dash.PIMbutton);
        //jsClick(dash.addEmployeeBtn);


        //add employee
        AddEmployeePage addEmp = new AddEmployeePage(driver);
        sendText(addEmp.firstNameTextBox, "Steward");
        sendText(addEmp.lastNameTextBox, "Thomson");

        click(addEmp.saveButton);

        //validation


        SoftAssert addEmpSoftAssertion = new SoftAssert();

    }

    @Test
    public void addMultipleEmployees() throws InterruptedException {
        LoginPage login = new LoginPage();
        login.login(ConfigsReader.getPropertyValue("username"), ConfigsReader.getPropertyValue("password"));

        //navigate to add emp page

        DashboardPage dashboardPage = new DashboardPage();
        PersonalDetailPage personalDetailPage = new PersonalDetailPage();


        //add emp

        List<Map<String, String>> newEmp = ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, "EmployeeData");

        AddEmployeePage addEmployeePage =  new AddEmployeePage(driver);
        EmployeeListPage employeeListPage = new EmployeeListPage(driver);

        List<String> empIDlist = new ArrayList<>();
        SoftAssert softAssert = new SoftAssert();

        Iterator<Map<String, String>> it = newEmp.iterator();

        while(it.hasNext()){

            jsClick(dashboardPage.PIMbutton);
            jsClick(dashboardPage.addEmployeeBtn);

            Map<String, String> mapNewEmployee = it.next();
            sendText(addEmployeePage.firstNameTextBox, mapNewEmployee.get("FirstName"));
            sendText(addEmployeePage.middleNameTextBox, mapNewEmployee.get("MiddleName"));
            sendText(addEmployeePage.lastNameTextBox, mapNewEmployee.get("LastName"));
            String employeeIDValue = addEmployeePage.empIDTextbox.getAttribute("value");
            sendText(addEmployeePage.photograph, mapNewEmployee.get("Photograph"));
            //click on checkbox
            if(!addEmployeePage.createLoginDetails.isSelected()){
                addEmployeePage.createLoginDetails.click();
            }

            sendText(addEmployeePage.usernameCreate, mapNewEmployee.get("Username"));
            sendText(addEmployeePage.userPassword, mapNewEmployee.get("Password"));
            sendText(addEmployeePage.rePassword, mapNewEmployee.get("Password"));
            click(addEmployeePage.saveButton);

            //navigate to the emp list

            jsClick(dashboardPage.PIMbutton);
            jsClick(dashboardPage.empListBtn);
            sendText(employeeListPage.idBoxEmpList, employeeIDValue);
            click(employeeListPage.searchBtnEmpList);

            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id = 'resultTable']/tbody/tr"));

            for(int i = 0; i<rowData.size(); i++){
                String rowText = rowData.get(i).getText();

                System.out.println(rowText);

                String expectedEmployeeDetails = employeeIDValue +" "+ mapNewEmployee.get("FirstName")+" "+mapNewEmployee.get("MiddleName")+" "+ mapNewEmployee.get("LastName");

                softAssert.assertEquals(rowText, expectedEmployeeDetails, "Data is NOT MATCHED");


            }

        }




    }




}

