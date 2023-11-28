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

    private void init() {
        RechargeFilter filterRecharge = new RechargeFilter();
        filterRecharge.setSpecificDate(Formatter.currentDate());
        List<Recharge> recharges = rechargeService.filterRecharge(filterRecharge);
        rechargeForm.setListRechargeTableModel(recharges);
        rechargeForm.setActionListener(this);
    }

    public void refreshRechargeTable() {
        List<Recharge> recharges = rechargeService.getAll();
        rechargeForm.setListRechargeTableModel(recharges);
    }

    private void registerRecharge() {
        try {
            Recharge recharge = rechargeForm.getRechargeFromForm();
            rechargeService.registerRecharge(recharge);
            refreshRechargeTable();
            NotificationUtil.show(Notifications.Type.SUCCESS, "¡Recarga realizada exitosamente!");
        } catch (RuntimeException e) {
            NotificationUtil.show(Notifications.Type.ERROR, e.getMessage());
        }

    }

    private void filterRechargeTable() {
        try {
            RechargeFilter filterRecharge = rechargeForm.getDataFromFilter();
            List<Recharge> recharges = rechargeService.filterRecharge(filterRecharge);
            rechargeForm.setListRechargeTableModel(recharges);
            NotificationUtil.show(Notifications.Type.SUCCESS, "¡Filtro aplicado correctamente!");
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
            default -> {
            }
        }
    }

}
