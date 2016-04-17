package com.rxdroid.data.enums;

import com.rxdroid.data.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rxdroid on 4/11/16.
 */
public enum PaymentRate {

    ONE_TIME(R.string.pr_one_time),
    DAILY(R.string.pr_daily),
    WEEKLY(R.string.pr_weekly),
    TWO_WEEKLY(R.string.pr_two_weekly),
    MONTHLY(R.string.pr_monthly),
    THREE_MONTHLY(R.string.pr_three_moths),
    ANNUALLY(R.string.pr_annually);

    private int mStringResId;

    PaymentRate(int stringResId) {
        mStringResId = stringResId;
    }

    public int getStringResId() {
        return mStringResId;
    }

    public static List<PaymentRate> getPaymentRates() {
        return Arrays.asList(PaymentRate.class.getEnumConstants());
    }

}

