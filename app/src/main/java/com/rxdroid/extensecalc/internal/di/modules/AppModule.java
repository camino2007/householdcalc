package com.rxdroid.extensecalc.internal.di.modules;

import android.content.Context;

import com.github.simonpercic.oklog3.OkLogInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rxdroid.data.RealmService;
import com.rxdroid.domain.util.NetworkUtil;
import com.rxdroid.extensecalc.BaseApplication;
import com.rxdroid.extensecalc.BuildConfig;
import com.rxdroid.extensecalc.provider.TransactionProvider;
import com.rxdroid.extensecalc.provider.UserProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by robert on 21.03.16.
 */
@Module
public class AppModule {

    private static final int TIME_OUT = 30;
    private static final String BASE_URL = "http://stage.tipzil.la/";

    private BaseApplication mBaseApplication;

    public AppModule(BaseApplication baseApplication) {
        mBaseApplication = baseApplication;
    }

    @Provides
    @Singleton
    Context providesApplication() {
        return mBaseApplication;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context context) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(context.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .cache(cache);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(OkLogInterceptor.builder().build());
        }
        return builder.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    NetworkUtil provideNetworkUtil(Context context) {
        return new NetworkUtil(context.getApplicationContext());
    }

    @Provides
    @Singleton
    UserProvider provideUserProvider(Context context, TransactionProvider transactionProvider) {
        return new UserProvider(context.getApplicationContext(), transactionProvider);
    }

    @Provides
    @Singleton
    TransactionProvider provideTransactionManager() {
        return new TransactionProvider();
    }

    @Provides
    @Singleton
    RealmService provideRealmService(Context context) {
        return new RealmService(context);
    }

}
