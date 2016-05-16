package com.rxdroid.extensecalc.model;

import java.util.Calendar;
import java.util.List;

/**
 * Created by rxdroid on 4/23/16.
 */
public class TransactionDataWrapper {

    private float mExpensesSum;
    private float mIncomesSum;
    private float mResultSum;
    private boolean mIsSingleTransaction;
    private Calendar mMonthDate;
    private List<Transaction> mExpenseList;
    private List<Transaction> mIncomeList;
    private Transaction mSingleTransaction;

    private TransactionDataWrapper(AbsoluteBuilder absoluteBuilder) {
        mExpensesSum = absoluteBuilder.allExpenses;
        mIncomesSum = absoluteBuilder.allIncomes;
        mResultSum = absoluteBuilder.resultSum;
        mMonthDate = absoluteBuilder.monthDate;
        mExpenseList = absoluteBuilder.expenseList;
        mIncomeList = absoluteBuilder.incomeList;
        mIsSingleTransaction = false;
    }

    public TransactionDataWrapper(SingleBuilder singleBuilder) {
        mSingleTransaction = singleBuilder.transaction;
        mIsSingleTransaction = true;
    }

    public boolean isSingleTransaction() {
        return mIsSingleTransaction;
    }

    public Transaction getSingleTransaction() {
        return mSingleTransaction;
    }

    public float getExpensesSum() {
        return mExpensesSum;
    }

    public float getIncomesSum() {
        return mIncomesSum;
    }

    public float getResultSum() {
        return mResultSum;
    }

    public Calendar getMonthDate() {
        return mMonthDate;
    }

    public List<Transaction> getExpenseList() {
        return mExpenseList;
    }

    public List<Transaction> getIncomeList() {
        return mIncomeList;
    }

    /**
     * creates a list item for a single transaction
     */
    public static class SingleBuilder {
        private Transaction transaction;

        public SingleBuilder transaction(Transaction transaction) {
            this.transaction = transaction;
            return this;
        }

        public TransactionDataWrapper build(){
            return new TransactionDataWrapper(this);
        }

    }

    /**
     * creates a list item for one month
     */
    public static class AbsoluteBuilder {
        private float allExpenses;
        private float allIncomes;
        private float resultSum;
        private Calendar monthDate;
        private List<Transaction> expenseList;
        private List<Transaction> incomeList;

        public AbsoluteBuilder monthDate(Calendar monthDate) {
            this.monthDate = monthDate;
            return this;
        }

        public AbsoluteBuilder expenseList(List<Transaction> expenseList) {
            this.expenseList = expenseList;
            return this;
        }

        public AbsoluteBuilder incomeList(List<Transaction> incomeList) {
            this.incomeList = incomeList;
            return this;
        }

        public AbsoluteBuilder expensesSum(float allExpenses) {
            this.allExpenses = allExpenses;
            return this;
        }

        public AbsoluteBuilder incomesSum(float allIncomes) {
            this.allIncomes = allIncomes;
            return this;
        }

        public AbsoluteBuilder resultSum(float resultSum) {
            this.resultSum = resultSum;
            return this;
        }

        public TransactionDataWrapper build() {
            return new TransactionDataWrapper(this);
        }
    }

}
