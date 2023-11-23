package org.wrs.configArduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.example.Main;

import java.io.Serial;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialLector implements SerialPortEventListener {

    private PanamaHitek_Arduino pha;

    /**
     * read mode Arduino
     * mode = true: mode purchase is active
     * mode = false: mode recharge is active
     */
    private boolean mode;

    private SerialComunicationInterface purchase;
    private SerialComunicationInterface recharge;
    private SerialComunicationInterface updateStudent;


    public SerialLector(PanamaHitek_Arduino pha) {
        mode = true;
        this.pha = pha;
    }

    public void toggleMode() {
        this.mode = !this.mode;
    }

    public void setRechargeInterface(SerialComunicationInterface iRecharge) {
        this.recharge = iRecharge;
    }

    public void setPurchaseInterface(SerialComunicationInterface iPurchase) {
        this.purchase = iPurchase;
    }

    public void setUpdateStudent(SerialComunicationInterface updateStudent) {
        this.updateStudent = updateStudent;
    }

    public void startConnectionArduino() {
        try {
            pha.arduinoRXTX("COM7", 9600, this);
        } catch (ArduinoException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        try {
            if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                if (pha.isMessageAvailable()) {
                    String data = pha.printMessage();

                    if (mode) {
                        purchase.setValueArduino(data);
                    } else {
                        recharge.setValueArduino(data);
                    }
                    updateStudent.setValueArduino(data);
                }
            }
        } catch (SerialPortException | ArduinoException ex) {
            Logger.getLogger(SerialLector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
