package com.automation.framework.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Set;

public class ProjectInputFile {
    private static String inputFilePath;
    private static JsonObject projectInputJson;

    private static void setProjectJson(JsonObject projectJson) {
        projectInputJson = projectJson;
    }

    private static JsonObject getProjectInputJson()
    {
        return projectInputJson;
    }

    public static String getProjectInput(String inputKey){
        return getProjectInputJson().get(inputKey).getAsString();
    }
    public static void loadInputFile() {
        setProjectInputFile(FrameWorkConstants.DEFAULT_PROJECT_INPUT_FILE);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObjectFromFile = gson.fromJson(FrameWorkFileUtils.readFileToString(inputFilePath), JsonObject.class);
        initializeProjectJsonObject(jsonObjectFromFile);
    }

    private static void initializeProjectJsonObject(JsonObject jsonObjectFromFile) {
        JsonObject projectInputJson = new JsonObject();
        String environment = jsonObjectFromFile.get(FrameWorkConstants.ENVIRONMENT).getAsString();
        projectInputJson.addProperty(FrameWorkConstants.ENVIRONMENT, environment);
        JsonObject defaultInputs = jsonObjectFromFile.get(FrameWorkConstants.DEFAULT).getAsJsonObject();
        JsonObject environmentInputs = jsonObjectFromFile.get(environment).getAsJsonObject();
        Set<String> environMentKeySet = new HashSet<>(environmentInputs.keySet());
        Set<String> defaultKeySet = new HashSet<>(defaultInputs.keySet());
        defaultKeySet.addAll(environMentKeySet);
        defaultKeySet.stream().forEach(key -> {
            if (environmentInputs.has(key)) {
                projectInputJson.add(key, environmentInputs.get(key));
            } else {
                projectInputJson.add(key, defaultInputs.get(key));
            }
        });
        setProjectJson(projectInputJson);

    }

    public static String getDefaultInputFileName() {
        return FrameWorkConstants.DEFAULT_PROJECT_INPUT_FILE;
    }

    public static void setProjectInputFile(String projectInputFilePath) {
        String commandLinePath = System.getProperty(FrameWorkConstants.PROJECT_INPUT_FILE_PATH);
        inputFilePath = commandLinePath != null ? commandLinePath : projectInputFilePath;
    }
}
