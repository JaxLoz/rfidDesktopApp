package org.example;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.wrs.configArduino.ArduinoController;
import org.wrs.configArduino.SerialComunicationInterface;
import org.wrs.configArduino.SerialLector;
import org.wrs.connectionFactory.ConnectionFactory;
import org.wrs.controllers.StudentController;
import org.wrs.dao.StudentDao;

import javax.swing.*;

import org.wrs.view.LogView;
import org.wrs.view.SellView;
import org.wrs.view.View;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }


        //create serial lector and init arduino connection
        SerialLector serialLector = new SerialLector(new PanamaHitek_Arduino());
        serialLector.startConnectionArduino();



        View vista = new View();
        LogView logView = new LogView();
        SellView overviewView = new SellView();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        StudentDao studentDao = new StudentDao(connectionFactory.getConnection());

        StudentController controller = new StudentController(studentDao, vista);

        ArduinoController arduinoController = new ArduinoController(serialLector);
        vista.setActionListener(arduinoController);


        controller.initView();

        //PanamaHitek_Arduino pha = new PanamaHitek_Arduino();

        vista.setVisible(true);


        SerialComunicationInterface iPurchase = new SerialComunicationInterface() {
            @Override
            public void setValueArduino(String data) {
                controller.setData(data);
                controller.verificarEstudiante(data);
            }
        };

        SerialComunicationInterface iRecharge = new SerialComunicationInterface() {
            @Override
            public void setValueArduino(String data) {
                JOptionPane.showMessageDialog(null, data);
            }
        };

        serialLector.setPurchaseInterface(iPurchase);
        serialLector.setRechargeInterface(iRecharge);


    }
}