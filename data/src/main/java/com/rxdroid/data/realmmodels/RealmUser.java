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
    private String currency;
    private boolean hasDropBox;
    private boolean hasGoogleDrive;
    private boolean isEmail;
    private boolean isNotNow;
    private RealmList<RealmTransaction> mIncomeList;
    private RealmList<RealmTransaction> mExpenseList;

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isEmail() {
        return isEmail;
    }

    public void setEmail(boolean email) {
        isEmail = email;
    }

    public boolean isNotNow() {
        return isNotNow;
    }

    public void setNotNow(boolean notNow) {
        isNotNow = notNow;
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

    public RealmList<RealmTransaction> getIncomeList() {
        return mIncomeList;
    }

    public void setIncomeList(RealmList<RealmTransaction> incomeList) {
        mIncomeList = incomeList;
    }

    public RealmList<RealmTransaction> getExpenseList() {
        return mExpenseList;
    }

    public void setExpenseList(RealmList<RealmTransaction> expenseList) {
        mExpenseList = expenseList;
    }
}
