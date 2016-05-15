package com.rxdroid.data;

/**
 * Created by rxdroid on 5/15/16.
 */
public interface RealmTable {

    String ID = "id";

    interface User {

        String NAME = "name";
        String CURRENCY = "currency";
        String BACKUP_TYPE = "backupType";
        String INCOMES = "incomeList";
        String EXPENSES = "expenseList";
        String CHILD_USERS = "childUserList";

    }

    interface Transaction {

        String USER_ID = "userId";
        String YEAR = "year";
        String MONTH = "month";
        String DAY = "day";
        String AMOUNT = "amount";
        String IS_SCHEDULED = "isScheduled";
        String SCHEDULED_YEAR = "yearScheduled";
        String SCHEDULED_MONTH = "monthScheduled";
        String SCHEDULED_DAY = "dayScheduled";
        String ISSUE = "issue";
        String PAYMENT_RATE = "paymentRate";
        String DESCRIPTION = "description";
        String TRANSACTION_TAYPE = "transactionType";

    }

}
