package org.wrs.models;

public class Sell {

    private int id;
    private String date;
    private Double total;


    public Sell(int id, String date, Double total) {
        this.id = id;
        this.date = date;
        this.total = total;
    }

    public Sell (Double total){
        this.id = 0;
        this.date = "";
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
