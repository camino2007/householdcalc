package com.rxdroid.extensecalc.model;

import com.rxdroid.data.enums.MoneyType;
import com.rxdroid.data.enums.PaymentRate;
import com.rxdroid.extensecalc.enums.Currency;

import java.util.Calendar;

/**
 * Created by rxdroid on 4/17/16.
 */
public abstract class MoneyModel {

    private Calendar mDate;
    private float mAmount;
    private Currency mCurrency;
    private PaymentRate mPaymentRate;
    private MoneyType mMoneyType;

    public MoneyModel(MoneyBuilder moneyBuilder) {
        mDate = moneyBuilder.getDate();
        mAmount = moneyBuilder.getAmount();
        mCurrency = moneyBuilder.getCurrency();
        mPaymentRate = moneyBuilder.getPaymentRate();
        mMoneyType = moneyBuilder.getMoneyType();
    }

    public Calendar getDate() {
        return mDate;
    }

    public float getAmount() {
        return mAmount;
    }

    public Currency getCurrency() {
        return mCurrency;
    }

    public PaymentRate getPaymentRate() {
        return mPaymentRate;
    }

    public MoneyType getMoneyType() {
        return mMoneyType;
    }

}
