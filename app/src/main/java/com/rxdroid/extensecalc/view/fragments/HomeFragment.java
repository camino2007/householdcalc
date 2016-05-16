package com.rxdroid.extensecalc.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.enums.ErrorType;
import com.rxdroid.extensecalc.enums.TransactionType;
import com.rxdroid.extensecalc.internal.di.components.ApiComponent;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.TransactionDataWrapper;
import com.rxdroid.extensecalc.view.HomeView;
import com.rxdroid.extensecalc.view.ViewPresenter;
import com.rxdroid.extensecalc.view.adapter.TransactionAdapter;
import com.rxdroid.extensecalc.view.adapter.holder.SingleTransactionHolder;
import com.rxdroid.extensecalc.view.presenter.HomePresenter;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by rxdroid on 4/16/16.
 */
public class HomeFragment extends RxBaseFragment implements HomeView, TransactionFragment.OnTransactionCallback {

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.ad_view_container) FrameLayout mAdContainer;
    @Bind(R.id.progress_bar) ProgressBar mProgressBar;

    @Inject HomePresenter mHomePresenter;

    private TransactionAdapter mTransactionAdapter;

    public static HomeFragment initialize() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTransactionAdapter = new TransactionAdapter(new TransactionDetailCallback());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHomePresenter.setHomeView(this);
        mHomePresenter.initCallbacks();
        mHomePresenter.loadUser(getContext().getApplicationContext());
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mTransactionAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void updateUserData() {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        mHomePresenter.loadTransactionsData(currentMonth, currentYear);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUserData();
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
        if (transaction.getTransactionType() == TransactionType.INCOME) {
            mHomePresenter.addIncome(transaction);
        } else {
            mHomePresenter.addExpense(transaction);
        }
    }

    @Override
    public void onUserDataDone(List<TransactionDataWrapper> transactionDataWrapperList) {
        mTransactionAdapter.update(transactionDataWrapperList);
    }

    private class TransactionDetailCallback implements SingleTransactionHolder.OnSingleTransactionClickListener {

        @Override
        public void onSingleTransactionClicked(Transaction transaction) {
            Log.d(getTagText(), "onSingleTransactionClicked: " + transaction.getAmount());
        }
    }
}
