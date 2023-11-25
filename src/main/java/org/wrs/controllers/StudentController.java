package org.wrs.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import raven.application.form.other.StudentForm;
import org.wrs.configArduino.ISerialComunication;
import raven.toast.Notifications;

/**
 *
 * @author C.Mateo
 */
public class StudentController implements ActionListener, ISerialComunication {

    private final StudentForm studentForm;
    private final StudentDao studentDao;
    private String uuid;

    public StudentController(StudentDao studentDAO, StudentForm studentForm) {
        this.studentDao = studentDAO;
        this.studentForm = studentForm;
        this.uuid = "";
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
        try{
        studentDao.registerNewStudent(studentForm.getNewStudentRegister());
        }catch (RuntimeException e){
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, e.getMessage());
        }
        System.out.println("Registrando");
    }
    
    
    public void closeRegisterStudentView (){
        studentForm.closeRegisterStudentView();
    }
    
    public void setUuidUpdateView (){
        studentForm.setNewUuid(this.uuid);
    }
    
    public void updateInfoStudent (){
        Student updateStudent = studentForm.getUpdateStudent();
        studentDao.updataStudent(updateStudent);
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
            case "updateStudentRfidCmd" ->{
                System.out.println("actualizar rfid");
                studentForm.showUpdateStudenView();
            }
                
            case "registerStudentCmd" ->{
                registerStudent();
                refreshStudentTable();
            }
           
            case "RefreshTableStudent" ->{
                refreshStudentTable();
            }
            
            case "CambiarbtnUpdate" ->{
                System.out.println("Presionando el boton de cambiar");
                this.setUuidUpdateView();
            }
            
            case "ActualizarBtnUpdate" ->{
                System.out.println("Presionando el boton de actualizar");
                this.updateInfoStudent();
            }
                
            case "CancelarStudentCmd" ->
                this.closeRegisterStudentView();  
            default -> {
            }
        }
    }
}
