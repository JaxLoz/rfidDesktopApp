package org.wrs.dao;

import org.wrs.models.Recharge;
import org.wrs.models.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RechargeDao {

    private final DataSource dataSource;

    public RechargeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Student save(Recharge recharge){

        Student student = null;
        String sqlRecharge = "insert into recharge (amount, date, is_confirmed, payment_type, status, student_id, transaction_id) values (?, CURRENT_TIMESTAMP, TRUE, 'EFECTIVO', 'REALIZADO', ?, 'N/A')";
        String sqlUpdateStudentBalance = "update student set balance = balance + ? where id = ?";
        String sqlSelectStudent = "select * from student where id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement rechargeStatement = connection.prepareStatement(sqlRecharge);
            PreparedStatement updateBalanceStatement = connection.prepareStatement(sqlUpdateStudentBalance)){
            PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlSelectStudent);

            connection.setAutoCommit(false);

            rechargeStatement.setDouble(1, recharge.getAmount());
            rechargeStatement.setInt(2, recharge.getStudent().getId());

            updateBalanceStatement.setDouble(1, recharge.getAmount());
            updateBalanceStatement.setLong(2, recharge.getStudent().getId());

            preparedStatementSelect.setInt(1,  recharge.getStudent().getId());

            rechargeStatement.executeUpdate();
            updateBalanceStatement.executeUpdate();
            preparedStatementSelect.execute();

            ResultSet resultSetSelect = preparedStatementSelect.getResultSet();

            while (resultSetSelect.next()){
                student = new Student(
                        resultSetSelect.getInt(1),
                        resultSetSelect.getDouble(2),
                        resultSetSelect.getString(3),
                        resultSetSelect.getString(4),
                        resultSetSelect.getString(5),
                        resultSetSelect.getString(6),
                        resultSetSelect.getString(7)
                );
            }

            connection.commit();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return  student;
    }


}
