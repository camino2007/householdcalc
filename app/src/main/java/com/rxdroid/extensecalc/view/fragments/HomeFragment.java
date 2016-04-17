package com.rxdroid.extensecalc.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.enums.ErrorType;
import com.rxdroid.extensecalc.internal.di.components.ApiComponent;
import com.rxdroid.extensecalc.model.Expense;
import com.rxdroid.extensecalc.view.HomeView;
import com.rxdroid.extensecalc.view.ViewPresenter;
import com.rxdroid.extensecalc.view.presenter.HomePresenter;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by rxdroid on 4/16/16.
 */
public class HomeFragment extends RxBaseFragment implements HomeView, AddExpenseFragment.OnAddExpenseCallback {

    @Inject HomePresenter mHomePresenter;

    public static HomeFragment initialize() {
        return new HomeFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomePresenter.setHomeView(this);
    }

    @Override
    public ViewPresenter getViewPresenter() {
        return mHomePresenter;
    }

    @Override
    public String getTagText() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void injectComponent(ApiComponent apiComponent) {
        apiComponent.inject(this);
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

    @OnClick(R.id.fab)
    public void onFloatingActionBtnClicked() {
        showAddExpenseDialog();
    }

    private void showAddExpenseDialog() {
        AddExpenseFragment expenseFragment = AddExpenseFragment.initialize();
        expenseFragment.setExpenseCallback(this);
        expenseFragment.show(getChildFragmentManager(), AddExpenseFragment.class.getSimpleName());
    }

    @Override
    public void onExpenseCreated(Expense expense) {
        mHomePresenter.addExpense(expense);
    }
}
