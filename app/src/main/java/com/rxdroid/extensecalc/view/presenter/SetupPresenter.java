package com.rxdroid.extensecalc.view.presenter;

import com.rxdroid.extensecalc.view.SetupView;
import com.rxdroid.extensecalc.view.ViewPresenter;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/16/16.
 */
public class SetupPresenter implements ViewPresenter {

    private SetupView mSetupView;

    @Inject
    public SetupPresenter() {

    }

    public void setSetupView(SetupView setupView) {
        mSetupView = setupView;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
