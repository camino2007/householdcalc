package com.rxdroid.extensecalc.view.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.TransactionDataWrapper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rxdroid on 5/16/16.
 */
public class MonthTransactionHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_date) TextView mDateView;
    @Bind(R.id.tv_monthly_incomes) TextView mMonthlyIncomesView;
    @Bind(R.id.tv_monthly_expenses) TextView mMonthlyExpensesView;
    @Bind(R.id.tv_absolute_result) TextView mMonthlyResultView;
    @Bind(R.id.income_layout) FrameLayout mIncomeLayout;
    @Bind(R.id.expense_layout) FrameLayout mExpenseLayout;

    private OnMonthDataClickListener mClickListener;
    private TransactionDataWrapper mTransactionDataWrapper;

    public MonthTransactionHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mIncomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onIncomesClicked(mTransactionDataWrapper.getIncomeList());
            }
        });
        mExpenseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onExpensesClicked(mTransactionDataWrapper.getExpenseList());
            }
        });
    }

    public void bind(TransactionDataWrapper transactionDataWrapper) {
        mTransactionDataWrapper = transactionDataWrapper;
        mMonthlyExpensesView.setText(String.valueOf(transactionDataWrapper.getExpensesSum()));
        mMonthlyIncomesView.setText(String.valueOf(transactionDataWrapper.getIncomesSum()));
        mMonthlyResultView.setText(String.valueOf(transactionDataWrapper.getResultSum()));
    }

    public void setClickListener(OnMonthDataClickListener clickListener) {
        mClickListener = clickListener;
    }

    public interface OnMonthDataClickListener {
        void onExpensesClicked(List<Transaction> expenseList);

        void onIncomesClicked(List<Transaction> incomeList);
    }
}
