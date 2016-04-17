package com.rxdroid.extensecalc.model;

import com.rxdroid.data.realmmodels.RealmExpense;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by rxdroid on 4/16/16.
 */
public class Expense {
    public static RealmList<RealmExpense> getRealmList(List<Expense> expenseList) {
        return new RealmList<>();
    }
}
