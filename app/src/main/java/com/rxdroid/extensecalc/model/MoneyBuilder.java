package com.rxdroid.extensecalc.model;

import com.rxdroid.data.enums.MoneyType;
import com.rxdroid.data.enums.PaymentRate;
import com.rxdroid.extensecalc.enums.Currency;

import java.util.Calendar;

/**
 * Created by rxdroid on 4/17/16.
 */
public abstract class MoneyBuilder<T> {

    private Calendar date;
    private float amount;
    private Currency currency;
    private PaymentRate paymentRate;
    private MoneyType moneyType;

    public MoneyBuilder setDate(Calendar date) {
        this.date = date;
        return this;
    }

    public MoneyBuilder setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public MoneyBuilder setCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public MoneyBuilder setPaymentRate(PaymentRate paymentRate) {
        this.paymentRate = paymentRate;
        return this;
    }

    public MoneyBuilder setMoneyType(MoneyType moneyType) {
        this.moneyType = moneyType;
        return this;
    }

    public Calendar getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public PaymentRate getPaymentRate() {
        return paymentRate;
    }

    public MoneyType getMoneyType() {
        return moneyType;
    }

    public abstract T build();

}
