package org.example;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.wrs.connectionFactory.ConnectionFactory;
import org.wrs.controllers.StudentController;
import org.wrs.dao.SellDao;
import org.wrs.dao.StudentDao;

import javax.swing.*;

import org.wrs.view.LogView;
import org.wrs.view.InfoSellView;
import org.wrs.view.view;

public class Main {
    public static void main(String[] args) {

         try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

         view vista = new view();
        LogView logView = new LogView();
        InfoSellView overviewView = new InfoSellView();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        StudentDao studentDao = new StudentDao(connectionFactory.getConnection());
        SellDao sellDao = new SellDao(connectionFactory.getConnection());

        StudentController controller = new StudentController(studentDao,sellDao,vista);


        controller.initView();
        PanamaHitek_Arduino pha = new PanamaHitek_Arduino();
        
        vista.setVisible(true);
         

        try {
            pha.arduinoRXTX("COM7", 9600, new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                    if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                        try {
                            if(pha.isMessageAvailable()) {
                                //System.out.println(pha.printMessage());
                                String data = pha.printMessage();
                                controller.setData(data);
                                boolean thereIsStudent = controller.verificarEstudiante(data);

                            }
                        } catch (SerialPortException | ArduinoException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (ArduinoException e) {
            e.printStackTrace();
        }

    }
}