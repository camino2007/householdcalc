package com.rxdroid.extensecalc.view.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.view.fragments.HomeFragment;
import com.rxdroid.extensecalc.view.fragments.SetupFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String PREFERENCES_APP = "net.tipzilla.PREFERENCES_APP";
    private static final String IS_FIRST_APP_START = "isFirstAppStart";

    @Bind(R.id.toolbar) Toolbar mToolbar;

    private DropboxAPI<AndroidAuthSession> mDBApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
     /*   AppKeyPair appKeys = new AppKeyPair(Constants.APP_KEY, Constants.APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<>(session);
        mDBApi.getSession().startOAuth2Authentication(this);*/
    }

    @OnClick(R.id.fab)
    public void onFloatingActionBtnClicked() {
        showAddExpenseDialog();
    }

    private void showAddExpenseDialog() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected String getTagText() {
        return MainActivity.class.getSimpleName();
    }

    @Override
    protected void initializeActionBar() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE);
        boolean isFirstAppStart = sharedPreferences.getBoolean(IS_FIRST_APP_START, true);
        Log.d(TAG, "initializeActionBar - isFirstAppStart: " + isFirstAppStart);
        if (isFirstAppStart) {
            mToolbar.setTitle(getString(R.string.main_ab_setup));
        } else {
            mToolbar.setTitle(getString(R.string.app_name));
        }
    }

    @Override
    protected void initializeActivity(Bundle savedInstanceState) {
        Log.d(TAG, "initializeActivity");
      /*  SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE);
        boolean isFirstAppStart = sharedPreferences.getBoolean(IS_FIRST_APP_START, true);
        if (isFirstAppStart) {
            showSetupFragment();
            sharedPreferences.edit().putBoolean(IS_FIRST_APP_START, false).apply();
        } else {
            showHomeFragment();
        }*/
        showSetupFragment();
    }

    private void showHomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (homeFragment == null) {
            homeFragment = HomeFragment.initialize();
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, homeFragment, HomeFragment.class.getSimpleName())
                .commit();
    }

    private void showSetupFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        SetupFragment setupFragment = (SetupFragment) fragmentManager.findFragmentByTag(SetupFragment.class.getSimpleName());
        if (setupFragment == null) {
            setupFragment = SetupFragment.initialize();
        }
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, setupFragment, SetupFragment.class.getSimpleName())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
     /*   if (mDBApi.getSession().authenticationSuccessful()) {
            try {
                // Required to complete auth, sets the access token on the session
                mDBApi.getSession().finishAuthentication();
                String accessToken = mDBApi.getSession().getOAuth2AccessToken();
                Log.d(TAG, "onResume - accessToken: " + accessToken);
            } catch (IllegalStateException e) {
                Log.i("DbAuthLog", "Error authenticating", e);
            }
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.e(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
