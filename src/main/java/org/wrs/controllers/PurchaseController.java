package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;
import org.wrs.configArduino.ISerialComunication;
import org.wrs.models.Purchase;
import org.wrs.models.PurchaseFilter;
import org.wrs.models.PurchaseInfo;
import org.wrs.models.Student;
import org.wrs.service.PurchaseService;
import org.wrs.util.EmailSend;
import org.wrs.util.NotificationUtil;
import org.wrs.util.PropertiesEmailUtil;
import raven.application.form.other.PurchaseForm;
import raven.toast.Notifications;

/**
 *
 * @author C.Mateo
 */
public class PurchaseController implements ActionListener, ISerialComunication {

    private final PurchaseForm purchaseForm;
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseForm purchaseForm, PurchaseService purchaseService) {
        this.purchaseForm = purchaseForm;
        this.purchaseService = purchaseService;
        init();
    }

    private void init() {
        purchaseForm.setActionListener(this);
    }

    public void registerPurchase() {
        try {
            Student student = purchaseForm.getStudentFromForm();
            Purchase purchase = purchaseForm.getPurchaseDataFromForm();
            purchaseService.registerPurchase(student, purchase);
            purchaseForm.updataPurchaseCurrentDate();
            NotificationUtil.show(Notifications.Type.SUCCESS, "¡Venta registrada!");
        } catch (Exception e) {
            NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());
        }
    }

    public void sendEmail() {
        Student student = purchaseForm.getStudentFromForm();
        Properties prop = PropertiesEmailUtil.emailProperties;
        String balanceString = String.valueOf(student.getBalance());

        EmailSend emailSend = new EmailSend();
        emailSend.confMessage(student.getFirtsName() + " " + student.getLastName(), balanceString);
        String msg = emailSend.getMessageSend();

        emailSend.sendEmail(student.getMail(), prop.getProperty("mail.subject"), msg, PropertiesEmailUtil.emailProperties);
    }

    @Override
    public void receiveDataSerialPort(String data) {
        boolean studentExists = purchaseService.studentExists(data);
        if (studentExists) {
            Student student = purchaseService.getStudent(data);
            purchaseForm.setStudentCurrent(student);
            purchaseForm.loadStudentInRegisterPurchaseView(student);
            //purchaseForm.loadTableSellInfo(purchaseService.getPurchaseList(student)); ERROR CARGABA LAS VENTAS DEL ESTUDIANTE AL REGISTRAR UNA NUEVA VENTA
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "registerPurchaseCmd" ->
                registerPurchase();
            case "btnSendEmail" ->
                sendEmail();
            case "searchPurchaseByStudentBtn" ->
                filterPurchase();
            default -> {
            }
        }
    }

    private void filterPurchase() {
        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    PurchaseFilter purchaseFilter = purchaseForm.getDataFromFilter();
                    List<Purchase> purchases = purchaseService.filterPurchase(purchaseFilter);
                    purchaseForm.loadTableSellInfo(purchases);
                    PurchaseInfo purchaseInfo = new PurchaseInfo(purchases);
                    purchaseForm.setInfoPurchaseInForm(purchaseInfo);
                    NotificationUtil.show(Notifications.Type.SUCCESS, "¡Filtro aplicado!");
                } catch (Exception e) {
                    NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());

                }

            }

        };
        
        thread.start();

    }

}
