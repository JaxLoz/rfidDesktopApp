package org.wrs;


import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Font;
import org.wrs.configArduino.ArduinoController;
import org.wrs.configArduino.SerialLector;
import org.wrs.connectionFactory.ConnectionFactory;
import org.wrs.dao.PurchaseDao;
import org.wrs.dao.RechargeDao;
import org.wrs.dao.StudentDao;
import javax.swing.*;
import org.wrs.controllers.AuthController;
import org.wrs.controllers.PurchaseController;
import org.wrs.controllers.SearchStudentController;
import org.wrs.controllers.StudentController;
import org.wrs.controllers.UserController;
import org.wrs.dao.UserDao;
import org.wrs.service.PurchaseService;
import org.wrs.view.Application;
import raven.application.form.LoginForm;
import raven.application.form.other.PurchaseForm;
import raven.application.form.other.StudentForm;
import raven.application.form.other.UserProfileForm;

public class DesktopSchoolStore {

    public static void main(String[] args) {

        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("raven.theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacLightLaf.setup();
        
        //MainJFrame and Forms
        Application app = Application.getInstance();
        LoginForm loginForm = app.getLoginForm();
        StudentForm studentForm = app.getMainForm().getStudentForm();
        PurchaseForm purchaseForm = app.getMainForm().getPurcharseForm();
        UserProfileForm userProfileForm = app.getMainForm().getUserProfileForm();

        ConnectionFactory connectionFactory = new ConnectionFactory();
        PurchaseDao purchaseDao = new PurchaseDao(connectionFactory.getConnection());

        //create serial lector and init arduino connection
        SerialLector serialLector = new SerialLector(new PanamaHitek_Arduino());
        serialLector.startConnectionArduino();
        ArduinoController arduinoController = new ArduinoController(serialLector);

        
        //User
        UserDao userDao = new UserDao(connectionFactory.getConnection());
        UserController userController = new UserController( userProfileForm, userDao);
        
        
        
        //Login
        AuthController authController = new AuthController(userDao, loginForm);
        
        

        
        
        //Student
        StudentDao studentDao = new StudentDao(connectionFactory.getConnection());
        StudentController studentControllerV2 = new StudentController(studentDao, studentForm);
        studentForm.setActionListener(studentControllerV2);
        
        //Student search controller 
        SearchStudentController searchStudentController = new SearchStudentController(studentDao, studentForm);
        studentForm.setISearchStudent(searchStudentController);

        //Recharge view and controller
        RechargeDao rechargeDao = new RechargeDao(connectionFactory.getConnection());

        
        //Purchase controller
        PurchaseService purchaseService = new PurchaseService(purchaseDao, studentDao);
        PurchaseController purchaseController = new PurchaseController(purchaseForm, purchaseService);
        purchaseForm.setActionListener(purchaseController);
        
        
        //Set Arduino interface
        serialLector.setPurchaseInterface(studentControllerV2);
        serialLector.setRechargeInterface(purchaseController);
        
        java.awt.EventQueue.invokeLater(() -> {
            app.setVisible(true);
        });

    }
}
