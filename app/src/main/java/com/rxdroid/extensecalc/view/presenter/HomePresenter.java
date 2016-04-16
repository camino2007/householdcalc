package com.rxdroid.extensecalc.view.presenter;

import com.rxdroid.extensecalc.internal.di.PerActivity;
import com.rxdroid.extensecalc.view.HomeView;
import com.rxdroid.extensecalc.view.ViewPresenter;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/16/16.
 */
@PerActivity
public class HomePresenter implements ViewPresenter {

    private HomeView mHomeView;

    @Inject
    public HomePresenter() {
    }

    public void setHomeView(HomeView homeView) {
        mHomeView = homeView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mHomeView = null;
    }
}
