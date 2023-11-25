package org.wrs.models;

public class Student {

    private int id;
    private double balance;
    private String first_name;
    private String identification;
    private String last_name;
    private String uuid;
    private String mail;



    public Student(String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = 0;
        this.balance = 0.0;
        this.first_name = first_name;
        this.identification = identification;
        this.last_name = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }
    
    public Student(double balance, String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = 0;
        this.balance = balance;
        this.first_name = first_name;
        this.identification = identification;
        this.last_name = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }

    public Student(int id, double balance, String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = id;
        this.balance = balance;
        this.first_name = first_name;
        this.identification = identification;
        this.last_name = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }
    
    public Student(int id, String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = id;
        this.balance = 0.0;
        this.first_name = first_name;
        this.identification = identification;
        this.last_name = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }
    
    public Student(){
        this.id = 0;
        this.balance = 0.0;
        this.first_name = "";
        this.identification = "";
        this.last_name = "";
        this.uuid = "";
        this.mail = "";
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

    public String getFirtsName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public String getMail (){
        return this.mail;
    }
    
    public void getMail (String mail){
        this.mail = mail;
    }
    
    
}
