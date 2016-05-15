package com.rxdroid.data.repository.impl;

import android.util.Log;

import com.rxdroid.data.RealmService;
import com.rxdroid.data.RealmTable;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.data.repository.IUserRepository;

import javax.inject.Inject;

/**
 * Created by rxdroid on 5/15/16.
 */
public class UserRepository implements IUserRepository {

    private static final String TAG = "UserRepository";

    private final RealmService mRealmService;

    private OnUserRepoCallback mCallback;

    @Inject
    public UserRepository(RealmService realmService) {
        mRealmService = realmService;
    }

    @Override
    public void setCallback(OnUserRepoCallback userRepoCallback) {
        mCallback = userRepoCallback;
    }

    @Override
    public void addMainUser(RealmUser user) {
        long userId = mRealmService.getNextUserKey();
        Log.d(TAG, "addMainUser: " + userId);
        user.setId(userId);
        mRealmService.getRealm().beginTransaction();
        mRealmService.getRealm().copyToRealm(user);
        mRealmService.getRealm().commitTransaction();
        mCallback.onMainUserAdded(user);
    }

    @Override
    public void addChildUser(RealmUser childUser) {

    }

    @Override
    public void deleteChildUserById(long childUserId) {

    }

    @Override
    public void getChildUserById(long childUserId) {

    }

    @Override
    public void getMainUserById(long userId) {
        RealmUser realmUser = mRealmService.getRealm()
                .where(RealmUser.class)
                .equalTo(RealmTable.ID, userId)
                .findFirst();
        if (mCallback != null) {
            mCallback.onMainUserLoaded(realmUser);
        }

    }
}
