package com.rxdroid.extensecalc.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxdroid.extensecalc.internal.di.HasComponent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rxdroid on 4/11/16.
 */
public abstract class RxBaseFragment extends Fragment {

    private CompositeSubscription mCompositeSubscription;
    private ViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = getViewModel();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.clear();
        if (mViewModel != null) {
            mViewModel.destroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mCompositeSubscription = new CompositeSubscription();
        if (mViewModel != null) {
            Log.d(getTagText(), "onResume - " + getTagText());
            mViewModel.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mViewModel != null) {
            mViewModel.pause();
        }
    }

    protected void addSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    protected void removeSubscription(Subscription subscription) {
        mCompositeSubscription.remove(subscription);
    }

    /**
     * Represents the ViewModel for each screen. Is used here for basic calls like in onResume()
     *
     * @return ViewModel
     */
    public abstract ViewModel getViewModel();

    /**
     * Returns a unique view identifier for logging, usually in this way: ViewXYZ.class.getSimpleName()
     *
     * @return String
     */
    public abstract String getTagText();

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
