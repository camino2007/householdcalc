package com.rxdroid.extensecalc.provider;

import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.TransactionDataWrapper;
import com.rxdroid.extensecalc.model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/23/16.
 */
public final class TransactionProvider {

    private static final String TAG = "TransactionProvider";

    @Inject
    public TransactionProvider() {
    }

    public TransactionDataWrapper calcUserDataForMonth(int currentMonth, int currentYear, User user) {
        List<TransactionDataWrapper> result = new ArrayList<>();
        TransactionDataWrapper dataWrapper;
        TransactionDataWrapper.SingleBuilder singleBuilder = new TransactionDataWrapper.SingleBuilder();
        TransactionDataWrapper.AbsoluteBuilder absoluteBuilder = new TransactionDataWrapper.AbsoluteBuilder();


        //
        List<Transaction> periodExpenses = new ArrayList<>();
        List<Transaction> periodIncomes = new ArrayList<>();


        int queryMonth = currentMonth;
        int queryYear = currentYear;

        if (user != null) {
            for (Transaction expense : user.getExpenseList()) {
                if (expense.getDate().get(Calendar.MONTH) == queryMonth
                        && expense.getDate().get(Calendar.YEAR) == queryYear) {
                    periodExpenses.add(expense);
                } else {
                    //new month or new year
                    queryMonth = expense.getDate().get(Calendar.MONTH);
                    queryYear = expense.getDate().get(Calendar.YEAR);
                    absoluteBuilder.expenseList(new ArrayList<>(periodExpenses));
                    periodExpenses.clear();
                    periodExpenses.add(expense);
                }
            }

        }




       /* if (mUserProvider.getUser() != null) {
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
            return new TransactionDataWrapper.AbsoluteBuilder()
                    .expensesSum(allExpenses)
                    .incomesSum(allIncomes)
                    .resultSum(resultSum)
                    .build();
        }*/
        return null;
    }

    private float calcTransactionList(List<Transaction> transactionList) {
        float result = 0f;
        for (Transaction transaction : transactionList) {
            result = result + transaction.getAmount();
        }
        return result;
    }

}
