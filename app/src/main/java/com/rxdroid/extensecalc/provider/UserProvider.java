package com.rxdroid.extensecalc.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.domain.subscriber.DefaultSubscriber;
import com.rxdroid.extensecalc.enums.TransactionType;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.User;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

import rx.Observable;

public final class UserProvider {

    private static final String TAG = "UserProvider";
    private static final String PREFERENCES_USER = "com.rxdroid.PREFERENCES_USER";
    private static final String USER_ID = "userId";

    private User mUser;
    private Context mContext;

    private final RealmLoader mRealmLoader;

    @Inject
    public UserProvider(Context context, RealmLoader realmLoader) {
        this.mContext = context;
        this.mRealmLoader = realmLoader;
    }

    public void loadUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        long userId = sharedPreferences.getLong(USER_ID, 0L);
        if (userId > 0L) {
            Log.d(TAG, "loadUser - userId: " + userId);
            Observable<RealmUser> realmUserObservable = mRealmLoader.getLoadUserObservable(userId);

            mRealmLoader.execute(realmUserObservable, new UserSubscriber());
        }
    }

    public User getUser() {
        return mUser;
    }

    private void persistUpdateForUser() {
        RealmUser realmUser = User.convertToRealm(mUser);
        mRealmLoader.updateUser(realmUser);
    }


    public void initializePersistUser(User user) {
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextUserKey());
        long userId = primaryKeyValue.get() + 1;
        Log.d(TAG, "initializePersistUser - userId: " + userId);
        user.setId(userId);
        mUser = user;
        RealmUser realmUser = User.convertToRealm(user);
        mRealmLoader.persistUser(realmUser);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putLong(USER_ID, user.getId())
                .apply();
    }

    public void addTransaction(Transaction transaction) {
        Log.d(TAG, "addTransaction - getAmount: " + transaction.getAmount() + " getTransactionType: " + transaction.getTransactionType());
        if (transaction.getTransactionType() == TransactionType.INCOME) {
            mUser.addIncome(transaction);
        } else {
            mUser.addExpense(transaction);
        }
        persistUpdateForUser();
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
                Log.d(TAG, "loadUserDone");
                for (Transaction expense : mUser.getExpenseList()) {
                    Log.d(TAG, "expense - onNext: " + expense.getAmount());
                }
                for (Transaction income : mUser.getIncomeList()) {
                    Log.d(TAG, "income - onNext: " + income.getAmount());
                }
            }

        }
    }

}
