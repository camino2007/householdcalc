package com.rxdroid.extensecalc.internal.di.components;


import com.rxdroid.extensecalc.internal.di.PerActivity;
import com.rxdroid.extensecalc.internal.di.modules.ActivityModule;
import com.rxdroid.extensecalc.internal.di.modules.ApiModule;
import com.rxdroid.extensecalc.view.fragments.HomeFragment;
import com.rxdroid.extensecalc.view.fragments.SetupFragment;

import dagger.Component;

/**
 * Created by robert on 21.03.16.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, ApiModule.class})
public interface ApiComponent extends ActivityComponent {

    void inject(SetupFragment setupFragment);

    void inject(HomeFragment homeFragment);

  /*  void inject(HomeFragment homeFragment);

    void inject(EventFragment eventFragment);

    void inject(LoginFragment loginFragment);

    void inject(CreateAccountFragment createAccountFragment);

    void inject(ChangeAccountFragment changeAccountFragment);

    void inject(BetFragment betFragment);

    void inject(UserBetStatsFragment userBetStatsFragment);*/

}
