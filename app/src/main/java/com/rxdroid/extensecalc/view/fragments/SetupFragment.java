package com.rxdroid.extensecalc.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.enums.BackupType;
import com.rxdroid.extensecalc.enums.Currency;
import com.rxdroid.extensecalc.enums.ErrorType;
import com.rxdroid.extensecalc.internal.di.components.ApiComponent;
import com.rxdroid.extensecalc.view.SetupView;
import com.rxdroid.extensecalc.view.ViewPresenter;
import com.rxdroid.extensecalc.view.presenter.SetupPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by rxdroid on 4/16/16.
 */
public class SetupFragment extends RxBaseFragment implements SetupView {

    @Bind(R.id.spinner_currency) Spinner mCurrencySpinner;
    @Bind(R.id.spinner_backup) Spinner mBackupSpinner;

    @Inject SetupPresenter mSetupPresenter;

    public static SetupFragment initialize() {
        return new SetupFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        Log.d(getTagText(), "onCreateView");
        mCurrencySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Currency.values()));
        mBackupSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, BackupType.values()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(getTagText(), "onActivityCreated");
        mSetupPresenter.setSetupView(this);
    }

    @Override
    public ViewPresenter getViewPresenter() {
        return mSetupPresenter;
    }

    @Override
    public String getTagText() {
        return SetupFragment.class.getSimpleName();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setup;
    }

    @Override
    protected void injectComponent(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @OnClick(R.id.btn_add_expense)
    public void addExpenseClicked() {

    }

    @OnClick(R.id.btn_add_income)
    public void addIncomeClicked() {

    }

    @OnClick(R.id.btn_start)
    public void startBtnClicked() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(ErrorType errorType) {

    }

    @Override
    public Context context() {
        return getContext();
    }
}
