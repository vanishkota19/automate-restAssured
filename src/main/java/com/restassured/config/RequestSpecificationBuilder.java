package com.restassured.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {
    private static final ConfigManager config = ConfigManager.getInstance();

    public static RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(config.getBaseUrl())
                .addHeader("Content-Type", config.getProperty("Content-Type"))
                .addHeader("Accept", config.getProperty("Accept"))
                .build();
    }

    public static RequestSpecification getAuthenticatedRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(config.getBaseUrl())
                .addHeader("Content-Type", config.getProperty("Content-Type"))
                .addHeader("Accept", config.getProperty("Accept"))
                .addHeader("Authorization", "Bearer " + getAuthToken())
                .build();
    }

    private static String getAuthToken() {
        // Implement your authentication logic here
        // This is a placeholder that should be replaced with actual authentication
        return "your-auth-token";
    }
} 