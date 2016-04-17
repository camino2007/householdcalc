package com.rxdroid.extensecalc.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.rxdroid.data.enums.MoneyType;
import com.rxdroid.data.enums.PaymentRate;
import com.rxdroid.domain.subscriber.DefaultSubscriber;
import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.enums.ValidType;
import com.rxdroid.extensecalc.model.Expense;
import com.rxdroid.extensecalc.model.MoneyBuilder;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

import static android.text.TextUtils.isEmpty;

/**
 * Created by rxdroid on 4/16/16.
 */
public class AddExpenseFragment extends DialogFragment {

    private static final String TAG = "AddExpenseFragment";

    @Bind(R.id.spinner_money_type) Spinner mMoneySpinner;
    @Bind(R.id.spinner_payment_rate) Spinner mPaymentSpinner;
    @Bind(R.id.expense_amount_edt) EditText mAmountTxtField;
    @Bind(R.id.btn_submit) Button mSubmitBtn;
    @Bind(R.id.expense_amount_input_layout) TextInputLayout mTextInputLayout;

    private CompositeSubscription mCompositeSubscription;
    private OnAddExpenseCallback mExpenseCallback;

    private ValidType mAmountValid = ValidType.IN_VALID;

    public static AddExpenseFragment initialize() {
        return new AddExpenseFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
    }

    public void setExpenseCallback(OnAddExpenseCallback expenseCallback) {
        mExpenseCallback = expenseCallback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        ButterKnife.bind(this, view);
        mMoneySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, MoneyType.values()));
        mPaymentSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, PaymentRate.values()));
        validateAmount();
        return view;
    }

    private void validateAmount() {
        Observable<CharSequence> phoneObservable = RxTextView.textChanges(mAmountTxtField);
        Subscription amountSub = phoneObservable
                .skip(1)
                .map(new Func1<CharSequence, ValidType>() {
                    @Override
                    public ValidType call(CharSequence pwChars) {
                        if(!isEmpty(pwChars)){
                            float amount = Float.valueOf(pwChars.toString());
                            if (amount > 0f) {
                                return ValidType.VALID;
                            }
                        }
                        return ValidType.IN_VALID;
                    }
                })
                .subscribe(new AmountSubscriber());
        mCompositeSubscription.add(amountSub);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        mExpenseCallback = null;
    }

    @OnClick(R.id.btn_submit)
    public void onSubmitClicked() {
        int currencyIndex = mMoneySpinner.getSelectedItemPosition();
        MoneyType moneyType = MoneyType.getMoneyTypes().get(currencyIndex);
        int paymentIndex = mPaymentSpinner.getSelectedItemPosition();
        PaymentRate paymentRate = PaymentRate.getPaymentRates().get(paymentIndex);
        Expense.Builder expenseBuilder = new Expense.Builder();
        Expense expense = (Expense) expenseBuilder
                .setAmount(Float.valueOf(mAmountTxtField.getText().toString()))
                .setMoneyType(moneyType)
                .setPaymentRate(paymentRate)
                .setDate(Calendar.getInstance())
                .build();
        mExpenseCallback.onExpenseCreated(expense);
        dismiss();
    }

    private void enableSubmitButton() {
        if (mAmountValid == ValidType.VALID) {
            mSubmitBtn.setEnabled(true);
        } else {
            mSubmitBtn.setEnabled(false);
        }
    }

    private void showAmountError() {
        mTextInputLayout.setErrorEnabled(true);
        String errText = getString(R.string.expense_amount_error);
        mTextInputLayout.setError(errText);
    }

    private void hideAmountError() {
        mTextInputLayout.setErrorEnabled(false);
    }

    private class AmountSubscriber extends DefaultSubscriber<ValidType> {
        @Override
        public void onNext(ValidType validType) {
            mAmountValid = validType;
            enableSubmitButton();
            if (validType == ValidType.IN_VALID) {
                showAmountError();
            } else {
                hideAmountError();
            }
        }
    }

    public interface OnAddExpenseCallback {
        void onExpenseCreated(Expense expense);
    }
}
