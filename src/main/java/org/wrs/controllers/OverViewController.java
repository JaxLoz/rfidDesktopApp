package org.wrs.controllers;

import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import org.wrs.view.OverviewView;

public class OverViewController {

    private StudentDao studentDao;
    private OverviewView overviewView;

    public OverViewController (StudentDao studentDao, OverviewView overviewView){
        this.studentDao = studentDao;
        this.overviewView = overviewView;
    }

    public void getStudent (String uuid){
        Student student = studentDao.getStudent(uuid);
        overviewView.showStudentInformation(student);
    }

}
