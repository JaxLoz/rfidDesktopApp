package org.wrs.dao;

import org.wrs.models.Recharge;
import org.wrs.models.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RechargeDao {

    private final DataSource dataSource;

    public RechargeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Recharge> all() {
        List<Recharge> recharges = new ArrayList<>();
        String sql = "SELECT r.id, r.amount, r.date, r.is_confirmed, r.payment_type, "
                + "r.status, r.transaction_id, r.phone, s.id as student_id, "
                + "s.first_name, s.last_name "
                + "FROM recharge r "
                + "INNER JOIN student s ON r.student_id = s.id "
                + "ORDER BY r.date DESC";

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("student_id"),
                        0.00,
                        resultSet.getString("first_name"),
                        "no identification",
                        resultSet.getString("last_name"),
                        "no uuid",
                        "no mail"
                );

                Recharge recharge = new Recharge(
                        resultSet.getLong("id"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("date").toLocalDateTime(),
                        resultSet.getBoolean("is_confirmed"),
                        resultSet.getString("payment_type"),
                        resultSet.getString("status"),
                        resultSet.getString("transaction_id"),
                        resultSet.getString("phone"),
                        student
                );

                recharges.add(recharge);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return recharges;
    }

    public List<Recharge> filterBetweenDate(String dateFrom, String dateTo) {

        List<Recharge> recharges = new ArrayList<>();

        String sql = "SELECT r.id, r.amount, r.date, r.is_confirmed, r.payment_type, "
                + "r.status, r.transaction_id, r.phone, s.id as student_id, "
                + "s.first_name, s.last_name "
                + "FROM recharge r "
                + "INNER JOIN student s ON r.student_id = s.id "
                + "WHERE r.date BETWEEN ? AND ?::timestamp + interval '1 day' "
                + "ORDER BY r.date DESC;";

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, dateFrom);
            statement.setString(2, dateTo);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt("student_id"),
                            0.00,
                            resultSet.getString("first_name"),
                            "no identification",
                            resultSet.getString("last_name"),
                            "no uuid",
                            "no mail"
                    );

                    Recharge recharge = new Recharge(
                            resultSet.getLong("id"),
                            resultSet.getDouble("amount"),
                            resultSet.getTimestamp("date").toLocalDateTime(),
                            resultSet.getBoolean("is_confirmed"),
                            resultSet.getString("payment_type"),
                            resultSet.getString("status"),
                            resultSet.getString("transaction_id"),
                            resultSet.getString("phone"),
                            student
                    );

                    recharges.add(recharge);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return recharges;
    }

    public Student save(Recharge recharge) {

        Student student = null;
        String sqlRecharge = "insert into recharge (amount, date, is_confirmed, payment_type, status, student_id, transaction_id) values (?, CURRENT_TIMESTAMP, TRUE, 'EFECTIVO', 'REALIZADO', ?, 'N/A')";
        String sqlUpdateStudentBalance = "update student set balance = balance + ? where id = ?";
        String sqlSelectStudent = "select * from student where id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement rechargeStatement = connection.prepareStatement(sqlRecharge); PreparedStatement updateBalanceStatement = connection.prepareStatement(sqlUpdateStudentBalance)) {
            PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlSelectStudent);

            connection.setAutoCommit(false);

            rechargeStatement.setDouble(1, recharge.getAmount());
            rechargeStatement.setInt(2, recharge.getStudent().getId());

            updateBalanceStatement.setDouble(1, recharge.getAmount());
            updateBalanceStatement.setLong(2, recharge.getStudent().getId());

            preparedStatementSelect.setInt(1, recharge.getStudent().getId());

            rechargeStatement.executeUpdate();
            updateBalanceStatement.executeUpdate();
            preparedStatementSelect.execute();

            ResultSet resultSetSelect = preparedStatementSelect.getResultSet();

            while (resultSetSelect.next()) {
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

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return student;
    }

}
