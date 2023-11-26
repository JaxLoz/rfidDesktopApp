
package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.configArduino.ISerialComunication;
import org.wrs.dao.PurchaseDao;
import org.wrs.models.Purchase;
import org.wrs.models.Student;
import org.wrs.service.PurchaseService;
import org.wrs.view.model.table.SellTableModel;
import raven.application.form.other.PurchaseForm;

/**
 *
 * @author C.Mateo
 */
public class PurchaseController implements ActionListener, ISerialComunication{
    
    private final PurchaseForm purchaseForm;
    private final PurchaseService purchaseService;
    private PurchaseDao purchaseDao;
    
    
    public PurchaseController(PurchaseForm purchaseForm, PurchaseService purchaseService) {
        this.purchaseForm = purchaseForm;
        this.purchaseService = purchaseService;
    }
    
    public void refreshPurchaseTable(){
        //logic to get purchace and present and view
    }
    
    public void registerPurchase(){
        Student student = purchaseForm.getStudentFromForm();
        Purchase purchase = purchaseForm.getPurchaseDataFromForm();
        purchaseService.registerPurchase(student, purchase);
        purchaseForm.showRegisterPurchaseView(false);
    }
    
    @Override
    public void receiveDataSerialPort(String data) {
        boolean studentExists = purchaseService.studentExists(data);
        if(studentExists){
            Student student = purchaseService.getStudent(data);
            purchaseForm.loadStudentInRegisterPurchaseView(student);
            purchaseForm.loadStudentInPurchageForm(student);
            purchaseForm.loadTableSellInfo(purchaseService.getPurchaseList(student));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch(command){
            case "registerPurchaseBtn" -> registerPurchase();
            case "BuscarStudentbtn" ->{
                System.out.println("Presionando el boton buscar");
            }
            default -> {}
        }
    }
    
}
