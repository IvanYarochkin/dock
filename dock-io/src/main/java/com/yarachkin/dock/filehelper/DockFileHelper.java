package com.yarachkin.dock.filehelper;

import com.yarachkin.dock.exception.IoDockException;

import java.io.IOException;
import java.util.Properties;

public class DockFileHelper {
    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";

    private String propertyPath;
    private Properties properties;
    private String filePath = "";

    private DockFileHelper() {
        propertyPath = FILE_PROPERTIES;
    }

    private static class SingletonHolder {
        private static final DockFileHelper INSTANCE = new DockFileHelper();
    }

    public static DockFileHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setPropertyPath(String propertyPath) throws IoDockException {
        this.propertyPath = propertyPath;
        filePath = "";
        loadProperties();
    }

    public void setDefaultPropertyPath() throws IoDockException {
        this.propertyPath = FILE_PROPERTIES;
        filePath = "";
        loadProperties();
    }

    public String acquireFilePath() throws IoDockException {
        if ( !filePath.isEmpty() ) {
            return filePath;
        }

        if ( properties == null ) {
            loadProperties();
        }
        return properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void loadProperties(Properties properties) {
        filePath = "";
        this.properties = properties;
    }

    private void loadProperties() throws IoDockException {
        try {
            properties = new Properties();
            properties.load(DockFileHelper.class.getResourceAsStream(propertyPath));
        } catch (IOException e) {
            throw new IoDockException("Unable to open " + FILE_PROPERTIES, e);
        }
    }
}
