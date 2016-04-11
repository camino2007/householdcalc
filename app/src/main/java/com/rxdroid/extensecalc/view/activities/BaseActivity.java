package com.rxdroid.extensecalc.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rxdroid.extensecalc.BaseApplication;
import com.rxdroid.extensecalc.internal.di.HasComponent;
import com.rxdroid.extensecalc.internal.di.components.ApiComponent;
import com.rxdroid.extensecalc.internal.di.components.AppComponent;
import com.rxdroid.extensecalc.internal.di.modules.ActivityModule;
import com.rxdroid.extensecalc.internal.di.modules.ApiModule;

/**
 * Created by rxdroid on 4/11/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements HasComponent<ApiComponent> {

    private ApiComponent mApiComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(getTagText(), "onCreate");
        setContentView(getLayoutId());
        getApplicationComponent().inject(this);
        initializeInjector();
        initializeActionBar();
        initializeActivity(savedInstanceState);
    }

    private void initializeInjector() {
        mApiComponent = DaggerApiComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .apiModule(new ApiModule())
                .build();
    }

    @Override
    public ApiComponent getComponent() {
        return mApiComponent;
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link AppComponent}
     */
    protected AppComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected abstract int getLayoutId();

    protected abstract String getTagText();

    protected abstract void initializeActionBar();

    protected abstract void initializeActivity(Bundle savedInstanceState);

}
