package com.rxdroid.data.repository.impl;

import com.rxdroid.data.realmmodels.RealmUser;

/**
 * Created by rxdroid on 5/15/16.
 */
public interface OnUserRepoCallback {

    void onMainUserAdded(RealmUser realmUser);

    void onMainUserLoaded(RealmUser realmUser);
}
