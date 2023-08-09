package UtilitiesFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class PropertyLoaderFactory {

    private Properties properties;
    private String propertiesPath;

    public Properties getPropertyFile(String filename) throws Exception {

        String path = null;

        try {

            path = System.getProperty("user.dir") + File.separator + "src/test/resources/Properties/";

        } catch (Exception e) {

            System.err.println("Could not locate .properties file");
            e.printStackTrace();
        }
        return getProperty(path, filename);
    }

    public Properties getTestDataPropertyFile(String filename) throws Exception {

        String path = null;

        try {

            path = System.getProperty("user.dir") + File.separator + "src/test/resources/Properties/TestData/";

        } catch (Exception e) {

            System.err.println("Could not locate Test Data properties file");
            e.printStackTrace();
        }
        return getProperty(path, filename);
    }

    public Properties getLocatorPropertyFile(String filename) throws Exception {

        String path = null;

        try {

            path = System.getProperty("user.dir") + File.separator + "src/test/resources/Properties/Locators/";

        } catch (Exception e) {

            System.err.println("Could not locate Locators properties file");
            e.printStackTrace();
        }
        return getProperty(path, filename);
    }

    public Properties getProperty(String path, String filename) throws Exception {

        propertiesPath = path;

        try {

            File file;

            file = new File(propertiesPath + filename);

            FileInputStream fileInput = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInput);

        } catch (Exception e) {

            System.err.println("Could not get Properties from the .properties file");
            e.printStackTrace();
            throw e;
        }
        return properties;
    }
}
