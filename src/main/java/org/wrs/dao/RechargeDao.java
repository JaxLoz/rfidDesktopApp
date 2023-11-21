package org.wrs.dao;

import org.wrs.models.Recharge;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RechargeDao {

    private final DataSource dataSource;

    public RechargeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(Recharge recharge){
        String sqlRecharge = "insert into recharge (amount, date, is_confirmed, payment_type, status, student_id, transaction_id) values (?, CURRENT_TIMESTAMP, TRUE, 'EFECTIVO', 'REALIZADO', ?, 'N/A')";
        String sqlUpdateStudentBalance = "update student set balance = balance - ? where id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement rechargeStatement = connection.prepareStatement(sqlRecharge);
            PreparedStatement updateBalanceStatement = connection.prepareStatement(sqlUpdateStudentBalance)){

            connection.setAutoCommit(false);

            rechargeStatement.setString(1, recharge.getPhone());
            rechargeStatement.setString(2, recharge.getPhone());

            updateBalanceStatement.setDouble(1, recharge.getAmount());
            updateBalanceStatement.setLong(2, recharge.getStudent().getId());

            rechargeStatement.executeUpdate();
            updateBalanceStatement.executeUpdate();

            connection.commit();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


}
