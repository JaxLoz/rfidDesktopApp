package org.wrs.models;

public class Student {

    private int id;
    private double balance;
    private String firstName;
    private String lastName;
    private String uuid;
    private String mail;
    private String identification;

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Student(String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = 0;
        this.balance = 0.0;
        this.firstName = first_name;
        this.identification = identification;
        this.lastName = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }
    
    public Student(double balance, String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = 0;
        this.balance = balance;
        this.firstName = first_name;
        this.identification = identification;
        this.lastName = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }

    public Student(int id, double balance, String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = id;
        this.balance = balance;
        this.firstName = first_name;
        this.identification = identification;
        this.lastName = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }
    
    public Student(int id, String first_name, String identification, String last_name, String uuid, String mail) {
        this.id = id;
        this.balance = 0.0;
        this.firstName = first_name;
        this.identification = identification;
        this.lastName = last_name;
        this.uuid = uuid;
        this.mail = mail;
    }
    
    public Student(){
        this.id = 0;
        this.balance = 0.0;
        this.firstName = "";
        this.identification = "";
        this.lastName = "";
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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
