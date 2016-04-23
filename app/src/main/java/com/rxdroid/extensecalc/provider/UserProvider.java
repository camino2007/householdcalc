package com.rxdroid.extensecalc.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.rxdroid.data.RealmLoader;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.User;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

public final class UserProvider extends Observable {

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
            RealmUser realmuser = mRealmLoader.loadUser(userId);
            mUser = User.convertFromRealm(realmuser);
            Log.d(TAG, "loadUser... done!");
        }
    }

    public User getUser() {
        return mUser;
    }

    public void addExpense(Transaction expense) {
        mUser.addExpense(expense);
        persistUpdateForUser();
    }

    public void addIncome(Transaction income) {
        mUser.addIncome(income);
        persistUpdateForUser();
    }

    private void persistUpdateForUser() {
        RealmUser realmUser = User.convertToRealm(mUser);
        mRealmLoader.updateUser(realmUser);
    }


    public void persistUser(User user) {
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextUserKey());
        long userId = primaryKeyValue.get() + 1;
        Log.d(TAG, "persistUser - userId: " + userId);
        user.setId(userId);
        mUser = user;
        RealmUser realmUser = User.convertToRealm(user);
        mRealmLoader.persistUser(realmUser);
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(USER_ID, user.getId()).apply();
    }
}
