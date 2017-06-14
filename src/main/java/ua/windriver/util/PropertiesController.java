package ua.windriver.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesController {

    private static final Logger log = LoggerFactory.getLogger(PropertiesController.class);
    private static PropertiesController instance;
    private final Properties properties = new Properties();
    private static final String PROPERTIES_FOLDER_PATH;
    private static final String PROPERTY_FILE_PATH;

    static {
        String configFilePath = System.getProperty("windriver.config.file.path");
        String configFileName = System.getProperty("windriver.config.file.name");

        if (configFilePath == null) PROPERTIES_FOLDER_PATH = "src/main/resources";
        else PROPERTIES_FOLDER_PATH = configFilePath;

        if (configFileName == null) PROPERTY_FILE_PATH = PROPERTIES_FOLDER_PATH + "/windriver.properties";
        else PROPERTY_FILE_PATH = PROPERTIES_FOLDER_PATH + "/" + configFileName;
    }

    private PropertiesController() {
        loadProperties(PROPERTY_FILE_PATH);
    }

    private static PropertiesController getInstance() {
        if (instance == null) {
            instance = new PropertiesController();
        }
        return instance;
    }

    public static String getProperty(final String propertyName) {
        return getInstance().properties.getProperty(propertyName);
    }

    private void loadProperties(final String path) {
        final File file = new File(path);
        try {
            final FileInputStream propFile = new FileInputStream(file);
            properties.load(propFile);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Can't load properties from file " + PROPERTY_FILE_PATH, e);
        }
    }
}
