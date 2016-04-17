package com.rxdroid.extensecalc.model;

import com.rxdroid.data.realmmodels.RealmExpense;
import com.rxdroid.data.realmmodels.RealmIncome;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.extensecalc.enums.BackupType;
import com.rxdroid.extensecalc.enums.Currency;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by rxdroid on 4/16/16.
 */
public class User {

    private String mUserName;
    private Currency mCurrency;
    private BackupType mBackupType;
    private List<Income> mIncomeList;
    private List<Expense> mExpenseList;

    public User(Builder builder) {
        mUserName = builder.userName;
        mCurrency = builder.currency;
        mBackupType = builder.backupType;
        mIncomeList = builder.incomeList;
        mExpenseList = builder.expenseList;
    }

    public String getUserName() {
        return mUserName;
    }

    public Currency getCurrency() {
        return mCurrency;
    }

    public BackupType getBackupType() {
        return mBackupType;
    }

    public List<Income> getIncomeList() {
        return mIncomeList;
    }

    public List<Expense> getExpenseList() {
        return mExpenseList;
    }

    public static RealmUser getRealmUser(User user) {
        RealmUser realmUser = new RealmUser();
        RealmList<RealmExpense> realmExpenses = Expense.getRealmList(user.getExpenseList());
        RealmList<RealmIncome> realmIncomes = Income.getRealmList(user.getIncomeList());
        realmUser.setExpenseList(realmExpenses);
        realmUser.setIncomeList(realmIncomes);
        realmUser.setName(user.getUserName());
        //TODO enums
        return realmUser;
    }

    public static User create(RealmUser realmuser) {
        Currency currency = getCurrencyFromDbUser(realmuser.getCurrency());
        BackupType backupType = getBackupTypeFromDbUser(realmuser);
        List<Expense> expenseList = getExpenseListFromDbUser(realmuser.getExpenseList());
        List<Income> incomeList = getIncomeListFromDbUser(realmuser.getIncomeList());
        return new Builder()
                .userName(realmuser.getName())
                .backupType(backupType)
                .currency(currency)
                .incomeList(incomeList)
                .expenseList(expenseList)
                .build();
    }

    private static List<Expense> getExpenseListFromDbUser(RealmList<RealmExpense> realmExpenses) {
        List<Expense> expenseList = new ArrayList<>();
        Expense expense;
        for (RealmExpense realmExpense : realmExpenses) {
            expense = Expense.create(realmExpense);
            expenseList.add(expense);
        }
        return expenseList;
    }

    private static List<Income> getIncomeListFromDbUser(RealmList<RealmIncome> realmIncomes) {
        List<Income> incomeList = new ArrayList<>();
        Income income;
        for (RealmIncome realmIncome : realmIncomes) {
            income = Income.create(realmIncome);
            incomeList.add(income);
        }
        return incomeList;
    }

    private static BackupType getBackupTypeFromDbUser(RealmUser realmUser) {
        if (realmUser.isEmail()) {
            return BackupType.EMAIL;
        }
        if (realmUser.isHasDropBox()) {
            return BackupType.DROP_BOX;
        }
        if (realmUser.isHasGoogleDrive()) {
            return BackupType.GOOGLE_DRIVE;
        }
        return BackupType.NOT_NOW;
    }

    private static Currency getCurrencyFromDbUser(String currencyString) {
        Currency currency = null;
        for (Currency c : Currency.getCurrencies()) {
            if (c.getCurrency().equals(currencyString)) {
                currency = c;
                break;
            }
        }
        return currency;
    }

    public static class Builder {
        private String userName;
        private Currency currency;
        private BackupType backupType;
        private List<Income> incomeList = new ArrayList<>();
        private List<Expense> expenseList = new ArrayList<>();

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder backupType(BackupType backupType) {
            this.backupType = backupType;
            return this;
        }

        public Builder incomeList(List<Income> incomeList) {
            this.incomeList = incomeList;
            return this;
        }

        public Builder expenseList(List<Expense> expenseList) {
            this.expenseList = expenseList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
