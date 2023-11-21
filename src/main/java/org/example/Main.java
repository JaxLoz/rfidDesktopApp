package org.example;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.wrs.configArduino.ArduinoController;
import org.wrs.configArduino.SerialComunicationInterface;
import org.wrs.configArduino.SerialLector;
import org.wrs.connectionFactory.ConnectionFactory;
import org.wrs.controllers.RechargeController;
import org.wrs.controllers.StudentController;
import org.wrs.dao.RechargeDao;
import org.wrs.dao.StudentDao;

import javax.swing.*;

import org.wrs.view.LogView;
import org.wrs.view.RechargeView;
import org.wrs.view.SellView;
import org.wrs.view.View;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());
            //UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }


        ConnectionFactory connectionFactory = new ConnectionFactory();

        //create serial lector and init arduino connection
        SerialLector serialLector = new SerialLector(new PanamaHitek_Arduino());
        serialLector.startConnectionArduino();
        ArduinoController arduinoController = new ArduinoController(serialLector);

        //student view and controller
        View vista = new View();
        StudentDao studentDao = new StudentDao(connectionFactory.getConnection());
        StudentController controller = new StudentController(studentDao, vista);

        //recharge view and controller
        RechargeView rechargeView = new RechargeView(vista);
        RechargeDao rechargeDao = new RechargeDao(connectionFactory.getConnection());
        RechargeController rechargeController = new RechargeController(studentDao,rechargeDao, rechargeView);




        vista.setActionListener(rechargeController);
        vista.setActionListener(arduinoController);


        controller.initView();


        vista.setVisible(true);


        SerialComunicationInterface iPurchase = new SerialComunicationInterface() {
            @Override
            public void setValueArduino(String data) {
                controller.setData(data);
                controller.verificarEstudiante(data);
            }
        };



        serialLector.setPurchaseInterface(iPurchase);
        serialLector.setRechargeInterface(rechargeController);


    }
}