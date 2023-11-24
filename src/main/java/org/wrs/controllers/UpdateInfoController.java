package org.wrs.controllers;

import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import org.wrs.view.UpdateUidView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.wrs.configArduino.ISerialComunication;

public class UpdateInfoController implements ActionListener, ISerialComunication {

    private UpdateUidView updateUidView;
    private StudentDao studentDao;

    private StudentController studentController;

    private String uuidOld;
    private String uuidNew;

    public UpdateInfoController(StudentDao studentDao, UpdateUidView updateUidView, StudentController studentController){
        this.updateUidView = updateUidView;
        this.studentDao = studentDao;
        this.studentController = studentController;
        this.uuidNew = "";
        this.uuidOld = "";
    }

    public void initView(){
        updateUidView.setVisible(true);
    }

    public void initUpdateInfoController (){
        updateUidView.setActionListener(this);
    }

    public boolean isWindowActive(){
        return updateUidView.isActive();
    }

    public void setDataStudenUpdateView (Student student){
        updateUidView.setDataStudent(student);
    }

    public void updateUuidOfStudent (){
        this.uuidOld = updateUidView.getUuid();
        boolean thereIsStudent = studentDao.thereIsStudent(uuidNew);

        if (!thereIsStudent){
            if(uuidNew.equals(uuidOld)){
                System.out.println("Son el mismo UUID. Para que lo vas a actualizar?");
            }else{
                System.out.println("Son UUID diferentes");
                updateUidView.setUuid(uuidNew);
            }
        }else{
            System.out.println("Este llavero pertenece a alguien ya registrado");
        }


    }

    public void updateinfoStudent (){
        Student updateInfo = updateUidView.getUpdateInfoStudent();
        studentDao.updataStudent(updateInfo, this.uuidOld);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if(command.equals("Actualizar")){
            System.out.println("Se preciono el boton actualizar de la ventana updataUidView");
            this.updateinfoStudent();
        } else if (command.equals("Cambiar")) {
            System.out.println("Precionando el boton cambiar");
            this.updateUuidOfStudent();
        }
    }

    @Override
    public void receiveDataSerialPort(String data) {
        System.out.println("updateController: " + data);
        this.uuidNew = data;
    }
}
