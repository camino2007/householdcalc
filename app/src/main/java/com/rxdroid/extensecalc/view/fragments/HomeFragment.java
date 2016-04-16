package com.rxdroid.extensecalc.view.fragments;

import com.rxdroid.extensecalc.R;
import com.rxdroid.extensecalc.view.ViewPresenter;

/**
 * Created by rxdroid on 4/16/16.
 */
public class HomeFragment extends RxBaseFragment {

    public static HomeFragment initialize() {
        return new HomeFragment();
    }

    @Override
    public ViewPresenter getViewPresenter() {
        return null;
    }

    @Override
    public String getTagText() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


}
