package com.rxdroid.extensecalc.view;

import com.rxdroid.extensecalc.model.UserResult;

/**
 * Created by rxdroid on 4/16/16.
 */
public interface HomeView extends LoadDataView {

    void onUserDataDone(UserResult userResult);
}
