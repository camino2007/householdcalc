package com.rxdroid.extensecalc.internal.di.components;

import android.app.Activity;

import com.rxdroid.extensecalc.internal.di.PerActivity;
import com.rxdroid.extensecalc.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by robert on 22.03.16.
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 * <p/>
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity activity();

}
