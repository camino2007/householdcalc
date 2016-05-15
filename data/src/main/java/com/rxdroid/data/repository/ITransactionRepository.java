package com.rxdroid.data.repository;

import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.data.repository.impl.OnTransactionRepoCallback;

/**
 * Created by rxdroid on 5/15/16.
 */
public interface ITransactionRepository {

    void setCallback(OnTransactionRepoCallback transactionRepoCallback);

    void addIncomeTransactionByUserId(RealmTransaction transaction, long userId);

    void addExpenseTransactionByUserId(RealmTransaction transaction, long userId);

    void deleteTransactionById(long transactionId);

    void getAllIncomeTransactionsByUserId(long userId);

    void getAllExpenseTransactionsByUserId(long userId);

    void getTransactionById(long userId);

}
