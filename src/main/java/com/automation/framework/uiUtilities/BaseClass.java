package com.automation.framework.uiUtilities;

import com.automation.framework.utils.FrameWorkConstants;
import com.automation.framework.utils.ProjectInputFile;
import com.automation.framework.listners.FrameWorkListener;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(FrameWorkListener.class)
public class BaseClass   {



    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Executing Before Suite");
        ProjectInputFile.loadInputFile();
        Reporter.log("Executing in environment" + ProjectInputFile.getProjectInput(FrameWorkConstants.ENVIRONMENT));
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        Reporter.log("Starting Execution of testcase : " + method.getName());
        DriverManager.initializeBrowser(ProjectInputFile.getProjectInput(FrameWorkConstants.BROWSER));
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().get(ProjectInputFile.getProjectInput(FrameWorkConstants.BASE_URL));
    }


    @AfterMethod
    public void afterMethod(){
        DriverManager.closeDriver();
    }
}
