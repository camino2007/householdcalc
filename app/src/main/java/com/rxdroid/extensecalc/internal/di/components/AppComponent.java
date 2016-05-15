package com.rxdroid.extensecalc.internal.di.components;

import android.content.Context;

import com.rxdroid.data.RealmService;
import com.rxdroid.extensecalc.internal.di.modules.AppModule;
import com.rxdroid.extensecalc.provider.TransactionProvider;
import com.rxdroid.extensecalc.provider.UserProvider;
import com.rxdroid.extensecalc.view.activities.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by robert on 21.03.16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseActivity baseActivity);

    UserProvider userProvider();

    RealmService realmService();

    Context context();

/*

    ErrorUtils errorUtils();*/
}
