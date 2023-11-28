
package org.wrs.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSend {
    
    
   
    
    public EmailSend(){
        
        
    }
    
    
    public void sendEmail (String addresse, String subject, String message){
        Session session = Session.getDefaultInstance(PropertiesEmailUtil.emailProperties, null);
        System.out.println(PropertiesEmailUtil.getProperties("mail.smtp.userq"));
        try{
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(PropertiesEmailUtil.getProperties("mail.smtp.user"), "Javier Eduardo Mmontes Delgado"));
            msg.addRecipient(Message.RecipientType.TO, 
                    new InternetAddress(addresse, "Señor/ra acodiente"));
            msg.setSubject(subject);
            msg.setText("Señor/ra acodiente \n"+message);
            
            Transport transportMessage = session.getTransport("smtp");
            transportMessage.connect(PropertiesEmailUtil.getProperties("mail.smtp.user"), PropertiesEmailUtil.getProperties("mail.smtp.password"));
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
