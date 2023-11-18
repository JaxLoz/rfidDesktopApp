package org.example;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.wrs.connectionFactory.ConnectionFactory;
import org.wrs.controllers.LogController;
import org.wrs.controllers.OverViewController;
import org.wrs.controllers.StudentController;
import org.wrs.dao.StudentDao;
import org.wrs.models.Student;

import javax.swing.*;

import org.wrs.view.LogView;
import org.wrs.view.OverviewView;
import org.wrs.view.view;

public class Main {
    public static void main(String[] args) {

         try {
            UIManager.setLookAndFeel( new FlatMacLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

         view vista = new view();
        LogView logView = new LogView();
        OverviewView overviewView = new OverviewView();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        StudentDao studentDao = new StudentDao(connectionFactory.getConnection());

        StudentController controller = new StudentController(studentDao, vista);
        LogController logController = new LogController(studentDao, logView, controller);
        OverViewController overViewController = new OverViewController(studentDao, overviewView);

        logView.setActionListener(logController);
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
                                boolean thereIsStudent = controller.verificarEstudiante(data);
                                if(thereIsStudent){
                                    overViewController.getStudent(data);
                                    overviewView.setVisible(true);
                                }else{
                                    logView.setVisible(true);
                                    logController.setTextUid(data);
                                }
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