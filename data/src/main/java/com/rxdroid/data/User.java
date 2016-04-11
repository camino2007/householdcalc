package com.rxdroid.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by rxdroid on 4/11/16.
 */
public class User extends RealmObject {

    private String mName;
    private boolean mHasDropBox;

    private RealmList<Income> mIncomeList;
    private RealmList<Expense> mExpenseList;


    public RealmList<Income> getIncomeList() {
        return mIncomeList;
    }

    public void setIncomeList(RealmList<Income> incomeList) {
        mIncomeList = incomeList;
    }

    public RealmList<Expense> getExpenseList() {
        return mExpenseList;
    }

    public void setExpenseList(RealmList<Expense> expenseList) {
        mExpenseList = expenseList;
    }
}
