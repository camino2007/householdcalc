package com.rxdroid.data.enums;

import com.rxdroid.data.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rxdroid on 4/11/16.
 */
public enum MoneyType {

    RENT(R.string.mt_rent),
    GROCERY(R.string.mt_grocery),
    CAR(R.string.mt_car),
    CLOTHING(R.string.mt_clothing),
    SHOES(R.string.mt_shoes),
    PARTY(R.string.mt_party),
    SPORTS(R.string.mt_sports),
    INSURANCE(R.string.mt_insurance),
    DRINKING(R.string.mt_alcohol),
    ENERGY(R.string.mt_energy),
    MEDICINE(R.string.mt_medicine),
    TABACAO(R.string.mt_tobacco);

    private int mStringResId;

    MoneyType(int stringResId) {
        mStringResId = stringResId;
    }

    public int getStringResId() {
        return mStringResId;
    }

    public static List<MoneyType> getMoneyTypes() {
        return Arrays.asList(MoneyType.class.getEnumConstants());
    }
}