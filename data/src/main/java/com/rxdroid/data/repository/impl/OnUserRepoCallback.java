package com.rxdroid.data.repository.impl;

import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.data.repository.OnRealmErrorCallback;

/**
 * Created by rxdroid on 5/15/16.
 */
public interface OnUserRepoCallback extends OnRealmErrorCallback {

    void onMainUserAdded(RealmUser realmUser);

    void onMainUserLoaded(RealmUser realmUser);
}
