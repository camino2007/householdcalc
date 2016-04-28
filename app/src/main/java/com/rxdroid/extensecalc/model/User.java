package com.rxdroid.extensecalc.model;

import com.rxdroid.data.realmmodels.RealmTransaction;
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

    private long mId;
    private String mUserName;
    private Currency mCurrency;
    private BackupType mBackupType;
    private List<Transaction> mIncomeList;
    private List<Transaction> mExpenseList;

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

    public List<Transaction> getIncomeList() {
        return mIncomeList;
    }

    public List<Transaction> getExpenseList() {
        return mExpenseList;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public static RealmUser convertToRealm(User user) {
        RealmUser realmUser = new RealmUser();
        realmUser.setId(user.getId());
        RealmList<RealmTransaction> realmExpenses = Transaction.convertTransactionsToRealmList(user.getExpenseList());
        RealmList<RealmTransaction> realmIncomes = Transaction.convertTransactionsToRealmList(user.getIncomeList());
        realmUser.setExpenseList(realmExpenses);
        realmUser.setIncomeList(realmIncomes);
        realmUser.setName(user.getUserName());
        realmUser.setBackupType(user.getBackupType().toString());
        realmUser.setCurrency(user.getCurrency().toString());
        return realmUser;
    }

    public static User convertFromRealm(RealmUser realmuser) {
        Currency currency = getCurrencyFromDbUser(realmuser.getCurrency());
        BackupType backupType = getBackupTypeFromRealm(realmuser.getBackupType());
        List<Transaction> expenseList = getExpenseListFromDbUser(realmuser.getExpenseList());
        List<Transaction> incomeList = getIncomeListFromDbUser(realmuser.getIncomeList());
        return new Builder()
                .userName(realmuser.getName())
                .backupType(backupType)
                .currency(currency)
                .incomeList(incomeList)
                .expenseList(expenseList)
                .build();
    }

    private static List<Transaction> getExpenseListFromDbUser(RealmList<RealmTransaction> realmTransactions) {
        List<Transaction> expenseList = new ArrayList<>();
        Transaction expense;
        for (RealmTransaction realmTransaction : realmTransactions) {
            expense = Transaction.convertFromRealm(realmTransaction);
            expenseList.add(expense);
        }
        return expenseList;
    }

    private static List<Transaction> getIncomeListFromDbUser(RealmList<RealmTransaction> realmIncomes) {
        List<Transaction> incomeList = new ArrayList<>();
        Transaction income;
        for (RealmTransaction realmTransaction : realmIncomes) {
            income = Transaction.convertFromRealm(realmTransaction);
            incomeList.add(income);
        }
        return incomeList;
    }

    private static BackupType getBackupTypeFromRealm(String backupTypeAsString) {
        BackupType backupType = null;
        for (BackupType type : BackupType.getBackupTypes()) {
            if (type.toString().equalsIgnoreCase(backupTypeAsString)) {
                backupType = type;
                break;
            }
        }
        return backupType;
    }

    private static Currency getCurrencyFromDbUser(String currencyString) {
        Currency currency = null;
        for (Currency c : Currency.getCurrencies()) {
            if (c.getCurrencyString().equals(currencyString)) {
                currency = c;
                break;
            }
        }
        return currency;
    }

    public void addExpense(Transaction expense) {
        mExpenseList.add(expense);
    }

    public void addIncome(Transaction income) {
        mIncomeList.add(income);
    }

    public static class Builder {
        private String userName;
        private Currency currency;
        private BackupType backupType;
        private List<Transaction> incomeList = new ArrayList<>();
        private List<Transaction> expenseList = new ArrayList<>();

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

        public Builder incomeList(List<Transaction> incomeList) {
            this.incomeList = incomeList;
            return this;
        }

        public Builder expenseList(List<Transaction> expenseList) {
            this.expenseList = expenseList;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
