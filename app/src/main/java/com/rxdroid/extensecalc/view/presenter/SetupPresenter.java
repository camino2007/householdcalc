package com.rxdroid.extensecalc.view.presenter;

import android.util.Log;

import com.rxdroid.data.RealmLoader;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.extensecalc.view.SetupView;
import com.rxdroid.extensecalc.view.ViewPresenter;

import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/16/16.
 */
public class SetupPresenter implements ViewPresenter {

    private static final String TAG = "SetupPresenter";

    private SetupView mSetupView;

    @Inject RealmLoader mRealmLoader;

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

    public void persistUser(RealmUser realmUser) {
        AtomicLong primaryKeyValue = new AtomicLong(mRealmLoader.getNextPrimaryKey());
        Log.d(TAG, "persistUser - primaryKeyValue: "+primaryKeyValue.get());
        realmUser.setId(primaryKeyValue.get()+1);
        mRealmLoader.persistUser(realmUser);
    }
}
