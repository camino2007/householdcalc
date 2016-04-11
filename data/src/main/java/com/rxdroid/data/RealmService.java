package com.rxdroid.data;

import io.realm.Realm;

/**
 * Created by rxdroid on 4/11/16.
 */
public class RealmService {

    private final Realm mRealm;

    public RealmService(Realm realm) {
        mRealm = realm;
    }

    public void closeRealm(){
        mRealm.close();
    }
}
