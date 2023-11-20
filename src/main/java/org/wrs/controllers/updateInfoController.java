package org.wrs.controllers;

import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import org.wrs.view.updateUidView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class updateInfoController implements ActionListener {

    private updateUidView updateUidView;
    private StudentDao studentDao;

    private StudentController studentController;

    private String uuidOld;
    private String uuidNew;

    public updateInfoController (StudentDao studentDao, updateUidView updateUidView, StudentController studentController){
        this.updateUidView = updateUidView;
        this.studentDao = studentDao;
        this.studentController = studentController;
        this.uuidNew = "";
        this.uuidOld = "";
    }

    public void initUpdateInfoController (){
        updateUidView.setActionListener(this);
    }

    public void setDataStudenUpdateView (Student student){
        updateUidView.setDataStudent(student);
    }

    public void updateUuidOfStudent (){
        this.uuidOld = updateUidView.getUuid();
        this.uuidNew = studentController.getData();
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
}
