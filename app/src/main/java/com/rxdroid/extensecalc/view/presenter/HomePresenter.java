package com.rxdroid.extensecalc.view.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.data.repository.impl.OnTransactionRepoCallback;
import com.rxdroid.data.repository.impl.OnUserRepoCallback;
import com.rxdroid.data.repository.impl.TransactionRepository;
import com.rxdroid.data.repository.impl.UserRepository;
import com.rxdroid.extensecalc.internal.di.PerActivity;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.User;
import com.rxdroid.extensecalc.provider.UserProvider;
import com.rxdroid.extensecalc.view.HomeView;
import com.rxdroid.extensecalc.view.ViewPresenter;

import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;

/**
 * Created by rxdroid on 4/16/16.
 */
@PerActivity
public class HomePresenter implements ViewPresenter {

    private static final String TAG = "HomePresenter";

    private static final String PREFERENCES_USER = "com.rxdroid.PREFERENCES_USER";
    private static final String USER_ID = "userId";

    private HomeView mHomeView;

    @Inject UserProvider mUserProvider;
    @Inject UserRepository mUserRepository;
    @Inject TransactionRepository mTransactionRepository;

    @Inject
    public HomePresenter() {

    }

    public void setHomeView(HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public void resume() {
        mTransactionRepository.setCallback(new OnRealmTransactionCallback());
        mUserRepository.setCallback(new OnRealmUserCallback());
    }

    @Override
    public void pause() {
        mTransactionRepository.setCallback(null);
        mUserRepository.setCallback(null);
    }

    @Override
    public void destroy() {
        mHomeView = null;
    }

    public void addIncome(Transaction income) {
        Log.d(TAG, "addIncome: " + income.getAmount());
        mUserProvider.addIncome(income);
        RealmTransaction realmTransaction = Transaction.convertToRealm(income);
        mTransactionRepository.addIncomeTransactionByUserId(realmTransaction, realmTransaction.getUserId());
    }

    public void addExpense(Transaction expense) {
        Log.d(TAG, "addExpense: " + expense.getAmount());
        mUserProvider.addExpense(expense);
        RealmTransaction realmTransaction = Transaction.convertToRealm(expense);
        mTransactionRepository.addExpenseTransactionByUserId(realmTransaction, realmTransaction.getUserId());
    }

    private void showLoading() {
        mHomeView.showLoading();
    }

    private void hideLoading() {
        mHomeView.hideLoading();
    }


    public void loadTransactionsData(int queryForCurrentMonth) {
        // showLoading();


/*        UserResult userResult = mTransactionProvider.calcUserDataForMonth(queryForCurrentMonth);
        if (userResult != null) {
            mHomeView.onUserDataDone(userResult);
        } else {
            Log.d(TAG, "loadTransactionsData == NULL!");
        }
        hideLoading();*/
    }

    public void loadUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        long userId = sharedPreferences.getLong(USER_ID, 0L);
        Log.d(TAG, "loadUser: " + userId);
        if (userId > 0L) {
            Log.d(TAG, "loadUser - userId: " + userId);
            mUserRepository.getMainUserById(userId);
            //.getMainUserById(userId);
         /*   Observable<RealmUser> realmUserObservable = mRealmLoader.getLoadUserObservable(userId);

            mRealmLoader.execute(realmUserObservable, new UserSubscriber());*/
        }

    }

    private class OnRealmUserCallback implements OnUserRepoCallback {

        @Override
        public void onMainUserAdded(RealmUser realmUser) {
            Log.d(TAG, "onMainUserAdded: ");
        }

        @Override
        public void onMainUserLoaded(RealmUser realmUser) {
            Log.d(TAG, "onMainUserLoaded: ");
            User user = User.convertFromRealm(realmUser);
            mUserProvider.onUserLoaded(user);
        }
    }

    private class OnRealmTransactionCallback implements OnTransactionRepoCallback {

        @Override
        public void onIncomeTransactionAdded(RealmTransaction realmIncome) {
            Log.d(TAG, "onIncomeTransactionAdded: ");
            mTransactionRepository.getAllIncomeTransactionsByUserId(mUserProvider.getUser().getId());
        }

        @Override
        public void onExpenseTransactionAdded(RealmTransaction realmExpense) {
            Log.d(TAG, "onExpenseTransactionAdded: ");
            mTransactionRepository.getAllExpenseTransactionsByUserId(mUserProvider.getUser().getId());
        }

        @Override
        public void onAllIncomes(RealmList<RealmTransaction> incomeList) {
            List<Transaction> incomes = Transaction.convertTransactionsFromToRealmList(incomeList);
            for (Transaction income : incomes) {
                Log.d(TAG, "onAllIncomes - getAmount: " + income.getAmount());
            }
        }

        @Override
        public void onAllExpenses(RealmList<RealmTransaction> expenseList) {
            List<Transaction> expenses = Transaction.convertTransactionsFromToRealmList(expenseList);
            for (Transaction expense : expenses) {
                Log.d(TAG, "onAllExpenses - getAmount: " + expense.getAmount());
            }
        }
    }

}
