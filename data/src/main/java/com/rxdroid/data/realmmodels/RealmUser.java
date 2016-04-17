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
    private long id;

    @Required
    private String name;
    private boolean hasDropBox;
    private boolean hasGoogleDrive;
    private RealmList<RealmIncome> mIncomeList;
    private RealmList<RealmExpense> mExpenseList;

    public long getId() {
        return id;
    }

    public void setId(long  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasDropBox() {
        return hasDropBox;
    }

    public void setHasDropBox(boolean hasDropBox) {
        this.hasDropBox = hasDropBox;
    }

    public boolean isHasGoogleDrive() {
        return hasGoogleDrive;
    }

    public void setHasGoogleDrive(boolean hasGoogleDrive) {
        this.hasGoogleDrive = hasGoogleDrive;
    }

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
