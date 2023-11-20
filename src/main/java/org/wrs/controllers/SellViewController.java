package org.wrs.controllers;

import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import org.wrs.view.SellView;

public class SellViewController {

    private StudentDao studentDao;
    private SellView overviewView;

    public SellViewController(StudentDao studentDao, SellView overviewView){
        this.studentDao = studentDao;
        this.overviewView = overviewView;
    }

    public void getStudent (String uuid){
        Student student = studentDao.getStudent(uuid);
        overviewView.showStudentInformation(student);
    }

}
