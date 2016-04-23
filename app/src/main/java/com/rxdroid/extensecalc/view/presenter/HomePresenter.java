package com.rxdroid.extensecalc.view.presenter;

import com.rxdroid.data.RealmLoader;
import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.extensecalc.internal.di.PerActivity;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.UserResult;
import com.rxdroid.extensecalc.provider.TransactionProvider;
import com.rxdroid.extensecalc.provider.UserProvider;
import com.rxdroid.extensecalc.view.HomeView;
import com.rxdroid.extensecalc.view.ViewPresenter;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/16/16.
 */
@PerActivity
public class HomePresenter implements ViewPresenter {

    private HomeView mHomeView;

    @Inject UserProvider mUserProvider;
    @Inject TransactionProvider mTransactionProvider;
    @Inject RealmLoader mRealmLoader;

    @Inject
    public HomePresenter() {
    }

    public void setHomeView(HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mHomeView = null;
    }

    public void addExpense(Transaction expense) {
        RealmTransaction realmTransaction = Transaction.convertToRealm(expense);
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextTransactionKey());
        realmTransaction.setId(primaryKeyValue.get() + 1);
        mUserProvider.addExpense(expense);
        mRealmLoader.addExpense(realmTransaction, mUserProvider.getUser().getId());
    }

    public void addIncome(Transaction income) {
        RealmTransaction realmTransaction = Transaction.convertToRealm(income);
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextTransactionKey());
        realmTransaction.setId(primaryKeyValue.get() + 1);
        mUserProvider.addIncome(income);
        mRealmLoader.addIncome(realmTransaction, mUserProvider.getUser().getId());
    }

    public void loadUserData(int queryForMonth) {
        showLoading();
        UserResult userResult = mTransactionProvider.calcUserDataForMonth(queryForMonth);
        if (userResult != null) {
            mHomeView.onUserDataDone(userResult);
        }
        hideLoading();
    }

    private void showLoading() {
        mHomeView.showLoading();
    }

    private void hideLoading() {
        mHomeView.hideLoading();
    }
}
