package com.example.refactor.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class ExampleFileUtils {

    public static JSONObject getJsonFromFile(File inputSource) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(new FileReader(inputSource));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File getFileFromResources(String fileName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            return new File(resource.getFile());
        } else {
            throw new IllegalArgumentException("Missing file");
        }
    }

}
