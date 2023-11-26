package org.wrs.models;

import java.time.LocalDateTime;

public class Recharge {

    private Long id;
    private double amount;
    private LocalDateTime date;
    private boolean isConfirmed;
    private String paymentType;
    private String status;
    private String transactionId;
    private String phone;
    private Student student;
    

    public Recharge() {
        status = "REALIZADO";
        isConfirmed = true;
        transactionId = "N/A";
        phone = "N/A";
    }

    public Recharge(
            Long id,
            double amount,
            LocalDateTime date,
            boolean isConfirmed,
            String paymentType,
            String status,
            String transactionId,
            String phone,
            Student student) {

        this.id = id;
        this.amount = amount;
        this.date = date;
        this.isConfirmed = isConfirmed;
        this.paymentType = paymentType;
        this.status = status;
        this.transactionId = transactionId;
        this.phone = phone;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
