package com.rxdroid.extensecalc.internal.di.components;

import android.content.Context;

import com.rxdroid.extensecalc.view.activities.BaseActivity;
import com.rxdroid.extensecalc.internal.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * Created by robert on 21.03.16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(BaseActivity baseActivity);

    Realm realm();

    Context context();

    Retrofit retrofit();

/*    UserProvider userProvider();

    ErrorUtils errorUtils();*/
}
