package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.wrs.configArduino.ISerialComunication;
import org.wrs.models.Recharge;
import org.wrs.models.RechargeFilter;
import org.wrs.models.Student;
import org.wrs.service.RechargeService;
import org.wrs.util.Formatter;
import org.wrs.util.NotificationUtil;
import raven.application.form.other.RechargeForm;
import raven.toast.Notifications;

/**
 *
 * @author C.Mateo
 */
public class RechargeController implements ActionListener, ISerialComunication {

    private final RechargeService rechargeService;
    private final RechargeForm rechargeForm;

    public RechargeController(RechargeService rechargeService, RechargeForm rechargeForm) {
        this.rechargeService = rechargeService;
        this.rechargeForm = rechargeForm;
        init();
    }
    
    private void init(){
        rechargeForm.setActionListener(this);
        showRechargesCurrentDate();
    }

    private void showRechargesCurrentDate() {
        RechargeFilter filterRecharge = new RechargeFilter();
        filterRecharge.setSpecificDate(Formatter.currentDate());
        List<Recharge> recharges = rechargeService.filterRecharge(filterRecharge);
        rechargeForm.setListRechargeTableModel(recharges);
    }

    private void refreshRechargeTable() {
        List<Recharge> recharges = rechargeService.getAll();
        rechargeForm.setListRechargeTableModel(recharges);
    }

    private void registerRecharge() {
        try {
            Recharge recharge = rechargeForm.getRechargeFromForm();
            rechargeService.registerRecharge(recharge);
            showRechargesCurrentDate();
            NotificationUtil.show(Notifications.Type.SUCCESS, "¡Recarga realizada exitosamente!");
        } catch (RuntimeException e) {
            NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());
        }

    }

    private void filterRechargeTable() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    RechargeFilter filterRecharge = rechargeForm.getDataFromFilter();
                    List<Recharge> recharges = rechargeService.filterRecharge(filterRecharge);
                    rechargeForm.setListRechargeTableModel(recharges);
                    NotificationUtil.show(Notifications.Type.SUCCESS, "¡Filtro aplicado correctamente!");
                } catch (RuntimeException e) {
                    NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());
                }
            }
        };
        thread.start();
    }

    private void cancelRecharge() {
        try {
            Long id = rechargeForm.getRechargeDataToCancel();
            System.out.println(id);
            rechargeService.cancelRecharge(id);
            showRechargesCurrentDate();
            NotificationUtil.show(Notifications.Type.SUCCESS, "¡Recarga cancelada!");
        } catch (RuntimeException e) {
            NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());
        }
    }

    @Override
    public void receiveDataSerialPort(String data) {
        boolean studentExists = rechargeService.studentExists(data);
        if (studentExists) {
            Student student = rechargeService.getStudent(data);
            rechargeForm.showRegisterRechargetDialog(student);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "registerRechargeCmd" ->
                registerRecharge();
            case "updateRechargeTableCmd" ->
                refreshRechargeTable();
            case "filterRechargeTableCmd" ->
                filterRechargeTable();
            case "cancelRechargeCmd" ->
                cancelRecharge();
            default -> {
            }
        }
    }

}
