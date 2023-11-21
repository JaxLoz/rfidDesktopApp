/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wrs.controllers;

import org.wrs.configArduino.SerialComunicationInterface;
import org.wrs.dao.RechargeDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Recharge;
import org.wrs.models.Student;
import org.wrs.view.RechargeView;
import org.wrs.controllers.StudentController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RechargeController implements ActionListener, SerialComunicationInterface {

    private final StudentDao studentDao;
    private final RechargeDao rechargeDao;
    private final RechargeView rechargeView;
    private final StudentController studentController;

    public RechargeController(
            StudentDao studentDao,
            RechargeDao rechargeDao,
            RechargeView rechargeView,
            StudentController studentController
    ) {
        this.studentDao = studentDao;
        this.rechargeDao = rechargeDao;
        this.rechargeView = rechargeView;
        this.studentController = studentController;
        rechargeView.setActionListener(this);
    }

    public void saveRecharge() {
        Recharge recharge = rechargeView.getRecharge();
        Student student = rechargeDao.save(recharge);
        rechargeView.updateBalance(student);
        studentController.listsStudentInTable();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("recargar")) {
            saveRecharge();
        }
    }

    @Override
    public void setValueArduino(String data) {
        boolean existsStudent = studentDao.thereIsStudent(data);
        if (existsStudent) {
            Student student = studentDao.getStudent(data);
            Recharge recharge = new Recharge();
            recharge.setStudent(student);
            rechargeView.setRecharge(recharge);
        } else {
            JOptionPane.showMessageDialog(rechargeView, "¡Estudiante no registrado!\nDesactive el modo RECARGA para registrarlo");
        }
    }
}
