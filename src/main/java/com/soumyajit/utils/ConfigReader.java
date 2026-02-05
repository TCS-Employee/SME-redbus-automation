package com.soumyajit.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader class reads configuration from config.properties file
 * Uses static initialization block to load properties once at startup
 */
public class ConfigReader {
    private static final Properties properties;

    // Private constructor prevents instantiation
    private ConfigReader() {
    }

    // Static block runs once when class is loaded
    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    /**
     * Gets property value by key from config.properties
     * @param key - property key (e.g., "baseUrl")
     * @return property value as String
     */
    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value.trim();
        } else {
            throw new RuntimeException(key + " not found in config.properties");
        }
    }
}