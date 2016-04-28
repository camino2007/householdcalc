package com.rxdroid.data.enums;

import android.content.Context;

import com.rxdroid.data.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rxdroid on 4/11/16.
 */
public enum IssueType {

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
    TOBACCO(R.string.mt_tobacco);

    private int mStringResId;

    IssueType(int stringResId) {
        mStringResId = stringResId;
    }

    public int getStringResId() {
        return mStringResId;
    }

    public static List<IssueType> getTypes() {
        return Arrays.asList(IssueType.class.getEnumConstants());
    }

    public static List<String> getIssueTypeStrings(Context context) {
        List<String> strings = new ArrayList<>();
        for (IssueType issueType : getTypes()) {
            strings.add(context.getString(issueType.getStringResId()));
        }
        return strings;
    }
}