package org.wrs.models;

public class Student {

    private int id_alumno;
    private String uid;
    private String nombre;

    public Student(int id_alumno, String uid, String nombre){
        this.id_alumno = id_alumno;
        this.uid = uid;
        this.nombre = nombre;
    }

    public Student(){
        this.id_alumno = 0;
        this.uid = "";
        this.nombre = "";
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
