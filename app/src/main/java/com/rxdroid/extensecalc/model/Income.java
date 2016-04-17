package com.rxdroid.extensecalc.model;

import com.rxdroid.data.realmmodels.RealmIncome;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by rxdroid on 4/16/16.
 */
public class Income {
    public static RealmList<RealmIncome> getRealmList(List<Income> incomeList) {
        return new RealmList<>();
    }
}
