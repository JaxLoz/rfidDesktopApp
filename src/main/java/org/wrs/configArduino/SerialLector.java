package org.wrs.configArduino;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SerialLector implements SerialPortEventListener {

    private final PanamaHitek_Arduino pha;

    /**
     * read mode Arduino mode = true: mode purchase is active mode = false: mode
     * recharge is active
     */
    private boolean mode;

    private ISerialComunication purchase;
    private ISerialComunication recharge;
    private ISerialComunication updateStudent;

    public SerialLector(PanamaHitek_Arduino pha) {
        this.pha = pha;
        mode = true;
    }

    public void toggleMode() {
        this.mode = !this.mode;
    }

    public void setRechargeInterface(ISerialComunication iRecharge) {
        this.recharge = iRecharge;
    }

    public void setPurchaseInterface(ISerialComunication iPurchase) {
        this.purchase = iPurchase;
    }

    public void setUpdateStudent(ISerialComunication updateStudent) {
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

                    purchase.receiveDataSerialPort(data);

                    recharge.receiveDataSerialPort(data);

                    //updateStudent.receiveDataSerialPort(data);
                }
            }
        } catch (SerialPortException | ArduinoException ex) {
            Logger.getLogger(SerialLector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
