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
import javax.management.Notification;
import raven.toast.Notifications;

public class EmailSend {

    public String messageSend = "Default";

    public EmailSend() {

    }

    public void confMessage(String studentName, String balance) {
        this.messageSend = "Estimado/a Padre de familia.,\n\n"
                + "Espero que este mensaje lo encuentre bien.\n\n"
                + "El motivo de este correo es para informarle respetuosamente sobre un asunto pendiente relacionado con la tienda escolar del Colegio Windsor Royal School.\n\n"
                + "Lamentamos comunicarle que, de acuerdo con nuestros registros, actualmente existe una deuda pendiente asociada con la cuenta de su hijo/a " + studentName + " en la tienda escolar. El monto adeudado y los detalles específicos de la transacción pendiente están detallados a continuación:\n\n"
                + "- Nombre del Estudiante: " + studentName + "\n"
                + "- Monto pendiente: " + balance + "\n\n"
                + "Entendemos que las circunstancias pueden variar y estamos comprometidos a resolver este asunto de manera cordial y eficiente. Le invitamos amablemente a ponerse en contacto con nuestra oficina administrativa lo antes posible para poder ofrecerle la asistencia necesaria y resolver esta situación de la manera más conveniente para usted.\n\n"
                + "Su cooperación y pronta atención a esta notificación serán muy apreciadas. Por favor, no dude en comunicarse con nosotros si necesita más información o tiene alguna pregunta al respecto.\n\n"
                + "Agradecemos su atención y colaboración en este asunto.\n\n"
                + "Atentamente,\n\n"
                + "Windsor Royal School\n";

    }

    public String getMessageSend() {
        return this.messageSend;
    }

    public void sendEmail(String addresse, String subject, String message, Properties prop) {
        Thread thread = new Thread() {

            @Override
            public void run() {

                Session session = Session.getDefaultInstance(prop, null);
                try {

                    Message msg = new MimeMessage(session);
                    msg.setFrom(new InternetAddress(prop.getProperty("mail.smtp.user"), "Javier Eduardo Mmontes Delgado"));
                    msg.addRecipient(Message.RecipientType.TO,
                            new InternetAddress(addresse, "*"));
                    msg.setSubject(subject);
                    msg.setText(message);

                    Transport transportMessage = session.getTransport("smtp");
                    transportMessage.connect(prop.getProperty("mail.smtp.user"), prop.getProperty("mail.smtp.password"));
                    transportMessage.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
                    transportMessage.close();
                    NotificationUtil.show(Notifications.Type.SUCCESS, "Se envio el correo");
                } catch (AddressException e) {
                    e.getMessage();
                    NotificationUtil.show(Notifications.Type.ERROR, "No se pudo enviar el correo");
                    
                } catch (MessagingException e) {
                    e.getMessage();
                    NotificationUtil.show(Notifications.Type.ERROR, "No se pudo enviar el correo");
                } catch (UnsupportedEncodingException e) {
                    e.getMessage();
                    NotificationUtil.show(Notifications.Type.ERROR, "No se pudo enviar el correo");
                }

            }

        };
        thread.start();
    }

}
