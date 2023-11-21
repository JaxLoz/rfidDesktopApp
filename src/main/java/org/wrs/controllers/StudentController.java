package org.wrs.controllers;

import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import org.wrs.view.LogView;
import org.wrs.view.SellView;
import org.wrs.view.updateUidView;
import org.wrs.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentController implements ActionListener {

    private final StudentDao studentDao;
    private final SellView overviewView;
    private final LogView logView;
    private final updateUidView updateUidView;
    private final LogController logController;
    private final SellViewController overViewController;
    private final updateInfoController updateInfoController;
    private String data;
    private final View view;

    public StudentController(StudentDao studentDAO, View view) {

        this.studentDao = studentDAO;
        this.view = view;
        this.data = "default";
        this.overviewView = new SellView();
        this.logView = new LogView();
        this.updateUidView = new updateUidView();

        this.updateInfoController = new updateInfoController(this.studentDao, this.updateUidView, this);
        this.logController = new LogController(this.studentDao, logView, this);
        this.overViewController = new SellViewController(this.studentDao, overviewView);

    }

    public void initView() {
        this.listsStudentInTable();
        view.setActionListener(this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean verificarEstudiante(String uid) {

        boolean thereIsStudent = studentDao.thereIsStudent(uid);
        if (thereIsStudent && !updateUidView.isActive()) {
            overViewController.getStudent(data);
            overviewView.setVisible(true);
        } else if (!thereIsStudent && !updateUidView.isActive()) {
            logController.initLogController();
            logView.setVisible(true);
            logController.setTextUid(data);
        }

        return thereIsStudent;
    }

    public Student getStudent(String uid) {
        return studentDao.getStudent(uid);
    }

    public void registerStudent(Student student) {
        studentDao.registerNewStudent(student);
    }

    public void selectStudent() {
        Student student = view.getOneStudenOfList();
        System.out.println("Selecionaste al estudiante: " + student.getFirst_name());
        updateInfoController.setDataStudenUpdateView(student);
    }

    public void listsStudentInTable() {
        List<Student> studentList = studentDao.listar();
        view.listStudents(studentList);
    }

    //TODO: search student by name or identification
    public void searchStudentByNameOrIdentification() {
        String key = view.getTextSearch();
        List<Student> studentList = studentDao.searchStudent(key);
        view.listStudents(studentList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commad = e.getActionCommand();

        if (commad.equals("Actualizar")) {
            System.out.println("presionando el boton actualizar");
            this.selectStudent();
            updateInfoController.initUpdateInfoController();
            updateUidView.setVisible(true);

        } else if (commad.equals("buscar")){
            searchStudentByNameOrIdentification();
        } else if (commad.equals("reset")) {
            listsStudentInTable();
        }
    }
}
