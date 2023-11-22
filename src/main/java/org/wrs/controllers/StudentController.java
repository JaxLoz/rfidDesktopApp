package org.wrs.controllers;

import org.wrs.dao.SellDao;
import org.wrs.dao.StudentDao;
import org.wrs.models.Sell;
import org.wrs.models.Student;
import org.wrs.view.LogView;
import org.wrs.view.InfoSellView;
import org.wrs.view.updateUidView;
import org.wrs.view.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentController implements ActionListener {

    private StudentDao studentDao;
    private SellDao sellDao;

    private InfoSellView infoSellView;
    private LogView logView;
    private updateUidView updateUidView;
    private LogController logController;
    private InfoSellViewController infoSellViewController;
    private updateInfoController updateInfoController;

    private String data;
    private view view;

    public StudentController (StudentDao studentDAO,SellDao sellDao, view view){

        this.studentDao = studentDAO;
        this.sellDao = sellDao;
        this.view = view;
        this.data = "default";
        this.infoSellView = new InfoSellView();
        this.logView = new LogView();
        this.updateUidView = new updateUidView();

        this.updateInfoController = new updateInfoController(this.studentDao, this.updateUidView, this);
        this.logController = new LogController(this.studentDao, logView, this);
        this.infoSellViewController = new InfoSellViewController(this.studentDao, this.sellDao,infoSellView);

    }

    public void initView (){
        this.listsStudentInTable();
        view.setActionListener(this);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean verificarEstudiante (String uid){

        boolean thereIsStudent = studentDao.thereIsStudent(uid);
        if(thereIsStudent && !updateUidView.isActive()){
            infoSellViewController.getStudent(data);
            infoSellViewController.initSellViewListener();
            infoSellViewController.setTableSell();
            infoSellView.setVisible(true);
        }else if (!thereIsStudent && !updateUidView.isActive()){
            logController.initLogController();
            logView.setVisible(true);
            logController.setTextUid(data);
        }

        return thereIsStudent;
    }

    public Student getStudent(String uid){
        return studentDao.getStudent(uid);
    }

    public void registerStudent (Student student){
        studentDao.registerNewStudent(student);
    }

    public void selectStudent (){
        Student student = view.getOneStudenOfList();
        System.out.println("Selecionaste al estudiante: "+student.getFirst_name());
        updateInfoController.setDataStudenUpdateView(student);
    }

    public void listsStudentInTable (){
        List<Student> studentList = studentDao.listar();
        view.listStudents(studentList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commad = e.getActionCommand();

        if(commad.equals("Actualizar")){
            System.out.println("presionando el boton actualizar");
            this.selectStudent();
            updateInfoController.initUpdateInfoController();
            updateUidView.setVisible(true);

        }
    }
}
