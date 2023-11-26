package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import raven.application.form.other.StudentForm;
import org.wrs.configArduino.ISerialComunication;

/**
 *
 * @author C.Mateo
 */
public class StudentController implements ActionListener, ISerialComunication {

    private final StudentForm studentForm;
    private final StudentDao studentDao;

    public StudentController(StudentDao studentDAO, StudentForm studentForm) {
        this.studentDao = studentDAO;
        this.studentForm = studentForm;
        init();
    }

    private void init() {
        refreshStudentTable();
        studentForm.setActionListener(this);
    }

    /**
     * Updates the student table in the graphical interface. Retrieves the
     * updated list of students from the database by means of the object
     * {@code studentDao} and updates the table model in the student form
     * ({@code studentForm}). student form ({@code studentForm}).
     */
    public void refreshStudentTable() {
        List<Student> students = studentDao.listar();
        studentForm.setListStudentTableModel(students);
    }

    public void registerStudent() {
        studentDao.registerNewStudent(null);
    }

    /**
     * Implementation of the SerialCommunicationInterface interface method that
     * receives data from the serial port.
     * @param data The data received from the serial port. In this context,
     * it is expected uuid read by the Arduino.
     */
    @Override
    public void receiveDataSerialPort(String data) {

        boolean studentExists = studentDao.thereIsStudent(data);
        
        if (!studentExists) {
            studentForm.showRegisterStudentView(data);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String commad = e.getActionCommand();
        switch (commad) {
            case "refreshStudentTableCmd" ->
                refreshStudentTable();
            case "registerStudentCmd" ->
                registerStudent();
            default -> {
            }
        }
    }

}
