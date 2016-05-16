package com.rxdroid.data.repository.impl;

import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.data.repository.OnRealmErrorCallback;

import io.realm.RealmList;

/**
 * Created by rxdroid on 5/15/16.
 */
public interface OnTransactionRepoCallback extends OnRealmErrorCallback {

    void onIncomeTransactionAdded(RealmTransaction realmIncome);

    void onExpenseTransactionAdded(RealmTransaction realmExpense);

    void onAllIncomes(RealmList<RealmTransaction> incomeList);

    void onAllExpenses(RealmList<RealmTransaction> expenseList);
}
