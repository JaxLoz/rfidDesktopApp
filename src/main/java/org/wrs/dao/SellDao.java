package org.wrs.dao;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.wrs.models.Sell;
import org.wrs.models.Student;

import javax.sql.DataSource;
import java.sql.*;

public class SellDao {

    private DataSource dataSource;

    public SellDao (DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Student setNewSell (Student student, Sell sell){
        Student studentUpdate = null;
        int idSell = 0;
        String insertSQL = "insert into purchase (date_time, total, student_id) values (CURRENT_TIMESTAMP, ?, ?)";
        String updateSQL = "update student set balance = balance - ? where id = ?";
        String selectSQL = "select * from student where id = ?";

        try(Connection con = this.dataSource.getConnection()){
            con.setAutoCommit(false);

            PreparedStatement preparedStatementInsert = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatementUpdate = con.prepareStatement(updateSQL);
            PreparedStatement preparedStatementSelect = con.prepareStatement(selectSQL);

            preparedStatementInsert.setDouble(1, sell.getTotal());
            preparedStatementInsert.setInt(2, student.getId());
            preparedStatementInsert.execute();
            ResultSet resultSetInsert = preparedStatementInsert.getGeneratedKeys();

            preparedStatementUpdate.setDouble(1,sell.getTotal());
            preparedStatementUpdate.setInt(2, student.getId());
            int updateRow = preparedStatementUpdate.executeUpdate();

            preparedStatementSelect.setInt(1,student.getId());
            preparedStatementSelect.executeQuery();
            ResultSet resultSetSelect = preparedStatementSelect.getResultSet();

            if(updateRow > 0){
                System.out.println("Se actualizo el saldo del estudiante");
            }else{
                System.out.println("No se actualizo el saldo del estudiante");
            }

            try(resultSetInsert){
                while (resultSetInsert.next()){
                    idSell = resultSetInsert.getInt(1);
                }
            }

            try(resultSetSelect){
                while (resultSetSelect.next()){
                    studentUpdate = new Student(
                            resultSetSelect.getInt(1),
                            resultSetSelect.getDouble(2),
                            resultSetSelect.getString(3),
                            resultSetSelect.getString(4),
                            resultSetSelect.getString(5),
                            resultSetSelect.getString(6));
                }
            }

            con.commit();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        System.out.println("Se registro la venta con el id: "+idSell);
        return studentUpdate;
    }

}
