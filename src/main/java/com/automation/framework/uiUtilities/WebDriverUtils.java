package com.automation.framework.uiUtilities;

import com.automation.framework.utils.FrameWorkFileUtils;
import org.openqa.selenium.*;
import org.testng.Reporter;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WebDriverUtils {

    public static void typeText(By inputFieldLocator, String textValue){
        DriverManager.getDriver().findElement(inputFieldLocator).sendKeys(textValue);
        Reporter.log("Entered "+ textValue + "to "+ inputFieldLocator);
    }

    public static void click(By elementToClick){
        DriverManager.getDriver().findElement(elementToClick).click();
        Reporter.log("Clicked  "+ elementToClick);
    }

    public static String getText(By elementLocator){
        Reporter.log("Getting text of "+ elementLocator.toString());
        return DriverManager.getDriver().findElement(elementLocator).getText();

    }

    public static Set<Cookie> getCookies(){
        Reporter.log("Fetching Cookies");
        return DriverManager.getDriver().manage().getCookies();
    }

    public static JavascriptExecutor getJavaScriptExecutor(){
        return ((JavascriptExecutor) DriverManager.getDriver());
    }

    public static <T> T executeJSScript(String script, Object... args) {
        return (T) getJavaScriptExecutor().executeScript(script, args);
    }

    public static Map<String,String> getSessionStorage(){
        String jsScriptForSessionStorage = "var items = {};" +
                "for (var i = 0; i < window.sessionStorage.length; i++) {" +
                "    var key = window.sessionStorage.key(i);" +
                "    items[key] = window.sessionStorage.getItem(key);" +
                "}" +
                "return items;";
        Reporter.log("Seeking for session storage");
        return executeJSScript(jsScriptForSessionStorage);
    }
    public static Map<String,String> getLocalStorage(){
        String jsScriptForLocalStorage = "var items = {};" +
                "for (var i = 0; i < window.localStorage.length; i++) {" +
                "    var key = window.localStorage.key(i);" +
                "    items[key] = window.localStorage.getItem(key);" +
                "}" +
                "return items;";
        Reporter.log("Seeking for local storage");
        return executeJSScript(jsScriptForLocalStorage);
    }

    public static String takeScreenShot(){
        TakesScreenshot takesScreenshot =((TakesScreenshot)DriverManager.getDriver());
        File tempScreenShotPath = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshots/"+getTodayDateInFormat()+"/"+getCurrentTimeTillMilli()+"/"+tempScreenShotPath.getName();
        return FrameWorkFileUtils.copyToNewLocation(tempScreenShotPath,screenshotPath).getAbsolutePath();
    }

    public static String getTodayDateInFormat(){
       return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    public static String getCurrentTimeTillMilli(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss.SSS"));
    }

    public static List<WebElement> getWebElementList(By locator){
        return DriverManager.getDriver().findElements(locator);
    }
    public static List<String> getWebElementsText(By locator){
        return getWebElementList(locator).stream().map(WebElement::getText).toList();
    }

}
