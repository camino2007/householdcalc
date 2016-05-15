package com.rxdroid.data;

import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.data.realmmodels.RealmUser;

import io.realm.annotations.RealmModule;

/**
 * Created by rxdroid on 5/15/16.
 */
@RealmModule(classes = {RealmUser.class, RealmTransaction.class})
public class SimpleRealmModule {
}
