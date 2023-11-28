package org.wrs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesEmailUtil {
    
    private final static String EMAIL_PROPERTIES = "src/email.properties";
    public final static Properties emailProperties = loadEmailProperties();
    
    public PropertiesEmailUtil (){
        
    }
    
    public static Properties loadEmailProperties (){
        Properties emailProperties = new Properties();
        try(InputStream input = new FileInputStream(new File(EMAIL_PROPERTIES))){
            emailProperties.load(input);
        }catch(IOException e){
            e.getMessage();
        }
        
        return emailProperties;
    }
    
    public static String getProperties(String key){
        return emailProperties.getProperty(key);
    }
    
}
