package org.wrs.connectionFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propertiesUtil {

    private static final String PROPERTIES_FILE = "src/application.properties";
    private static final Properties properties = loadProperties();

    private propertiesUtil (){

    }

    public static Properties loadProperties (){
        Properties props = new Properties();
        try(InputStream input = new FileInputStream(new File(PROPERTIES_FILE))){
          props.load(input);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return props;
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
