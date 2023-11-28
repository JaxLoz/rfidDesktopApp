package org.wrs.view.model.table;

import org.wrs.models.Student;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import org.wrs.util.Formatter;

public class StudentTableModel extends AbstractTableModel {

    private List<Student> studentList;
    private final String COLUMN_NAME[];

    public StudentTableModel (){
        super();
        this.studentList = new ArrayList<>();
        this.COLUMN_NAME = new String[]{"Identificacion", "Nombres", "Apellidos", "Correo", "Saldo"};
    }

    public void setStudentList (List<Student> studentList){
        this.studentList = studentList;
        fireTableDataChanged();
    }

    public List<Student> getStudentList (){
        return studentList;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAME[column];
    }

    @Override
    public int getRowCount() {
        return studentList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAME.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = studentList.get(rowIndex);

        switch (columnIndex){
            case 0 -> {
                return student.getIdentification();
            }
            case 1 -> {
                return student.getFirtsName();
            }
            case 2 -> {
                return student.getLastName();
            }
            case 3 -> {
                return student.getMail();
            }
            
            case 4 -> {return Formatter.formatCurrency(student.getBalance());}
        }

        return student;
    }

    public Student getStudentOfList (int rowIndex){
        return studentList.get(rowIndex);
    }
}
