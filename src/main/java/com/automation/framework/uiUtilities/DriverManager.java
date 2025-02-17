package com.automation.framework.uiUtilities;

import com.automation.framework.utils.FrameWorkConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class DriverManager {

    private static final ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();

    public static void initializeBrowser(String browserName) {
        WebDriver driver = switch (browserName) {
            case FrameWorkConstants.FIREFOX -> new FirefoxDriver();
            case FrameWorkConstants.EDGE -> new EdgeDriver();
            default -> new ChromeDriver();
        };
        setThreadLocalWebDriver(driver);
    }

    public static void initializeBrowser(String browserName, AbstractDriverOptions<?> driverOptions){
        WebDriver driver = switch (browserName) {
            case FrameWorkConstants.FIREFOX -> new FirefoxDriver((FirefoxOptions) driverOptions);
            case FrameWorkConstants.EDGE -> new EdgeDriver((EdgeOptions) driverOptions);
            default -> new ChromeDriver((ChromeOptions) driverOptions);
        };
        setThreadLocalWebDriver(driver);
    }

    public static void setThreadLocalWebDriver(WebDriver driver) {
        threadLocalWebDriver.set(driver);
    }

    public static WebDriver getDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        return threadLocalWebDriver.get();
    }

    public static void closeDriver() {
        threadLocalWebDriver.get().quit();
    }


}
