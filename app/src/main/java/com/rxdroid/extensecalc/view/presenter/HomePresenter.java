package com.rxdroid.extensecalc.view.presenter;

import android.util.Log;

import com.rxdroid.extensecalc.internal.di.PerActivity;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.provider.UserProvider;
import com.rxdroid.extensecalc.view.HomeView;
import com.rxdroid.extensecalc.view.ViewPresenter;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/16/16.
 */
@PerActivity
public class HomePresenter implements ViewPresenter {

    private static final String TAG = "HomePresenter";

    private HomeView mHomeView;

    @Inject UserProvider mUserProvider;

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
        Log.d(TAG, "addExpense: " + expense.getAmount());
       // mUserProvider.addExpense(expense);

/*        Observable.just(expense)
                .map(new Func1<Transaction, RealmTransaction>() {
                    @Override
                    public RealmTransaction call(Transaction transaction) {
                        return Transaction.convertToRealm(transaction);
                    }
                })
                .map(new Func1<RealmTransaction, Boolean>() {
                    @Override
                    public Boolean call(RealmTransaction realmTransaction) {
                        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextTransactionKey());
                        realmTransaction.setId(primaryKeyValue.get() + 1);
                        return mRealmLoader.addExpense(realmTransaction);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe();*/


/*        RealmTransaction realmTransaction = Transaction.convertToRealm(expense);
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextTransactionKey());
        realmTransaction.setId(primaryKeyValue.get() + 1);
        mRealmLoader.addExpense(realmTransaction, mUserProvider.getUser().getId());*/
    }

    private void showLoading() {
        mHomeView.showLoading();
    }

    private void hideLoading() {
        mHomeView.hideLoading();
    }

    public void addTransaction(Transaction transaction) {
        mUserProvider.addTransaction(transaction);

/*        RealmTransaction realmTransaction = Transaction.convertToRealm(transaction);
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextTransactionKey());
        realmTransaction.setId(primaryKeyValue.get() + 1);
        mRealmLoader.addTransaction(realmTransaction);*/
    }

   /* public void addIncome(Transaction income) {
        Log.d(TAG, "addIncome: " + income.getAmount());
        mUserProvider.addIncome(income);
        RealmTransaction realmTransaction = Transaction.convertToRealm(income);
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextTransactionKey());
        realmTransaction.setId(primaryKeyValue.get() + 1);
        mRealmLoader.addIncome(realmTransaction, mUserProvider.getUser().getId());
    }*/

    public void loadTransactionsData(int queryForCurrentMonth) {
        showLoading();


/*        UserResult userResult = mTransactionProvider.calcUserDataForMonth(queryForCurrentMonth);
        if (userResult != null) {
            mHomeView.onUserDataDone(userResult);
        } else {
            Log.d(TAG, "loadTransactionsData == NULL!");
        }
        hideLoading();*/
    }


}
