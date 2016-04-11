package com.rxdroid.domain.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rxdroid on 4/10/16.
 */
public class MonthEntity {

    @SerializedName("name") String mName;
    @SerializedName("year") int mYear;
    @SerializedName("months") List<ExpenseEntity> mExpenseEntities;

    public String getName() {
        return mName;
    }

    public int getYear() {
        return mYear;
    }

    public List<ExpenseEntity> getExpenseEntities() {
        return mExpenseEntities;
    }
}
