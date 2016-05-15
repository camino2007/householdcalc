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
    private RealmList<RealmTransaction> incomeList;
    private RealmList<RealmTransaction> expenseList;
    private RealmList<RealmUser> childUserList;

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
        return incomeList;
    }

    public void setIncomeList(RealmList<RealmTransaction> incomeList) {
        this.incomeList = incomeList;
    }

    public RealmList<RealmTransaction> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(RealmList<RealmTransaction> expenseList) {
        this.expenseList = expenseList;
    }

    public RealmList<RealmUser> getChildUserList() {
        return childUserList;
    }

    public void setChildUserList(RealmList<RealmUser> childUserList) {
        this.childUserList = childUserList;
    }
}
