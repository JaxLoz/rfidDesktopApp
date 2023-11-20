package org.wrs.controllers;

import org.wrs.dao.StudentDao;
import org.wrs.view.LogView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogController implements ActionListener {

    private LogView logView;
    private StudentDao studentDao;
    private StudentController studentController;

    public LogController (StudentDao studentDao, LogView logView, StudentController studentController){
        this.studentController = studentController;
        this.logView = logView;
        this.studentDao = studentDao;
    }

    public void initLogController (){
        logView.setActionListener(this);
    }

    public void regNewStudent (){
        studentDao.registerNewStudent(logView.getDataNewStudent());
        studentController.listsStudentInTable();
    }

    public void setTextUid (String uid){
        logView.setUuidTexField(uid);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String command = e.getActionCommand();

       if(command.equals("Guardar")){
           this.regNewStudent();
       }
    }
}
