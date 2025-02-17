package com.automation.framework.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class FrameWorkFileUtils {


    public static String readFileToString(String inputFilePath)  {
        File file;
        try {
            file = new File(ClassLoader.getSystemResource(inputFilePath).toURI().getPath());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
           return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  File copyToNewLocation(File fileToCopy, String newLocationPath){
        try {
            File newFile = new File(newLocationPath);
            FileUtils.copyFile(fileToCopy,newFile);
            return newFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
