package com.rxdroid.extensecalc.provider;

import com.rxdroid.extensecalc.model.Transaction;
import com.rxdroid.extensecalc.model.UserResult;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by rxdroid on 4/23/16.
 */
public final class TransactionProvider {

    private final UserProvider mUserProvider;

    @Inject
    public TransactionProvider(UserProvider userProvider) {
        mUserProvider = userProvider;
    }

    public UserResult calcUserDataForMonth(int month) {
        if(mUserProvider.getUser()!=null){
        List<Transaction> expenseList = mUserProvider.getUser().getExpenseList();
        List<Transaction> incomeList = mUserProvider.getUser().getIncomeList();
        float allExpenses = calcTransactionList(expenseList, month);
        float allIncomes = calcTransactionList(incomeList, month);
        float resultSum = allIncomes - allExpenses;
        return new UserResult.Builder()
                .allExpenses(allExpenses)
                .allIncomes(allIncomes)
                .resultSum(resultSum)
                .build();}return null;
    }

    private float calcTransactionList(List<Transaction> expenseList, int month) {
        float result = 0f;
        for (Transaction transaction : expenseList) {
            if (transaction.getDate().get(Calendar.MONTH) == month) {
                result = result + transaction.getAmount();
            }
        }
        return result;
    }

}
