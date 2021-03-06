package com.rxdroid.extensecalc.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.domain.subscriber.DefaultSubscriber;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.TransactionDataWrapper;
import com.rxdroid.extensecalc.model.User;

import java.util.List;

import javax.inject.Inject;

public final class UserProvider {

    private static final String TAG = "UserProvider";

    private static final String PREFERENCES_USER = "com.rxdroid.PREFERENCES_USER";
    private static final String USER_ID = "userId";

    private User mUser;
    private Context mContext;
    private final TransactionProvider mTransactionProvider;

    @Inject
    public UserProvider(Context context, TransactionProvider transactionProvider) {
        this.mContext = context;
        this.mTransactionProvider = transactionProvider;
    }

    public User getUser() {
        return mUser;
    }

    public void addIncome(Transaction income) {
        mUser.getIncomeList().add(income);
    }

    public void addExpense(Transaction expense) {
        mUser.getExpenseList().add(expense);
    }

    public void onUserLoaded(User user) {
        mUser = user;
        Log.d(TAG, "onUserLoaded - user.getId() : " + user.getId());
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putLong(USER_ID, user.getId())
                .apply();
    }

    public List<TransactionDataWrapper> getUserTransactionData(int currentMonth, int currentYear) {

        mTransactionProvider.calcUserDataForMonth(currentMonth, currentYear, mUser);


    }

    private class UserSubscriber extends DefaultSubscriber<RealmUser> {
        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError: ", e);
        }

        @Override
        public void onNext(RealmUser realmUser) {
            if (realmUser.isLoaded()) {
                mUser = User.convertFromRealm(realmUser);

                for (Transaction expense : mUser.getExpenseList()) {
                    Log.d(TAG, "expense - onNext: " + expense.getAmount());
                }
                for (Transaction income : mUser.getIncomeList()) {
                    Log.d(TAG, "income - onNext: " + income.getAmount());
                }
                Log.d(TAG, "loadUserDone");
            }

        }
    }


}
