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
    private boolean isAuthenticated = false;

    private ISerialComunication auth;
    private ISerialComunication register;
    private ISerialComunication purchase;
    private ISerialComunication recharge;

    public SerialLector(PanamaHitek_Arduino pha) {
        this.pha = pha;
        mode = true;
    }

    public void toggleMode() {
        this.mode = !this.mode;
    }

    public void setAuthInterface(ISerialComunication auth) {
        this.auth = auth;
    }
    
    public void authenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setRegisterInterface(ISerialComunication register) {
        this.register = register;
    }

    public void setRechargeInterface(ISerialComunication recharge) {
        this.recharge = recharge;
    }

    public void setPurchaseInterface(ISerialComunication purchase) {
        this.purchase = purchase;
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

                    if(!isAuthenticated){
                        auth.receiveDataSerialPort(data);
                        return;
                    }
                    
                    register.receiveDataSerialPort(data);

                    if (mode) {
                        purchase.receiveDataSerialPort(data);
                    } else {
                        recharge.receiveDataSerialPort(data);
                    }
                }
            }
        } catch (SerialPortException | ArduinoException ex) {
            Logger.getLogger(SerialLector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
