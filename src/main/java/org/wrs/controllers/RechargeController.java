
package org.wrs.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.configArduino.ISerialComunication;

/**
 *
 * @author C.Mateo
 */
public class RechargeController implements ActionListener, ISerialComunication{

    

    @Override
    public void receiveDataSerialPort(String data) {
        System.out.println("hola desde la recargas");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
