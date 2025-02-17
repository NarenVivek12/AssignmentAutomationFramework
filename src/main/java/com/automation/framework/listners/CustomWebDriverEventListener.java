package com.automation.framework.listners;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Reporter;

public class CustomWebDriverEventListener  implements WebDriverListener {

    @Override
    public void beforeGetText(WebElement element) {
        Reporter.log("Trying to  get text of an element");
    }

   @Override
    public void afterGetText(WebElement element, String result) {
       Reporter.log("Getting text of an element was "+ result);
   }


}
