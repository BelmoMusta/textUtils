package musta.belmo.utils.textutils.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by DELL on 07/08/2018.
 */
public class Commons {
    public static String readFromProperties(String key) throws IOException {
        InputStream resourceAsStream = Commons.class.getClassLoader().getResource("application.properties").openStream();
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        return properties.getProperty(key);
    }
}
