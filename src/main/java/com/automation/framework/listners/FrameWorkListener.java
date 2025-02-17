package com.automation.framework.listners;

import com.automation.framework.uiUtilities.WebDriverUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class FrameWorkListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        String screenshotPath = WebDriverUtils.takeScreenShot();
        Reporter.log("<a href='file://" + screenshotPath + "' target='_blank'>"
                + "<img src='file://" + screenshotPath  + "' height='200' width='300'/></a>");
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        String screenshotPath = WebDriverUtils.takeScreenShot();
        Reporter.log("<a href='file://" + screenshotPath + "' target='_blank'>"
                + "<img src='file://" + screenshotPath + "' height='200' width='300'/></a>");
    }
}
