package com.rxdroid.extensecalc;

import android.app.Application;

import com.rxdroid.extensecalc.internal.di.components.AppComponent;
import com.rxdroid.extensecalc.internal.di.components.DaggerAppComponent;
import com.rxdroid.extensecalc.internal.di.modules.AppModule;
import com.squareup.leakcanary.LeakCanary;

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

        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
