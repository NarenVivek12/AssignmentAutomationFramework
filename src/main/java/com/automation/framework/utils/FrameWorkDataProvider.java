package com.automation.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.testng.ITestNGMethod;
import org.testng.annotations.DataProvider;

import java.util.Optional;

public class FrameWorkDataProvider {


    @DataProvider(name = "FrameWorkDataProvider")
    public static Object[][] dataProvider(ITestNGMethod iTestNGMethod) {

        String testCaseName = iTestNGMethod.getMethodName();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray testcaseDataArray = gson.fromJson(FrameWorkFileUtils.readFileToString(FrameWorkConstants.TESTCASE_INPUT_FILE), JsonArray.class);
        Optional<JsonElement> testCaseJsonElement = testcaseDataArray.asList().stream().filter(jsonElement -> jsonElement.getAsJsonObject().get(FrameWorkConstants.TESTCASE_NAME).getAsString().equals(testCaseName)).findFirst();
        if (testCaseJsonElement.isPresent()) {
            JsonArray testCaseInputList = testCaseJsonElement.get().getAsJsonObject().get(FrameWorkConstants.TEST_INPUT_KEY).getAsJsonArray();
            Object[][] data = new Object[testCaseInputList.size()][1];
            for (int i = 0; i < testCaseInputList.size(); i++) {
                data[i][0] = testCaseInputList.get(i);
            }
            return data;
        } else {
            return null;
        }
    }
}

