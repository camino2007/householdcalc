package com.rxdroid.extensecalc.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.enums.ErrorType;
import com.rxdroid.extensecalc.enums.TransactionType;
import com.rxdroid.extensecalc.internal.di.components.ApiComponent;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.UserResult;
import com.rxdroid.extensecalc.view.HomeView;
import com.rxdroid.extensecalc.view.ViewPresenter;
import com.rxdroid.extensecalc.view.presenter.HomePresenter;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by rxdroid on 4/16/16.
 */
public class HomeFragment extends RxBaseFragment implements HomeView, TransactionFragment.OnTransactionCallback {

    @Bind(R.id.tv_date) TextView mDateTv;
    @Bind(R.id.tv_monthly_incomes) TextView mIncomesTv;
    @Bind(R.id.tv_monthly_expenses) TextView mExpensesTv;
    @Bind(R.id.home_tv_absolute_result) TextView mAbsoluteResultTv;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;

    @Inject HomePresenter mHomePresenter;

    public static HomeFragment initialize() {
        return new HomeFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomePresenter.setHomeView(this);
        updateUserData();
        mDateTv.setText(new Date().toString());
    }

    private void updateUserData() {
        mHomePresenter.loadTransactionsData(Calendar.getInstance().get(Calendar.MONTH));
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
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(ErrorType errorType) {
        Toast toast = Toast.makeText(getContext(), errorType.getMessageId(), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public Context context() {
        return getContext();
    }

    @OnClick(R.id.fab_add_expense)
    public void onFabExpenseClicked() {
        showTransactionDialog(TransactionType.EXPENSE);
    }

    @OnClick(R.id.fab_add_income)
    public void onFabIncomeClicked() {
        showTransactionDialog(TransactionType.INCOME);
    }

    private void showTransactionDialog(TransactionType transactionType) {
        TransactionFragment expenseFragment = TransactionFragment.initialize(transactionType);
        expenseFragment.setExpenseCallback(this);
        expenseFragment.show(getChildFragmentManager(), TransactionFragment.class.getSimpleName());
    }

    @Override
    public void onTransactionCreated(Transaction transaction) {
        mHomePresenter.addTransaction(transaction);
    }

    @Override
    public void onUserDataDone(UserResult userResult) {
        mAbsoluteResultTv.setText(String.valueOf(userResult.getResultSum()));
        mExpensesTv.setText(String.valueOf(userResult.getAllExpenses()));
        mIncomesTv.setText(String.valueOf(userResult.getAllIncomes()));
    }
}
