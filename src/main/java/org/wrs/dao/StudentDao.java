package org.wrs.dao;

import javax.sql.DataSource;
import java.sql.*;

import org.wrs.models.Student;

public class StudentDao {

    private final DataSource dataSource;

    public StudentDao(DataSource datasource){
        this.dataSource = datasource;
    }

    public boolean thereIsStudent (String uid){

        try(Connection con = dataSource.getConnection()){

            PreparedStatement preparedStatement = con.prepareStatement("select * from alumno where ?");
            preparedStatement.setString(1, uid);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            try(resultSet){
                if(resultSet.next()){
                    System.out.println("ya existe el alumno con el uid: "+uid);
                    return true;
                }else {
                    System.out.printf("No hay registro del alumno con el uid: "+uid);
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

            PreparedStatement preparedStatement = con.prepareStatement("select * from alumno where uid = ?");
            preparedStatement.setString(1, uid);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            try(resultSet){
                if(resultSet.next()){
                    student = new Student(
                            resultSet.getInt("id_alumno"),
                            resultSet.getString("uid"),
                            resultSet.getString("nombre"));

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

            PreparedStatement preparedStatement = con.prepareStatement("insert into alumno (uid, nombre) values (?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getUid());
            preparedStatement.setString(2, student.getNombre());
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

}
