package com.yarachkin.dock.reader;

import com.yarachkin.dock.filehelper.DockFileHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class DockFileReaderTest {
    private String filePath;
    private String testText;
    private File testFile;
    private List<String> expectedResult;

    @BeforeMethod
    public void setUp() throws Exception {
        testFile = File.createTempFile("dock_file_reader_test", "txt");
        filePath = testFile.getAbsolutePath();

        testText = "30 10 5 7\n" +
                "40 15 10 7\n" +
                "45 35 35 10";

        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(testText);

        }
        DockFileHelper.getInstance().setFilePath(filePath);

        expectedResult = new ArrayList<>();
        expectedResult.add("30 10 5 7");
        expectedResult.add("40 15 10 7");
        expectedResult.add("45 35 35 10");

    }

    @AfterMethod
    public void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get("src/test/resources/testFile.txt"));
        DockFileHelper.getInstance().setDefaultPropertyPath();
        testFile.delete();
    }

    @Test
    public void readFromFileTest() throws Exception {
        assertEquals(DockFileReader.getInstance().readFromFile(), expectedResult);
    }

    @Test
    public void createFileIfNotExistsTest() throws Exception {
        DockFileHelper.getInstance().setFilePath("src/test/resources/testFile.txt");
        DockFileReader.getInstance().createFileIfNotExists();
        assertFalse(Files.notExists(Paths.get("src/test/resources/testFile.txt")));
    }

}