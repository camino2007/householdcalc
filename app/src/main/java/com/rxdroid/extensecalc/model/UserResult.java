package com.rxdroid.extensecalc.model;

/**
 * Created by rxdroid on 4/23/16.
 */
public class UserResult {

    private float mAllExpenses;
    private float mAllIncomes;
    private float mResultSum;

    private UserResult(Builder builder) {
        mAllExpenses = builder.allExpenses;
        mAllIncomes = builder.allIncomes;
        mResultSum = builder.resultSum;
    }

    public float getAllExpenses() {
        return mAllExpenses;
    }

    public float getAllIncomes() {
        return mAllIncomes;
    }

    public float getResultSum() {
        return mResultSum;
    }

    public static class Builder {
        private float allExpenses;
        private float allIncomes;
        private float resultSum;

        public Builder allExpenses(float allExpenses) {
            this.allExpenses = allExpenses;
            return this;
        }

        public Builder allIncomes(float allIncomes) {
            this.allIncomes = allIncomes;
            return this;
        }

        public Builder resultSum(float resultSum) {
            this.resultSum = resultSum;
            return this;
        }

        public UserResult build() {
            return new UserResult(this);
        }
    }

}
