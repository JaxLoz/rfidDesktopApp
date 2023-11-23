package org.example;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.panamahitek.PanamaHitek_Arduino;
import org.wrs.configArduino.ArduinoController;
import org.wrs.configArduino.SerialLector;
import org.wrs.connectionFactory.ConnectionFactory;
import org.wrs.controllers.RechargeController;
import org.wrs.controllers.StudentController;
import org.wrs.controllers.UpdateInfoController;
import org.wrs.dao.SellDao;
import org.wrs.dao.RechargeDao;
import org.wrs.dao.StudentDao;

import javax.swing.*;

import org.wrs.view.*;


public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());

            ConnectionFactory connectionFactory = new ConnectionFactory();
            SellDao sellDao = new SellDao(connectionFactory.getConnection());


            //create serial lector and init arduino connection
            SerialLector serialLector = new SerialLector(new PanamaHitek_Arduino());
            serialLector.startConnectionArduino();
            ArduinoController arduinoController = new ArduinoController(serialLector);

            View vista = new View();
            StudentDao studentDao = new StudentDao(connectionFactory.getConnection());
            StudentController controller = new StudentController(studentDao, sellDao, vista);

            //recharge view and controller
            RechargeView rechargeView = new RechargeView(vista);
            RechargeDao rechargeDao = new RechargeDao(connectionFactory.getConnection());
            RechargeController rechargeController = new RechargeController(studentDao, rechargeDao, rechargeView, controller);

            //Update student
            updateUidView updateView = new updateUidView();
            UpdateInfoController updateInfoController = new UpdateInfoController(studentDao, updateView, controller);
            controller.setUpdateInfoController(updateInfoController);

            vista.setActionListener(rechargeController);
            vista.setActionListener(arduinoController);


            controller.initView();


            vista.setVisible(true);


            serialLector.setPurchaseInterface(controller);
            serialLector.setRechargeInterface(rechargeController);
            serialLector.setUpdateStudent(updateInfoController);

        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

    }
}