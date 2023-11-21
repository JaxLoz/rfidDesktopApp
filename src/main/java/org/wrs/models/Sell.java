package org.wrs.models;

public class Sell {

    private String name;
    private Double total;

    public Sell(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public Sell (Double total){
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
