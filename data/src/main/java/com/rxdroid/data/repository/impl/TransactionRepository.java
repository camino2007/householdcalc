package com.rxdroid.data.repository.impl;

import android.util.Log;

import com.rxdroid.data.RealmService;
import com.rxdroid.data.RealmTable;
import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.data.repository.ITransactionRepository;

import javax.inject.Inject;

/**
 * Created by rxdroid on 5/15/16.
 */
public class TransactionRepository implements ITransactionRepository {

    private static final String TAG = "TransactionRepository";

    private final RealmService mRealmService;

    private OnTransactionRepoCallback mCallback;

    @Inject
    public TransactionRepository(RealmService realmService) {
        mRealmService = realmService;
    }

    @Override
    public void setCallback(OnTransactionRepoCallback transactionRepoCallback) {
        mCallback = transactionRepoCallback;
    }

    @Override
    public void addIncomeTransactionByUserId(RealmTransaction transaction, long userId) {
        long transId = mRealmService.getNextTransactionKey();
        Log.d(TAG, "addIncomeTransactionByUserId - transId: " + transId);
        transaction.setId(transId);
        mRealmService.getRealm().beginTransaction();
        transaction = mRealmService.getRealm().copyToRealm(transaction);
        RealmUser realmUser = mRealmService.getRealm()
                .where(RealmUser.class)
                .equalTo(RealmTable.ID, userId)
                .findFirst();
        realmUser.getIncomeList().add(transaction);
        mRealmService.getRealm().commitTransaction();
        if (mCallback != null) {
            mCallback.onIncomeTransactionAdded(transaction);
        }
    }

    @Override
    public void addExpenseTransactionByUserId(RealmTransaction transaction, long userId) {
        long transId = mRealmService.getNextTransactionKey();
        Log.d(TAG, "addExpenseTransactionByUserId - transId: " + transId);
        transaction.setId(transId);
        mRealmService.getRealm().beginTransaction();
        transaction = mRealmService.getRealm().copyToRealm(transaction);
        RealmUser realmUser = mRealmService.getRealm()
                .where(RealmUser.class)
                .equalTo(RealmTable.ID, userId)
                .findFirst();
        realmUser.getExpenseList().add(transaction);
        mRealmService.getRealm().commitTransaction();
        if (mCallback != null) {
            mCallback.onExpenseTransactionAdded(transaction);
        }
    }

    @Override
    public void deleteTransactionById(long transactionId) {

    }

    @Override
    public void getAllIncomeTransactionsByUserId(long userId) {
        RealmUser realmUser = mRealmService.getRealm()
                .where(RealmUser.class)
                .equalTo(RealmTable.ID, userId)
                .findFirst();
        if (mCallback != null) {
            mCallback.onAllIncomes(realmUser.getIncomeList());
        }
    }

    @Override
    public void getAllExpenseTransactionsByUserId(long userId) {
        RealmUser realmUser = mRealmService.getRealm()
                .where(RealmUser.class)
                .equalTo(RealmTable.ID, userId)
                .findFirst();
        if (mCallback != null) {
            mCallback.onAllExpenses(realmUser.getExpenseList());
        }
    }

    @Override
    public void getTransactionById(long userId) {

    }
}
