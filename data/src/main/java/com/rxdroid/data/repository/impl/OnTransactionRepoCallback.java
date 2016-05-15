package com.rxdroid.data.repository.impl;

import com.rxdroid.data.realmmodels.RealmTransaction;

import io.realm.RealmList;

/**
 * Created by rxdroid on 5/15/16.
 */
public interface OnTransactionRepoCallback {

    void onIncomeTransactionAdded(RealmTransaction realmIncome);

    void onExpenseTransactionAdded(RealmTransaction realmExpense);

    void onAllIncomes(RealmList<RealmTransaction> incomeList);

    void onAllExpenses(RealmList<RealmTransaction> expenseList);
}
