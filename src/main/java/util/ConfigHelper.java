package util;

import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    public static String retrieveProperty(String propertyName) throws IOException {
        final Properties properties = new Properties();
        properties.load(DriverManager.class.getClassLoader().getResourceAsStream("project.properties"));
        return properties.getProperty(propertyName);
    }

}
