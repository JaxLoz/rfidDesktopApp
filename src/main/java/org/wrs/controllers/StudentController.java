package org.wrs.controllers;

import org.wrs.dao.PurchaseDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import org.wrs.view.LogView;
import org.wrs.view.InfoSellView;
import org.wrs.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.wrs.configArduino.ISerialComunication;

public class StudentController implements ActionListener, ISerialComunication {

    private final LogView logView;
    private final StudentDao studentDao;
    private final InfoSellView infoSellView;
    private final LogController logController;
    private final InfoSellViewController infoSellViewController;
    private  UpdateInfoController updateInfoController;

    private final View view;


    public StudentController (StudentDao studentDAO,PurchaseDao sellDao, View view){
        this.studentDao = studentDAO;
        this.view = view;
        this.infoSellView = new InfoSellView();
        this.logView = new LogView();
        this.logController = new LogController(this.studentDao, logView, this);
        this.infoSellViewController = new InfoSellViewController(this.studentDao, sellDao,infoSellView);
    }

    public void initView() {
        this.listsStudentInTable();
        view.setActionListener(this);
    }

    public void setUpdateInfoController( UpdateInfoController updateInfoController){
        this.updateInfoController = updateInfoController;
    }


    public void verificarEstudiante(String uid) {

        boolean thereIsStudent = studentDao.thereIsStudent(uid);

        if(thereIsStudent && !updateInfoController.isWindowActive()){
            infoSellViewController.getStudent(uid);
            infoSellViewController.setTableSell();
            infoSellView.setVisible(true);
        } else if (!thereIsStudent && !updateInfoController.isWindowActive()){
            logView.setVisible(true);
            logController.setTextUid(uid);
        }

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
            updateInfoController.initView();
        } else if (commad.equals("buscar")){
            searchStudentByNameOrIdentification();
        } else if (commad.equals("reset")) {
            listsStudentInTable();
        }
    }

    @Override
    public void receiveDataSerialPort(String data) {
        verificarEstudiante(data);
    }
}
