package com.rxdroid.extensecalc.model;

import com.rxdroid.data.enums.PaymentRate;
import com.rxdroid.data.realmmodels.RealmExpense;

import java.util.Calendar;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by rxdroid on 4/16/16.
 */
public class Expense extends MoneyModel{

    public Expense(Builder builder) {
        super(builder);
    }

    public static RealmList<RealmExpense> getRealmList(List<Expense> expenseList) {
        return new RealmList<>();
    }

    public static Expense create(RealmExpense realmExpense) {
        PaymentRate paymentRate = getPaymentRateFromDb(realmExpense);
        Builder expenseBuilder = new Builder();
        return (Expense) expenseBuilder
                .setAmount(realmExpense.getAmount())
               // .setMoneyType(moneyType)
                .setPaymentRate(paymentRate)
                .setDate(Calendar.getInstance())
                .build();
    }

    private static PaymentRate getPaymentRateFromDb(RealmExpense realmExpense) {
       // realmExpense.
        return null;
    }

    public static class Builder extends MoneyBuilder{

        @Override
        public Expense build() {
            return new Expense(this);
        }
    }

}
