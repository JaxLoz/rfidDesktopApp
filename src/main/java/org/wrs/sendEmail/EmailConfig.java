
package org.wrs.sendEmail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailConfig {
    
    private PropertiesEmailUtil propertiesEmailUtil;
    private Properties emailProperties = propertiesEmailUtil.loadEmailProperties();
    
    
    public EmailConfig(){
        propertiesEmailUtil = new PropertiesEmailUtil();
        
    }
    
    
    public void sendEmail (String addresse, String recipientName, String subject, String message){
        Session session = Session.getDefaultInstance(emailProperties, null);
        
        try{
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(emailProperties.getProperty("mail.smtp.user"), "Javier Eduardo Mmontes Delgado"));
            msg.addRecipient(Message.RecipientType.TO, 
                    new InternetAddress(recipientName, addresse));
            msg.setSubject(subject);
            msg.setText(message);
            
            Transport transportMessage = session.getTransport("smtp");
            transportMessage.connect(emailProperties.getProperty("mail.smtp.user"), emailProperties.getProperty("mail.smtp.password"));
            transportMessage.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
            transportMessage.close();
            System.out.println("Se envio el mensaje de correo");
            
        }catch (AddressException e){
            e.getMessage();
        }catch (MessagingException e){
            e.getMessage();
        }catch (UnsupportedEncodingException e){
            e.getMessage();
        }
    }
    
}
