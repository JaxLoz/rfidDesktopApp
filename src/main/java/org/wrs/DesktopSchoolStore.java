package org.wrs;


import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Font;
import org.wrs.configArduino.ArduinoController;
import org.wrs.configArduino.SerialLector;
import org.wrs.connectionFactory.ConnectionFactory;
import org.wrs.controllers.RechargeController;
import org.wrs.controllers.StudentController;
import org.wrs.controllers.UpdateInfoController;
import org.wrs.dao.PurchaseDao;
import org.wrs.dao.RechargeDao;
import org.wrs.dao.StudentDao;
import javax.swing.*;
import org.wrs.controllers.PurchaseController;
import org.wrs.controllers.SearchStudentController;
import org.wrs.controllers.StudentControllerV2;
import org.wrs.service.PurchaseService;
import org.wrs.view.Application;
import org.wrs.view.UpdateUidView;
import org.wrs.view.RechargeView;
import org.wrs.view.View;
import raven.application.form.other.PurchaseForm;
import raven.application.form.other.StudentForm;

public class DesktopSchoolStore {

    public static void main(String[] args) {

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        
        //MainJFrame and Forms
        Application app = Application.getInstance();
        
        StudentForm studentForm = app.getMainForm().getStudentForm();
        
        PurchaseForm purchaseForm = app.getMainForm().getPurcharseForm();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        PurchaseDao purchaseDao = new PurchaseDao(connectionFactory.getConnection());

        //create serial lector and init arduino connection
        SerialLector serialLector = new SerialLector(new PanamaHitek_Arduino());
        serialLector.startConnectionArduino();
        ArduinoController arduinoController = new ArduinoController(serialLector);

        //Student
        View vista = new View();
        StudentDao studentDao = new StudentDao(connectionFactory.getConnection());
        StudentController controller = new StudentController(studentDao, purchaseDao, vista);

        StudentControllerV2 studentControllerV2 = new StudentControllerV2(studentDao, studentForm);
        studentForm.setActionListener(studentControllerV2);
        
        //Student search controller 
        SearchStudentController searchStudentController = new SearchStudentController(studentDao, studentForm);
        studentForm.setISearchStudent(searchStudentController);

        //recharge view and controller
        RechargeView rechargeView = new RechargeView(vista);
        RechargeDao rechargeDao = new RechargeDao(connectionFactory.getConnection());
        RechargeController rechargeController = new RechargeController(studentDao, rechargeDao, rechargeView, controller);
        
       
        //Update student
        UpdateUidView updateView = new UpdateUidView();
        UpdateInfoController updateInfoController = new UpdateInfoController(studentDao, updateView, controller);
        controller.setUpdateInfoController(updateInfoController);

        vista.setActionListener(rechargeController);
        vista.setActionListener(arduinoController);
        controller.initView();
        
        
        //Purchase controller
        PurchaseService purchaseService = new PurchaseService(purchaseDao, studentDao);
        PurchaseController purchaseController = new PurchaseController(purchaseForm, purchaseService);
        purchaseForm.setActionListener(purchaseController);
        
        serialLector.setPurchaseInterface(studentControllerV2);
        serialLector.setRechargeInterface(purchaseController);

        /*
        serialLector.setPurchaseInterface(controller);
        serialLector.setRechargeInterface(rechargeController);
        serialLector.setUpdateStudent(updateInfoController);
        */
        
        java.awt.EventQueue.invokeLater(() -> {
            app.setVisible(true);
        });

    }
}
