package com.rxdroid.extensecalc.internal.di.modules;

import android.app.Activity;

import com.rxdroid.extensecalc.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by robert on 22.03.16.
 * A module to wrap the Activity state and expose it to the graph.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }

}
