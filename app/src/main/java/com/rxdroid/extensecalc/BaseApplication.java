package com.rxdroid.extensecalc;

import android.app.Application;

import com.rxdroid.extensecalc.internal.di.components.AppComponent;
import com.rxdroid.extensecalc.internal.di.components.DaggerAppComponent;
import com.rxdroid.extensecalc.internal.di.modules.AppModule;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by rxdroid on 4/11/16.
 */
public class BaseApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        initRealmConfiguration();

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
