package org.wrs.controllers;

import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import org.wrs.view.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentController implements ActionListener {

    private StudentDao studentDao;
    private String prueba;
    private view view;

    public StudentController (StudentDao studentDAO, view view){

        this.studentDao = studentDAO;
        this.view = view;
        this.prueba = "default";
    }

    public void initView (){
        this.listsStudentInTable();
        view.setActionListener(this);
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public boolean verificarEstudiante (String uid){
       return studentDao.thereIsStudent(uid);
    }

    public Student getStudent(String uid){
        return studentDao.getStudent(uid);
    }

    public void registerStudent (Student student){
        studentDao.registerNewStudent(student);
    }

    public void listsStudentInTable (){
        List<Student> studentList = studentDao.listar();
        view.listStudents(studentList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commad = e.getActionCommand();

        if(commad.equals("Actualizar")){

        }
    }
}
