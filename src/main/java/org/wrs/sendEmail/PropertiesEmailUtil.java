package org.wrs.sendEmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesEmailUtil {
    
    private final String EMAIL_PROPERTIES = "src/email.properties";
    private final Properties emailProperties = loadEmailProperties();
    
    public PropertiesEmailUtil (){
        
    }
    
    public Properties loadEmailProperties (){
        Properties emailProperties = null;
        try(InputStream input = new FileInputStream(new File(EMAIL_PROPERTIES))){
            emailProperties.load(input);
        }catch(IOException e){
            e.getMessage();
        }
        
        return emailProperties;
    }
    
}
