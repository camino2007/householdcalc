package com.rxdroid.extensecalc.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rxdroid on 4/16/16.
 */
public enum Currency {

    EUR("€", "EUR"),
    USD("$", "USD"),
    GBP("£", "GBP"),
    CHF("SFr", "CHF");

    private String mSign;
    private String mCurrency;

    Currency(String sign, String currency) {
        mSign = sign;
        mCurrency = currency;
    }

    public String getSign() {
        return mSign;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public static List<Currency> getCurrencies() {
        return Arrays.asList(Currency.class.getEnumConstants());
    }
}
