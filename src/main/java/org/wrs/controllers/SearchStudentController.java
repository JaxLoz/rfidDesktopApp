package org.wrs.controllers;

import java.util.List;
import org.wrs.dao.StudentDao;
import org.wrs.models.Student;
import raven.application.form.other.StudentForm;

/**
 *
 * @author C.Mateo
 */
public class SearchStudentController implements ISearchStudent{
    
    private final StudentForm studentForm;
    private final StudentDao studentDao;

    public SearchStudentController(StudentDao studentDAO, StudentForm studentForm) {
        this.studentDao = studentDAO;
        this.studentForm = studentForm;
        init();
    }
    
    private void init(){
        studentForm.setISearchStudent(this);
    }

    @Override
    public void searchStudent(String key) {
        List<Student> students = studentDao.searchStudent(key);
        studentForm.setListStudentTableModel(students);
    }
    
}
