package com.example.trading_webapp_backend.model;

public class FinnhubQuoteResponse {

    private double c; // Current price
    private double d; // Change in price
    private double dp; // Percentage change
    private double h; // High price of the day
    private double l; // Low price of the day
    private double o; // Open price of the day
    private double pc; // Previous close price
    private long t; // Timestamp

    // Getters and Setters
    public double getCurrent() {
        return c;
    }

    public void setCurrent(double c) {
        this.c = c;
    }

    public double getChange() {
        return d;
    }

    public void setChange(double d) {
        this.d = d;
    }

    public double getPercentageChange() {
        return dp;
    }

    public void setPercentageChange(double dp) {
        this.dp = dp;
    }

    public double getHigh() {
        return h;
    }

    public void setHigh(double h) {
        this.h = h;
    }

    public double getLow() {
        return l;
    }

    public void setLow(double l) {
        this.l = l;
    }

    public double getOpen() {
        return o;
    }

    public void setOpen(double o) {
        this.o = o;
    }

    public double getPreviousClose() {
        return pc;
    }

    public void setPreviousClose(double pc) {
        this.pc = pc;
    }

    public long getTimestamp() {
        return t;
    }

    public void setTimestamp(long t) {
        this.t = t;
    }
}
