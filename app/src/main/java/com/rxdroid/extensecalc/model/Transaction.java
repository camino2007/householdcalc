package com.rxdroid.extensecalc.model;

import com.rxdroid.data.enums.IssueType;
import com.rxdroid.data.enums.PaymentRate;
import com.rxdroid.data.realmmodels.RealmTransaction;
import com.rxdroid.extensecalc.enums.TransactionType;

import java.util.Calendar;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by rxdroid on 4/17/16.
 * <p/>
 * Incomes and Expenses are modeled by this class
 */
public class Transaction {

    private long mUserId;
    private float mAmount;
    private PaymentRate mPaymentRate;
    private IssueType mIssueType;
    private String mDescription;
    private boolean mIsScheduled;
    private TransactionType mTransactionType;
    private Calendar mDate;
    private Calendar mScheduledDate;

    public Transaction(Builder builder) {
        mAmount = builder.amount;
        mUserId = builder.userId;
        mPaymentRate = builder.paymentRate;
        mIsScheduled = builder.isScheduled;
        mIssueType = builder.issueType;
        mDate = builder.date;
        mDescription = builder.description;
        mScheduledDate = builder.scheduledDate;
        mTransactionType = builder.transactionType;
    }

    public long getUserId() {
        return mUserId;
    }

    public float getAmount() {
        return mAmount;
    }

    public PaymentRate getPaymentRate() {
        return mPaymentRate;
    }

    public IssueType getIssueType() {
        return mIssueType;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isScheduled() {
        return mIsScheduled;
    }

    public Calendar getDate() {
        return mDate;
    }

    public Calendar getScheduledDate() {
        return mScheduledDate;
    }

    public TransactionType getTransactionType() {
        return mTransactionType;
    }

    public static RealmList<RealmTransaction> convertTransactionsToRealmList(List<Transaction> expenseList) {
        RealmList<RealmTransaction> realmTransactions = new RealmList<>();
        RealmTransaction realmTransaction;
        for (Transaction transaction : expenseList) {
            realmTransaction = convertToRealm(transaction);
            realmTransactions.add(realmTransaction);
        }
        return realmTransactions;
    }

    public static RealmTransaction convertToRealm(Transaction transaction) {
        RealmTransaction realmTransaction = new RealmTransaction();
        realmTransaction.setUserId(transaction.getUserId());
        realmTransaction.setAmount(transaction.getAmount());
        realmTransaction.setPaymentRate(transaction.getPaymentRate().toString());
        realmTransaction.setDescription(transaction.getDescription());
        realmTransaction.setIssue(transaction.getIssueType().toString());
        realmTransaction.setTransactionType(transaction.getTransactionType().toString());
        realmTransaction.setScheduled(transaction.isScheduled());
        int year = transaction.getDate().get(Calendar.YEAR);
        int month = transaction.getDate().get(Calendar.MONTH);
        int day = transaction.getDate().get(Calendar.DAY_OF_MONTH);
        realmTransaction.setYear(year);
        realmTransaction.setMonth(month);
        realmTransaction.setDay(day);
        if (transaction.isScheduled()) {
            int yearScheduled = transaction.getScheduledDate().get(Calendar.YEAR);
            int monthScheduled = transaction.getScheduledDate().get(Calendar.MONTH);
            int dayScheduled = transaction.getScheduledDate().get(Calendar.DAY_OF_MONTH);
            realmTransaction.setYearScheduled(yearScheduled);
            realmTransaction.setMonthScheduled(monthScheduled);
            realmTransaction.setDayScheduled(dayScheduled);
        }
        return realmTransaction;
    }

    public static Transaction convertFromRealm(RealmTransaction rt) {
        PaymentRate paymentRate = getPaymentRateFromRealm(rt.getPaymentRate());
        IssueType issueType = getIssueTypeFromRealm(rt.getIssue());
        TransactionType transactionType = getTransactionTypeFromRealm(rt.getTransactionType());
        Calendar date = getDateFromRealm(rt.getYear(), rt.getMonth(), rt.getDay());
        Calendar scheduledDate = getDateFromRealm(rt.getYearScheduled(), rt.getMonthScheduled(), rt.getDayScheduled());
        return new Builder()
                .amount(rt.getAmount())
                .userId(rt.getUserId())
                .isScheduled(rt.isScheduled())
                .description(rt.getDescription())
                .paymentRate(paymentRate)
                .issueType(issueType)
                .transactionType(transactionType)
                .transactionDate(date)
                .scheduledDate(scheduledDate)
                .build();
    }


    private static Calendar getDateFromRealm(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar;
    }

    private static IssueType getIssueTypeFromRealm(String issueAsString) {
        IssueType issueType = null;
        for (IssueType type : IssueType.getTypes()) {
            if (type.toString().equalsIgnoreCase(issueAsString)) {
                issueType = type;
                break;
            }
        }
        return issueType;
    }

    private static TransactionType getTransactionTypeFromRealm(String transactionTypeAsString) {
        TransactionType transactionType = null;
        for (TransactionType type : TransactionType.getTypes()) {
            if (type.toString().equals(transactionTypeAsString)) {
                transactionType = type;
                break;
            }
        }
        return transactionType;
    }

    private static PaymentRate getPaymentRateFromRealm(String paymentRateAsString) {
        PaymentRate paymentRate = null;
        for (PaymentRate rate : PaymentRate.getPaymentRates()) {
            if (rate.toString().equalsIgnoreCase(paymentRateAsString)) {
                paymentRate = rate;
                break;
            }
        }
        return paymentRate;
    }

    public static class Builder {
        private float amount;
        private long userId;
        private PaymentRate paymentRate;
        private IssueType issueType;
        private TransactionType transactionType;
        private String description;
        private boolean isScheduled = false;
        private Calendar date;
        private Calendar scheduledDate;


        public Builder transactionDate(Calendar date) {
            this.date = date;
            return this;
        }

        public Builder amount(float amount) {
            this.amount = amount;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder paymentRate(PaymentRate paymentRate) {
            this.paymentRate = paymentRate;
            return this;
        }

        public Builder issueType(IssueType issueType) {
            this.issueType = issueType;
            return this;
        }

        public Builder transactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isScheduled(boolean scheduled) {
            isScheduled = scheduled;
            return this;
        }

        public Builder scheduledDate(Calendar scheduledDate) {
            this.scheduledDate = scheduledDate;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }

}
