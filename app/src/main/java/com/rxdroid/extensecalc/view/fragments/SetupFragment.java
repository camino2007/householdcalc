package com.rxdroid.extensecalc.view.fragments;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.view.ViewPresenter;

/**
 * Created by rxdroid on 4/16/16.
 */
public class SetupFragment extends RxBaseFragment {


    public static SetupFragment initialize() {
        return new SetupFragment();
    }

    @Override
    public ViewPresenter getViewPresenter() {
        return null;
    }

    @Override
    public String getTagText() {
        return SetupFragment.class.getSimpleName();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setup;
    }


}
