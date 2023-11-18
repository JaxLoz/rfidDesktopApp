package org.wrs.configArduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.example.Main;

import java.io.Serial;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialLector implements SerialPortEventListener {

    private PanamaHitek_Arduino pha;
    private SerialComunicationInterface sci;

    private String data;

    public SerialLector (PanamaHitek_Arduino pha){

        this.pha = pha;
        this.data = "Defaul dataSerialLector";
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSerialComunicationInterface (SerialComunicationInterface sci){
        this.sci = sci;
    }

    public void startConnectionArduino (){
        try {
            pha.arduinoRXTX("COM7", 9600, this);
        } catch (ArduinoException ex) {

        }
    }
    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        try {
            if (this.pha.isMessageAvailable()){
                data = pha.printMessage();
                //sci.setValueArduino(pha.printMessage());
            }
        } catch (SerialPortException | ArduinoException ex) {
            Logger.getLogger(SerialLector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
