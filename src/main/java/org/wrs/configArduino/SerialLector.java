package org.wrs.configArduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.example.Main;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialLector implements SerialPortEventListener {

    private PanamaHitek_Arduino pha;
    private String uidmessage;

    public SerialLector (PanamaHitek_Arduino pha){
        this.pha = pha;
        this.uidmessage = "";
    }

    public String getUidmessage() {
        return uidmessage;
    }

    public void setUidmessage(String uidmessage) {
        this.uidmessage = uidmessage;
    }



    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        try {
            if (this.pha.isMessageAvailable()){

                this.setUidmessage(this.pha.printMessage());
            }
        } catch (SerialPortException | ArduinoException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
