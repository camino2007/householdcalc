package com.rxdroid.extensecalc.model;

import com.rxdroid.data.realmmodels.RealmIncome;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by rxdroid on 4/16/16.
 */
public class Income extends MoneyModel {

    public Income(Builder builder) {
        super(builder);
    }

    public static RealmList<RealmIncome> getRealmList(List<Income> incomeList) {
        return new RealmList<>();
    }

    public static Income create(RealmIncome realmIncome) {
        return null;
    }

    public static class Builder extends MoneyBuilder{

        @Override
        public MoneyModel build() {
            return new Income(this);
        }
    }
}
