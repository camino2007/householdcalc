package com.rxdroid.extensecalc.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rxdroid on 4/28/16.
 */
public enum TransactionType {

    INCOME, EXPENSE;


    public static List<TransactionType> getTypes() {
        return Arrays.asList(TransactionType.class.getEnumConstants());
    }
}
