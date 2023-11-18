package org.wrs.dao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.wrs.models.Student;

public class StudentDao {

    private final DataSource dataSource;

    public StudentDao(DataSource datasource){
        this.dataSource = datasource;
    }

    public boolean thereIsStudent (String uid){


        try(Connection con = dataSource.getConnection()){

            PreparedStatement preparedStatement = con.prepareStatement("select * from student where uuid = ?");
            preparedStatement.setString(1, uid);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            try(resultSet){
                if(resultSet.next()){
                    System.out.println("ya existe el alumno con el uid: "+uid);
                    return true;
                }else {
                    System.out.println("No hay registro del alumno con el uid: "+uid);
                    return false;
                    //throw new RuntimeException ("estudiante no registrado");
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public Student getStudent (String uid){
        Student student = null;

        try(Connection con = dataSource.getConnection()){

            PreparedStatement preparedStatement = con.prepareStatement("select * from student where uuid = ?");
            preparedStatement.setString(1, uid);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            try(resultSet){
                if(resultSet.next()){
                    student = new Student(
                                    resultSet.getInt(1),
                                    resultSet.getDouble(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getString(6));

                }else{
                    System.out.printf("Algo salio mal al tratar de obtener el registro del alumno con el uid: "+uid);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException (e);
        }

        return student;
    }

    public void registerNewStudent (Student student){

        int idRegisteredStudent = 0;
        try(Connection con = dataSource.getConnection()){

            PreparedStatement preparedStatement = con.prepareStatement("insert into student (balance, first_name, identification, last_name, uuid) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1,student.getBalance());
            preparedStatement.setString(2,student.getFirst_name());
            preparedStatement.setString(3, student.getIdentification());
            preparedStatement.setString(4, student.getLast_name());
            preparedStatement.setString(5, student.getUuid());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            try(resultSet){
                while (resultSet.next()){
                    idRegisteredStudent = resultSet.getInt(1);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        System.out.println("Se registro el alumno con el id: "+idRegisteredStudent);

    }

    public List<Student> listar (){
        List<Student> listStudent = new ArrayList<>();

        try(Connection con = dataSource.getConnection()){
            PreparedStatement preparedStatement = con.prepareStatement("select * from student");
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                Student student = new Student(
                        resultSet.getInt(1),
                        resultSet.getDouble(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6));

                listStudent.add(student);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return listStudent;
    }



}
