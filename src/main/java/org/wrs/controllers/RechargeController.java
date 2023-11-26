
package org.wrs.controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.wrs.configArduino.ISerialComunication;
import org.wrs.models.Recharge;
import org.wrs.models.Student;
import org.wrs.service.RechargeService;
import raven.application.form.other.RechargeForm;

/**
 *
 * @author C.Mateo
 */
public class RechargeController implements ActionListener, ISerialComunication{


    private final RechargeService rechargeService;
    private final RechargeForm rechargeForm;

    public RechargeController(RechargeService rechargeService, RechargeForm rechargeForm) {
        this.rechargeService = rechargeService;
        this.rechargeForm = rechargeForm;
        init();
    }
   
    private void init(){
        refreshRechargeTable();
        rechargeForm.setActionListener(this);
    }
    
    public void refreshRechargeTable(){
        List<Recharge> recharges = rechargeService.getAll();
        rechargeForm.setListRechargeTableModel(recharges);
    }
    
    @Override
    public void receiveDataSerialPort(String data) {
        boolean studentExists = rechargeService.studentExists(data);
        if(studentExists){
            Student student = rechargeService.getStudent(data);
            rechargeForm.showRegisterRechargetDialog(student);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "registerRechargeCmd" -> {}
            case "updateRechargeTableCmd" -> refreshRechargeTable();
            default ->{}
        }
    }
    
}
