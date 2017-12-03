package com.yarachkin.dock.reader;

import com.yarachkin.dock.exception.IoDockException;
import com.yarachkin.dock.filehelper.DockFileHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class DockFileReader {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String FILE_MESSAGE = "File ";

    private DockFileReader() {

    }

    private static class SingletonHolder {
        private static final DockFileReader INSTANCE = new DockFileReader();
    }

    public static DockFileReader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<String> readFromFile() throws IoDockException {
        createFileIfNotExists();
        try {
            List<String> lines = Files.lines(Paths.get(DockFileHelper.getInstance().acquireFilePath()))
                    .filter(Strings::isNotEmpty)
                    .collect(Collectors.toList());

            return lines;
        } catch (IOException e) {
            throw new IoDockException("Read error", e);
        }
    }

    public void createFileIfNotExists() throws IoDockException {
        String filePath = DockFileHelper.getInstance().acquireFilePath();
        try {
            Path cachePath = Paths.get(filePath);

            if ( Files.notExists(cachePath) ) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " does not exist.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " created.");
            }
        } catch (IOException e) {
            throw new IoDockException("Unable to create " + filePath, e);
        }
    }
}
