package com.rxdroid.data.realmmodels;

import com.rxdroid.data.enums.PaymentRate;

import io.realm.RealmObject;

/**
 * Created by rxdroid on 4/11/16.
 */
public class RealmIncome extends RealmObject{

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mAmount;
    private String mCurrency;

    private String mEnumDescription;

    public void saveEnum(PaymentRate paymentRate) {
        this.mEnumDescription = paymentRate.toString();
    }

    public PaymentRate getEnum() {
        return PaymentRate.valueOf(mEnumDescription);
    }



}
