package com.rxdroid.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rxdroid on 4/10/16.
 */
public class ExpenseEntity {

    @SerializedName("date") String mDate;
    @SerializedName("amount") int mAmount;
    @SerializedName("issue") String mIssue;
    @SerializedName("currency") String mCurrency;

    public String getDate() {
        return mDate;
    }

    public int getAmount() {
        return mAmount;
    }

    public String getIssue() {
        return mIssue;
    }

    public String getCurrency() {
        return mCurrency;
    }
}
