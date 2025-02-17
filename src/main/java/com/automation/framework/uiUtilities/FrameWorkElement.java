package com.automation.framework.uiUtilities;

import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Reporter;

public class FrameWorkElement extends RemoteWebElement {



    @Override
    public String getText() {
        // Custom implementation of getText()
        String text = super.getText(); // Call the original method
        Reporter.log("Got the text"+ text);
        return text+"From Custom Element".trim();
    }
}
