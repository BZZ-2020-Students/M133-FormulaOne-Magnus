package ch.formula.one.service;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Configuration
 *
 * @author Magnus Götz
 * @version 1.0
 * @since 2022-05-23
 */
@ApplicationPath("/formula")
public class Config extends Application {
    private static final String PROPERTIES_PATH = "C:\\Github\\M133-FormulaOne-Magnus\\formulaOne\\formulaOne\\src\\main\\resources\\formulaOne.properties";
//    private static final String PROPERTIES_PATH = "C:\\Users\\MGGG\\Documents\\GitHub\\M133-FormulaOne-Magnus\\formulaOne\\formulaOne\\src\\main\\resources\\formulaOne.properties";

    private static Properties properties = null;

    /**
     * Gets the value of a property
     *
     * @param property the key of the property to be read
     * @return the value of the property
     */
    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }

    /**
     * reads the properties file
     */
    private static void readProperties() {

        InputStream inputStream;
        try {
            inputStream = Files.newInputStream(Paths.get(PROPERTIES_PATH));
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Sets the properties
     *
     * @param properties the value to set
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}
