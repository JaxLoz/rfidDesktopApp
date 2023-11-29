package org.wrs.dao;

import org.wrs.models.Recharge;
import org.wrs.models.Student;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.wrs.models.RechargeFilter;
import org.wrs.util.Formatter;

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

    public void cancelRecharge(Long rechargeId) {
        String sqlCancelRecharge = "update recharge set status = 'CANCELADO_RECHAZADO', is_confirmed = FALSE where id = ?";

        try (Connection connection = dataSource.getConnection(); PreparedStatement cancelRechargeStatement = connection.prepareStatement(sqlCancelRecharge)) {
            connection.setAutoCommit(false);

            cancelRechargeStatement.setLong(1, rechargeId);

            cancelRechargeStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Recharge> filterRecharge(RechargeFilter filterRecharge) {

        List<Recharge> recharges = new ArrayList<>();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT r.id, r.amount, r.date, r.is_confirmed, r.payment_type, ")
                .append("r.status, r.transaction_id, r.phone, s.id as student_id, ")
                .append("s.first_name, s.last_name ")
                .append("FROM recharge r ")
                .append("INNER JOIN student s ON r.student_id = s.id ")
                .append("WHERE 1 = 1 ");

        if (filterRecharge.getFromDate() != null && filterRecharge.getToDate() != null) {
            sqlBuilder.append("AND (r.date >= ? AND r.date <= ?::timestamp + interval '1 day') ");
        }

        if (filterRecharge.getSpecificDate() != null) {
            sqlBuilder.append("AND DATE(r.date) = ? ");
        }

        if (filterRecharge.getPaymentType() != null) {
            sqlBuilder.append("AND r.payment_type = ? ");
        }

        if (filterRecharge.getStudentName() != null) {
            sqlBuilder.append("AND CONCAT(s.first_name, ' ', s.last_name) ILIKE ? ");
        }

        if (filterRecharge.isIsConfirmed()) {
            sqlBuilder.append("AND r.is_confirmed = true ");
        }

        if (filterRecharge.getStatus() != null) {
            sqlBuilder.append("AND r.status = ? ");
        }

        sqlBuilder.append("ORDER BY r.date DESC");

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());) {

            int parameterIndex = 1;

            if (filterRecharge.getFromDate() != null && filterRecharge.getToDate() != null) {
                statement.setDate(parameterIndex++, Date.valueOf(filterRecharge.getFromDate()));
                statement.setDate(parameterIndex++, Date.valueOf(filterRecharge.getToDate()));
            }

            if (filterRecharge.getSpecificDate() != null) {
                statement.setDate(parameterIndex++, Date.valueOf(filterRecharge.getSpecificDate()));
            }

            if (filterRecharge.getPaymentType() != null) {
                statement.setString(parameterIndex++, filterRecharge.getPaymentType());
            }

            if (filterRecharge.getStudentName() != null) {
                statement.setString(parameterIndex++, "%" + filterRecharge.getStudentName() + "%");
            }

            if (filterRecharge.getStatus() != null) {
                System.out.println(filterRecharge.getStatus());
                statement.setString(parameterIndex++, filterRecharge.getStatus());
            }

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
        String sqlRecharge = "insert into recharge (amount, date, is_confirmed, payment_type, status, student_id, transaction_id, phone) values (?, ?, TRUE, 'EFECTIVO', 'REALIZADO', ?, 'N/A', 'N/A')";
        String sqlUpdateStudentBalance = "update student set balance = balance + ? where id = ?";
        String sqlSelectStudent = "select * from student where id = ?";
        try (Connection connection = dataSource.getConnection(); PreparedStatement rechargeStatement = connection.prepareStatement(sqlRecharge); PreparedStatement updateBalanceStatement = connection.prepareStatement(sqlUpdateStudentBalance)) {
            PreparedStatement preparedStatementSelect = connection.prepareStatement(sqlSelectStudent);

            connection.setAutoCommit(false);

            rechargeStatement.setDouble(1, recharge.getAmount());
            rechargeStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            rechargeStatement.setInt(3, recharge.getStudent().getId());

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
