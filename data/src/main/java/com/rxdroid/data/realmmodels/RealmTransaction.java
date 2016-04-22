package com.rxdroid.data.realmmodels;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rxdroid on 4/11/16.
 */
public class RealmTransaction extends RealmObject {

    @Index
    @PrimaryKey
    private long id;

    private int year;
    private int month;
    private int day;
    private float amount;
    boolean isScheduled;
    private int yearScheduled;
    private int monthScheduled;
    private int dayScheduled;
    private String currency;
    private String issue;
    private String paymentRate;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentRate() {
        return paymentRate;
    }

    public void setPaymentRate(String paymentRate) {
        this.paymentRate = paymentRate;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public int getYearScheduled() {
        return yearScheduled;
    }

    public void setYearScheduled(int yearScheduled) {
        this.yearScheduled = yearScheduled;
    }

    public int getMonthScheduled() {
        return monthScheduled;
    }

    public void setMonthScheduled(int monthScheduled) {
        this.monthScheduled = monthScheduled;
    }

    public int getDayScheduled() {
        return dayScheduled;
    }

    public void setDayScheduled(int dayScheduled) {
        this.dayScheduled = dayScheduled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
