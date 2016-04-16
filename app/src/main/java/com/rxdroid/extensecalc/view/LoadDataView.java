package com.rxdroid.extensecalc.view;

import android.content.Context;

import com.rxdroid.extensecalc.enums.ErrorType;


/**
 * Created by robert on 25.02.16.
 */
public interface LoadDataView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param errorType
     */
    void showError(ErrorType errorType);

    /**
     * Get a {@link Context}.
     */
    Context context();

}
