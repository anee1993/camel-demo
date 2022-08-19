package util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileParser {

    private final Properties properties = new Properties();
    public Properties getProperties() {
        String propertiesFileName = "application.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);

        try {
            if (inputStream != null) {
                properties.load(inputStream);
            }
            else {
                throw new RuntimeException("Properties file does not exist!");
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return properties;
    }
}
