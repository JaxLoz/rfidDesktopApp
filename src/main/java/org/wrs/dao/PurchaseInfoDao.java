package org.wrs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.wrs.models.Purchase;
import org.wrs.models.PurchaseFilter;
import org.wrs.models.PurchaseInfo;
import org.wrs.models.Student;

public class PurchaseInfoDao {

    private DataSource dataSource;

    public PurchaseInfoDao(DataSource datasource) {
        this.dataSource = datasource;
    }

    public PurchaseInfo getInfoPurchaseRange(String since, String to) {
        PurchaseInfo purchaseInfoRange = new PurchaseInfo();
        List<Purchase> listPurchase = new ArrayList<>();

        try (Connection con = dataSource.getConnection(); PreparedStatement prestatementPurchase = con.prepareStatement("SELECT p.id, TO_CHAR(p.date_time, 'DD-MM-YYYY HH24:MI:SS'), p.total, s.id, s.first_name, s.last_name "
                + "FROM purchase p "
                + "INNER JOIN student s ON p.student_id = s.id "
                + "WHERE p.date_time BETWEEN CAST(? AS TIMESTAMP) AND CAST(? AS TIMESTAMP) "
                + "ORDER BY p.date_time DESC")) {
            prestatementPurchase.setString(1, since);
            prestatementPurchase.setString(2, to);

            try (ResultSet resultSet = prestatementPurchase.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );

                    Purchase purchase = new Purchase(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            student
                    );

                    listPurchase.add(purchase);
                }
            } // El ResultSet se cierra automáticamente aquí

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        purchaseInfoRange = new PurchaseInfo(listPurchase);
        return purchaseInfoRange;
    }

    public PurchaseInfo getInfoPurchaseTo(String to) {
        String sql = "SELECT p.id, TO_CHAR(p.date_time, 'DD-MM-YYYY HH24:MI:SS'), p.total, s.id, s.first_name, s.last_name "
                + "FROM purchase p "
                + "INNER JOIN student s ON p.student_id = s.id "
                + "WHERE DATE(p.date_time) = ? "
                + "ORDER BY p.date_time DESC";

        PurchaseInfo purchaseInfoTo = new PurchaseInfo();
        List<Purchase> listPurchase = new ArrayList<>();

        try (Connection con = dataSource.getConnection(); PreparedStatement prestatementPurchase = con.prepareStatement(sql)) {
            prestatementPurchase.setDate(1, Date.valueOf(to));

            try (ResultSet resultSet = prestatementPurchase.executeQuery()) {
                while (resultSet.next()) {
                    Student student = new Student(
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    );

                    Purchase purchase = new Purchase(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getDouble(3),
                            student
                    );

                    listPurchase.add(purchase);
                }
            } // El ResultSet se cierra automáticamente aquí

        } catch (SQLException e) {
            e.printStackTrace();
        }

        purchaseInfoTo = new PurchaseInfo(listPurchase);
        return purchaseInfoTo;
    }

    public List<Purchase> filterPurchases(PurchaseFilter purchaseFilter) {

        List<Purchase> purchases = new ArrayList<>();

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT p.id, TO_CHAR(p.date_time, 'DD-MM-YYYY HH24:MI:SS'), p.total, s.id as student_id, ")
                .append("s.first_name, s.last_name ")
                .append("FROM purchase p ")
                .append("INNER JOIN student s ON p.student_id = s.id ")
                .append("WHERE 1 = 1 ");

        if (purchaseFilter.getFromDate() != null && purchaseFilter.getToDate() != null) {
            sqlBuilder.append("AND (p.date_time >= ? AND p.date_time <= ?::timestamp + interval '1 day') ");
        }

        if (purchaseFilter.getSpecificDate() != null) {
            sqlBuilder.append("AND DATE(p.date_time) = ? ");
        }

        if (purchaseFilter.getStudentName() != null) {
            sqlBuilder.append("AND CONCAT(s.first_name, ' ', s.last_name) ILIKE ? ");
        }

        sqlBuilder.append("ORDER BY p.date_time DESC");

        try (Connection connection = dataSource.getConnection(); PreparedStatement statement = connection.prepareStatement(sqlBuilder.toString());) {

            int parameterIndex = 1;

            if (purchaseFilter.getFromDate() != null && purchaseFilter.getToDate() != null) {
                statement.setDate(parameterIndex++, Date.valueOf(purchaseFilter.getFromDate()));
                statement.setDate(parameterIndex++, Date.valueOf(purchaseFilter.getToDate()));
            }

            if (purchaseFilter.getSpecificDate() != null) {
                statement.setDate(parameterIndex++, Date.valueOf(purchaseFilter.getSpecificDate()));
            }

            if (purchaseFilter.getStudentName() != null) {
                statement.setString(parameterIndex++, "%" + purchaseFilter.getStudentName() + "%");
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

                    Purchase purchase = new Purchase(
                            resultSet.getInt("id"),
                            resultSet.getString(2),
                            resultSet.getDouble("total"),
                            student
                    );

                    purchases.add(purchase);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return purchases;
    }

}
