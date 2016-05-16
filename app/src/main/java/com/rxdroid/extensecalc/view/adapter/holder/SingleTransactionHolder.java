package com.rxdroid.extensecalc.view.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.model.Transaction;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rxdroid on 5/16/16.
 */
public class SingleTransactionHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tv_amount) TextView mAmountView;
    @Bind(R.id.tv_payment_rate) TextView mPaymentView;
    @Bind(R.id.tv_date) TextView mDateView;
    @Bind(R.id.tv_issue) TextView mIssueView;
    @Bind(R.id.tv_description) TextView mDescriptionView;
    @Bind(R.id.single_transaction_layout) FrameLayout mRootLayout;

    private OnSingleTransactionClickListener mSingleTransactionClickListener;
    private Transaction mTransaction;
    private Context mContext;

    public SingleTransactionHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
        mRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingleTransactionClickListener.onSingleTransactionClicked(mTransaction);
            }
        });
    }

    public void setSingleTransactionClickListener(OnSingleTransactionClickListener singleTransactionClickListener) {
        mSingleTransactionClickListener = singleTransactionClickListener;
    }

    public void bind(Transaction singleTransaction) {
        mTransaction = singleTransaction;
        mAmountView.setText(String.valueOf(singleTransaction.getAmount()));
        mPaymentView.setText(mContext.getString(singleTransaction.getPaymentRate().getStringResId()));
        mIssueView.setText(mContext.getString(singleTransaction.getIssueType().getStringResId()));
    }

    public interface OnSingleTransactionClickListener {
        void onSingleTransactionClicked(Transaction transaction);
    }
}
