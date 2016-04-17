package com.rxdroid.extensecalc.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.rxdroid.data.realmmodels.RealmUser;
import com.rxdroid.domain.Constants;
import com.rxdroid.domain.subscriber.DefaultSubscriber;
import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.enums.BackupType;
import com.rxdroid.extensecalc.enums.Currency;
import com.rxdroid.extensecalc.enums.ErrorType;
import com.rxdroid.extensecalc.enums.ValidType;
import com.rxdroid.extensecalc.internal.di.components.ApiComponent;
import com.rxdroid.extensecalc.model.User;
import com.rxdroid.extensecalc.view.SetupView;
import com.rxdroid.extensecalc.view.ViewPresenter;
import com.rxdroid.extensecalc.view.activities.MainActivity;
import com.rxdroid.extensecalc.view.presenter.SetupPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.functions.Func1;

import static android.text.TextUtils.isEmpty;

/**
 * Created by rxdroid on 4/16/16.
 */
public class SetupFragment extends RxBaseFragment implements SetupView {

    @Bind(R.id.spinner_currency) Spinner mCurrencySpinner;
    @Bind(R.id.spinner_backup) Spinner mBackupSpinner;
    @Bind(R.id.fragment_setup_username) EditText mUserNameTxtField;
    @Bind(R.id.btn_start) Button mContinueBtn;
    @Bind(R.id.setup_input_layout) TextInputLayout mTextInputLayout;

    @Inject SetupPresenter mSetupPresenter;

    private ValidType mUserNameValid = ValidType.IN_VALID;

    public static SetupFragment initialize() {
        return new SetupFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mCurrencySpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, Currency.values()));
        mBackupSpinner.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, BackupType.values()));
        validateUserName();
        return view;
    }

    private void validateUserName() {
        Observable<CharSequence> phoneObservable = RxTextView.textChanges(mUserNameTxtField);
        Subscription passwordSub = phoneObservable
                .skip(1)
                .map(new Func1<CharSequence, ValidType>() {
                    @Override
                    public ValidType call(CharSequence pwChars) {
                        boolean isPwValid = !isEmpty(pwChars) && pwChars.length() >= Constants.USER_NAME_MIN_LENGTH
                                && pwChars.length() <= Constants.USER_NAME_MAX_LENGTH;
                        if (isPwValid) {
                            return ValidType.VALID;
                        }
                        return ValidType.IN_VALID;
                    }
                })
                .subscribe(new UserNameSubscriber());
        addSubscription(passwordSub);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSetupPresenter.setSetupView(this);
    }

    @Override
    public ViewPresenter getViewPresenter() {
        return mSetupPresenter;
    }

    @Override
    public String getTagText() {
        return SetupFragment.class.getSimpleName();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setup;
    }

    @Override
    protected void injectComponent(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @OnClick(R.id.btn_add_expense)
    public void addExpenseClicked() {

    }

    @OnClick(R.id.btn_add_income)
    public void addIncomeClicked() {

    }

    @OnClick(R.id.btn_start)
    public void startBtnClicked() {
        String userName = mUserNameTxtField.getText().toString();
        int selectedCurrencyPos = mCurrencySpinner.getSelectedItemPosition();
        Currency currency = Currency.getCurrencies().get(selectedCurrencyPos);
        int selectedBackupPos = mBackupSpinner.getSelectedItemPosition();
        BackupType backupType = BackupType.getBackupTypes().get(selectedBackupPos);
        Log.d(getTagText(), "startBtnClicked - currency: " + currency);
        Log.d(getTagText(), "startBtnClicked - backupType: " + backupType);
        User user = new User.Builder()
                .userName(userName)
                .currency(currency)
                .backupType(backupType)
                .build();
        RealmUser realmUser = User.getRealmUser(user);
        mSetupPresenter.persistUser(realmUser);
        Intent mainIntent = MainActivity.getCallingIntent(getContext());
        startActivity(mainIntent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(ErrorType errorType) {

    }

    @Override
    public Context context() {
        return getContext();
    }

    private void showUserNameError() {
        mTextInputLayout.setErrorEnabled(true);
        String errText = String.format(getString(R.string.setup_user_name_error), Constants.USER_NAME_MIN_LENGTH, Constants.USER_NAME_MAX_LENGTH);
        mTextInputLayout.setError(errText);
    }

    private void hideUserNameError() {
        mTextInputLayout.setErrorEnabled(false);
    }

    private void enableContinueButton() {
        if (mUserNameValid == ValidType.VALID) {
            mContinueBtn.setEnabled(true);
        } else {
            mContinueBtn.setEnabled(false);
        }
    }

    private class UserNameSubscriber extends DefaultSubscriber<ValidType> {
        @Override
        public void onNext(ValidType validType) {
            mUserNameValid = validType;
            enableContinueButton();
            if (validType == ValidType.IN_VALID) {
                showUserNameError();
            } else {
                hideUserNameError();
            }
        }
    }


}
