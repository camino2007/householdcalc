package com.rxdroid.extensecalc.view.presenter;

import android.util.Log;

import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.data.repository.impl.OnUserRepoCallback;
import com.rxdroid.data.repository.impl.UserRepository;
import com.rxdroid.extensecalc.model.User;
import com.rxdroid.extensecalc.provider.UserProvider;
import com.rxdroid.extensecalc.view.SetupView;
import com.rxdroid.extensecalc.view.ViewPresenter;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/16/16.
 */
public class SetupPresenter implements ViewPresenter {

    private static final String TAG = "SetupPresenter";

    private SetupView mSetupView;
    @Inject UserRepository mUserRepository;
    @Inject UserProvider mUserProvider;

    @Inject
    public SetupPresenter() {

    }

    public void setSetupView(SetupView setupView) {
        mSetupView = setupView;
        mUserRepository.setCallback(new UserRepoCallback());
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        mSetupView = null;
        mUserRepository.setCallback(null);
    }

    public void persistUser(User user) {
        mSetupView.showLoading();
        RealmUser realmUser = User.convertToRealm(user);
        mUserRepository.addMainUser(realmUser);
    }

    private class UserRepoCallback implements OnUserRepoCallback {

        @Override
        public void onMainUserAdded(RealmUser realmUser) {
            Log.d(TAG, "onMainUserAdded - realmUser.getId(): " + realmUser.getId());
            User user = User.convertFromRealm(realmUser);
            mUserProvider.onUserLoaded(user);
            mSetupView.hideLoading();
            mSetupView.onUserLoaded();
        }

        @Override
        public void onMainUserLoaded(RealmUser realmUser) {
            Log.d(TAG, "onMainUserLoaded: ");
        }
    }
}
