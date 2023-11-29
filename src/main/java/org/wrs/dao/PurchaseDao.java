package org.wrs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import org.wrs.models.Purchase;
import org.wrs.models.Student;

import javax.sql.DataSource;

import java.time.LocalDateTime;

public class PurchaseDao {

    private final DataSource dataSource;

    public PurchaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Student createPurchase(Student student, Purchase sell) {
        Student studentUpdate = null;
        int idSell = 0;
        String insertSQL = "insert into purchase (date_time, total, student_id) values (?, ?, ?)";
        String updateSQL = "update student set balance = balance - ? where id = ?";
        String selectSQL = "select * from student where id = ?";

        try (Connection con = this.dataSource.getConnection()) {
            con.setAutoCommit(false);

            PreparedStatement preparedStatementInsert = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement preparedStatementUpdate = con.prepareStatement(updateSQL);
            PreparedStatement preparedStatementSelect = con.prepareStatement(selectSQL);

            preparedStatementInsert.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatementInsert.setDouble(2, sell.getTotal());
            preparedStatementInsert.setInt(3, student.getId());
            preparedStatementInsert.execute();

            ResultSet resultSetInsert = preparedStatementInsert.getGeneratedKeys();

            preparedStatementUpdate.setDouble(1, sell.getTotal());
            preparedStatementUpdate.setInt(2, student.getId());
            int updateRow = preparedStatementUpdate.executeUpdate();

            preparedStatementSelect.setInt(1, student.getId());
            preparedStatementSelect.executeQuery();
            ResultSet resultSetSelect = preparedStatementSelect.getResultSet();

            if (updateRow > 0) {
                System.out.println("Se actualizo el saldo del estudiante");
            } else {
                System.out.println("No se actualizo el saldo del estudiante");
            }

            try (resultSetInsert) {
                while (resultSetInsert.next()) {
                    idSell = resultSetInsert.getInt(1);
                }
            }

            try (resultSetSelect) {
                while (resultSetSelect.next()) {
                    studentUpdate = new Student(
                            resultSetSelect.getInt(1),
                            resultSetSelect.getDouble(2),
                            resultSetSelect.getString(3),
                            resultSetSelect.getString(4),
                            resultSetSelect.getString(5),
                            resultSetSelect.getString(6),
                            resultSetSelect.getString(7)
                    );
                }
            }

            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Se registro la venta con el id: " + idSell);
        return studentUpdate;
    }
}
