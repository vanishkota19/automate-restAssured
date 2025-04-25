package com.restassured.config;

import lombok.Getter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class ConfigManager {
    private static ConfigManager instance;
    private final Properties properties;

    private ConfigManager() {
        properties = new Properties();
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/env.config");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment configuration: " + e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        String env = System.getProperty("env", "qa");
        return getProperty(env + ".base.url");
    }
} 