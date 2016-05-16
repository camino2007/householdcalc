package com.rxdroid.extensecalc.view;

import com.rxdroid.extensecalc.model.TransactionDataWrapper;

import java.util.List;

/**
 * Created by rxdroid on 4/16/16.
 */
public interface HomeView extends LoadDataView {

    void onUserDataDone(List<TransactionDataWrapper> transactionDataWrapperList);
}
