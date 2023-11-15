package org.example;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import org.wrs.configArduino.SerialLector;
import org.wrs.connectionFactory.ConnectionFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        PanamaHitek_Arduino pha = new PanamaHitek_Arduino();
        SerialLector listener = new SerialLector(pha);
        AtomicReference<String> atomicUid = new AtomicReference<>("defaul value");
        boolean algo = true;

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    pha.arduinoRXTX("COM7", 9600, listener);
                    while (true) {
                        if (listener.getUidmessage() != null && !listener.getUidmessage().isEmpty()) {
                            atomicUid.set(listener.getUidmessage());
                            System.out.println("Mensaje recibido: " + listener.getUidmessage());
                            // Realizar otras operaciones con el mensaje
                            listener.setUidmessage(""); // Limpiar el mensaje

                        }
                        Thread.sleep(100); // Esperar un poco antes de verificar nuevamente
                    }
                } catch (ArduinoException | InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        t.start();

        while (true){
            String uid = atomicUid.get();
            if(!uid.isEmpty()){
                System.out.println("Valor en Main: "+uid);
               t.interrupt();
            }

            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}