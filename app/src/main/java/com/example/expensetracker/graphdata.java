package com.example.expensetracker;

public class graphdata {
    String category;
    Double amt;

    public graphdata(String category, Double amt) {
        this.category = category;
        this.amt = amt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmt() {
        return amt;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }
}
