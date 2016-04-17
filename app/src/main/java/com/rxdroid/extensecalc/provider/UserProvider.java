package com.rxdroid.extensecalc.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.rxdroid.data.RealmLoader;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.extensecalc.model.Expense;
import com.rxdroid.extensecalc.model.User;

import java.util.Observable;

import javax.inject.Inject;

public final class UserProvider extends Observable {

    private static final String TAG = "UserProvider";
    private static final String USER_FILE = "userFile.json";
    private static final String PREFERENCES_USER = "com.rxdroid.PREFERENCES_USER";
    private static final String USER_CREDENTIALS = "userCredentials";
    private static final String USER_ID = "userId";

    private User mUser;
    private Context mContext;

    @Inject RealmLoader mRealmLoader;

    @Inject
    public UserProvider(Context context) {
        this.mContext = context;
    }

    public void loadUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString(USER_ID, "");
        if (!TextUtils.isEmpty(userId)) {
            RealmUser realmuser = mRealmLoader.loadUser(userId);
            mUser = User.create(realmuser);
        }
    }

    public void addExpense(Expense expense) {

    }


}
