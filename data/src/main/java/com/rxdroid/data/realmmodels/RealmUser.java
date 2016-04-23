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
    private String backupType;
    private RealmList<RealmTransaction> mIncomeList;
    private RealmList<RealmTransaction> mExpenseList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getBackupType() {
        return backupType;
    }

    public void setBackupType(String backupType) {
        this.backupType = backupType;
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
