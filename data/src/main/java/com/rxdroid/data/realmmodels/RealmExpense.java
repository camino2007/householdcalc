package com.rxdroid.data.realmmodels;

import io.realm.RealmObject;

/**
 * Created by rxdroid on 4/11/16.
 */
public class RealmExpense extends RealmObject {

    private int mYear;
    private int mMonth;
    private int mDay;
    private float mAmount;
    private String mCurrency;
    private String mPaymentRate;

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getMonth() {
        return mMonth;
    }

    public void setMonth(int month) {
        mMonth = month;
    }

    public int getDay() {
        return mDay;
    }

    public void setDay(int day) {
        mDay = day;
    }

    public float getAmount() {
        return mAmount;
    }

    public void setAmount(float amount) {
        mAmount = amount;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }
}
