package com.rxdroid.extensecalc.provider;

import android.util.Log;

import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.UserResult;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/23/16.
 */
public final class TransactionProvider {

    private static final String TAG = "TransactionProvider";

    private final UserProvider mUserProvider;

    @Inject
    public TransactionProvider(UserProvider userProvider) {
        mUserProvider = userProvider;
    }

    public UserResult calcUserDataForMonth(int month) {
        if (mUserProvider.getUser() != null) {
            List<Transaction> expenseList = mUserProvider.getUser().getExpenseList();
            for (Transaction transaction : expenseList) {
                Log.d(TAG, "expenseList - getAmount: " + transaction.getAmount());
            }
            List<Transaction> incomeList = mUserProvider.getUser().getIncomeList();
            for (Transaction transaction : incomeList) {
                Log.d(TAG, "incomeList - getAmount: " + transaction.getAmount());
            }
            float allExpenses = calcTransactionList(expenseList, month) * (-1);
            float allIncomes = calcTransactionList(incomeList, month);
            float resultSum = allIncomes + allExpenses;
            return new UserResult.Builder()
                    .allExpenses(allExpenses)
                    .allIncomes(allIncomes)
                    .resultSum(resultSum)
                    .build();
        }
        return null;
    }

    private float calcTransactionList(List<Transaction> transactionList, int month) {
        float result = 0f;
        for (Transaction transaction : transactionList) {
            if (transaction.getDate().get(Calendar.MONTH) == month) {
                result = result + transaction.getAmount();
            }
        }
        return result;
    }

}
