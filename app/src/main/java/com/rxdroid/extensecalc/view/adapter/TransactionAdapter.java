package com.rxdroid.extensecalc.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.TransactionDataWrapper;
import com.rxdroid.extensecalc.view.adapter.holder.MonthTransactionHolder;
import com.rxdroid.extensecalc.view.adapter.holder.SingleTransactionHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxdroid on 5/16/16.
 */
public class TransactionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "TransactionAdapter";

    private static final int VIEW_TYPE_ABSOLUTE = 0;
    private static final int VIEW_TYPE_SINGLE = 1;

    private List<TransactionDataWrapper> mTransactionDataWrapperList = new ArrayList<>();
    private HolderClickListener mHolderClickListener;
    private SingleTransactionHolder.OnSingleTransactionClickListener mSingleTransactionClickListener;

    public TransactionAdapter(SingleTransactionHolder.OnSingleTransactionClickListener singleTransactionClickListener) {
        mHolderClickListener = new HolderClickListener();
        mSingleTransactionClickListener = singleTransactionClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;
        if (viewType == VIEW_TYPE_ABSOLUTE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_month_transaction, parent, false);
            viewHolder = new MonthTransactionHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_transaction, parent, false);
            viewHolder = new SingleTransactionHolder(parent.getContext(), view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TransactionDataWrapper transactionDataWrapper = mTransactionDataWrapperList.get(position);
        if (getItemViewType(position) == VIEW_TYPE_ABSOLUTE) {
            MonthTransactionHolder monthTransactionHolder = (MonthTransactionHolder) holder;
            monthTransactionHolder.bind(transactionDataWrapper);
            monthTransactionHolder.setClickListener(mHolderClickListener);
        } else {
            SingleTransactionHolder singleTransactionHolder = (SingleTransactionHolder) holder;
            singleTransactionHolder.bind(transactionDataWrapper.getSingleTransaction());
            singleTransactionHolder.setSingleTransactionClickListener(mSingleTransactionClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mTransactionDataWrapperList.size();
    }

    public void update(List<TransactionDataWrapper> transactionDataWrapperList) {
        mTransactionDataWrapperList = transactionDataWrapperList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mTransactionDataWrapperList.get(position).isSingleTransaction()) {
            return VIEW_TYPE_SINGLE;
        } else {
            return VIEW_TYPE_ABSOLUTE;
        }
    }

    private class HolderClickListener implements MonthTransactionHolder.OnMonthDataClickListener {

        @Override
        public void onExpensesClicked(List<Transaction> expenseList) {
            Log.d(TAG, "onExpensesClicked: " + expenseList.size());
        }

        @Override
        public void onIncomesClicked(List<Transaction> incomeList) {
            Log.d(TAG, "onIncomesClicked: " + incomeList.size());
        }
    }
}
