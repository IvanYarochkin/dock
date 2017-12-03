package com.yarachkin.dock.filehelper;

import com.yarachkin.dock.exception.IoDockException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class DockFileHelperTest {
    private String filePath;
    private Properties properties;

    @BeforeMethod
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(DockFileHelperTest.class.getResourceAsStream("/file_helper_test.properties"));
        DockFileHelper.getInstance().loadProperties(properties);
        filePath = properties.getProperty("file.path") + properties.getProperty("file.name");

        Files.createFile(Paths.get(filePath));

    }

    @AfterMethod
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filePath));
        DockFileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void acquireFilePathTest() throws IoDockException {
        assertEquals(DockFileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void loadPropertiesTest() throws IoDockException {
        DockFileHelper.getInstance().loadProperties(properties);
        assertEquals(DockFileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void setDefaultPropertyPathTest() throws IoDockException, IOException {
        DockFileHelper.getInstance().setDefaultPropertyPath();
        Properties defaultProperties = new Properties();
        defaultProperties.load(DockFileHelper.class.getResourceAsStream("/file.properties"));
        String defaultPath = defaultProperties.getProperty("file.path") + defaultProperties.getProperty("file.name");
        assertEquals(DockFileHelper.getInstance().acquireFilePath(), defaultPath);
    }

    @Test
    public void setPropertyPathTest() throws IoDockException {
        DockFileHelper.getInstance().setPropertyPath("/file_helper_test.properties");

        assertEquals(DockFileHelper.getInstance().acquireFilePath(), filePath);
    }
}