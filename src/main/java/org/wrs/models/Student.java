package org.wrs.models;

public class Student {

    private int id;
    private double balance;
    private String first_name;
    private String identification;
    private String last_name;
    private String uuid;



    public Student(double balance, String first_name, String identification, String last_name, String uuid) {
        this.id = 0;
        this.balance = balance;
        this.first_name = first_name;
        this.identification = identification;
        this.last_name = last_name;
        this.uuid = uuid;
    }

    public Student(int id, double balance, String first_name, String identification, String last_name, String uuid) {
        this.id = id;
        this.balance = balance;
        this.first_name = first_name;
        this.identification = identification;
        this.last_name = last_name;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
