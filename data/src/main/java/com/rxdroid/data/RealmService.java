package com.rxdroid.data;

import android.content.Context;

import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.data.realmmodels.RealmUser;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rxdroid on 5/15/16.
 */
public final class RealmService {

    private Realm mRealm;

    public RealmService(Context context) {
        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                .modules(new SimpleRealmModule())
                .schemaVersion(1)
                .build();

        //TODO add Realm migration
    /*
     https://github.com/realm/realm-java/blob/master/examples/migrationExample/src/main/java/io/realm/examples/realmmigrationexample/model/Migration.java
     try {
            Realm.migrateRealm(config0, new Migration());
        } catch (FileNotFoundException ignored) {
            // If the Realm file doesn't exist, just ignore.
        }*/


        // Get a Realm instance for this thread
        mRealm = Realm.getInstance(realmConfig);
    }

    public Realm getRealm() {
        return mRealm;
    }

    public long getNextUserKey() {
        Number lastPrimaryKey = mRealm.where(RealmUser.class).max("id");
        if (lastPrimaryKey != null) {
            return lastPrimaryKey.longValue() + 1;
        }
        return 1;
    }

    public long getNextTransactionKey() {
        Number lastPrimaryKey = mRealm.where(RealmTransaction.class).max("id");
        if (lastPrimaryKey != null) {
            return lastPrimaryKey.longValue() + 1;
        }
        return 100;
    }
}
