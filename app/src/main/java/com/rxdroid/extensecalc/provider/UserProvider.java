package com.rxdroid.extensecalc.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.rxdroid.extensecalc.model.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import javax.inject.Inject;



public final class UserProvider extends Observable {

    private static final String TAG = "UserProvider";
    private static final String USER_FILE = "userFile.json";
    private static final String PREFERENCES_USER = "net.tipzilla.PREFERENCES_USER";
    private static final String USER_CREDENTIALS = "userCredentials";
    private static final String USER_MAIL = "userMail";
    private static final String USER_PW = "userPw";

    private User mUser;
    private Context mContext;



    @Inject
    public UserProvider(Context context) {
        this.mContext = context;
    }

    public void loggedIn(User user) {
        Log.d(TAG, "loggedIn");
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        String mail = sharedPreferences.getString(USER_MAIL, "");
        String pw = sharedPreferences.getString(USER_PW, "");
      /*  user.setMail(mail);
        user.setPassword(pw);*/
        this.mUser = user;
        updateSettings(true);
    }

    public User getUser() {
        return mUser;
    }

    public boolean isLoggedIn() {
        return mUser != null;
    }

    public void loggedOut() {
        Log.d(TAG, "loggedOut");
        mUser = null;
        updateSettings(false);
    }

    private void updateSettings(boolean isLoggedIn) {
        saveUser();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(USER_CREDENTIALS, isLoggedIn).apply();
        setChanged();
        notifyObservers();
    }

    public void loadUser() {
        Log.d(TAG, "loadUser!");
        User user = null;
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        boolean isUserAvailable = sharedPreferences.getBoolean(USER_CREDENTIALS, false);
        if (isUserAvailable) {
            Gson gson = new Gson();
            FileInputStream fileInputStream;
            InputStreamReader inputStreamReader;
            try {
                fileInputStream = mContext.openFileInput(USER_FILE);
                inputStreamReader = new InputStreamReader(fileInputStream);
                user = gson.fromJson(inputStreamReader, User.class);
                inputStreamReader.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "loadUser - FileNotFoundException: ", e);
            } catch (IOException e) {
                Log.e(TAG, "loadUser - IOException: ", e);
            }
            if (user != null) {
                mUser = user;
            }
        }
    }

    public void storeUserCredentials(String mail, String password) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(PREFERENCES_USER, Context.MODE_PRIVATE);
        sharedPreferences
                .edit()
                .putString(USER_MAIL, mail)
                .putString(USER_PW, password)
                .apply();
    }

    private void saveUser() {
        Log.d(TAG, "saveUser");
        if (mUser != null) {
            Gson gson = new Gson();
            String json = gson.toJson(mUser);
            FileOutputStream outputStream;
            try {
                outputStream = mContext.openFileOutput(USER_FILE, Context.MODE_PRIVATE);
                outputStream.write(json.getBytes());
                outputStream.close();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "saveUser - FileNotFoundException: ", e);
            } catch (IOException e) {
                Log.e(TAG, "saveUser - IOException: ", e);
            }
            Log.d(TAG, "saveUser... done!");
        }
    }


}
