package com.rxdroid.data.realmmodels;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by rxdroid on 4/16/16.
 */
public class RealmUser extends RealmObject {

    @Index
    @PrimaryKey
    private int id;

    @Required
    private String name;
    private boolean hasDropBox;
    private boolean hasGoogleDrive;
    private RealmList<RealmIncome> mIncomeList;
    private RealmList<RealmExpense> mExpenseList;

    public RealmList<RealmIncome> getIncomeList() {
        return mIncomeList;
    }

    public void setIncomeList(RealmList<RealmIncome> incomeList) {
        mIncomeList = incomeList;
    }

    public RealmList<RealmExpense> getExpenseList() {
        return mExpenseList;
    }

    public void setExpenseList(RealmList<RealmExpense> expenseList) {
        mExpenseList = expenseList;
    }

}
