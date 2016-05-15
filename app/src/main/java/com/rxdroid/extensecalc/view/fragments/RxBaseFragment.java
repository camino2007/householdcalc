package com.rxdroid.extensecalc.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rxdroid.extensecalc.internal.di.HasComponent;
import com.rxdroid.extensecalc.internal.di.components.ApiComponent;
import com.rxdroid.extensecalc.view.ViewPresenter;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rxdroid on 4/11/16.
 */
public abstract class RxBaseFragment extends Fragment {

    private CompositeSubscription mCompositeSubscription;
    private ViewPresenter mViewPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * Take care that all calls to {@link ViewPresenter} and injected objects are made after onActivityCreated()!
     * Otherwise the objects are null.
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectComponent(getComponent(ApiComponent.class));
        mViewPresenter = getViewPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mViewPresenter != null) {
            Log.d(getTagText(), "onResume - " + getTagText());
            mViewPresenter.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mViewPresenter != null) {
            mViewPresenter.pause();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(getTagText(), "onDestroy");
        if (mViewPresenter != null) {
            mViewPresenter.destroy();
        }
        mCompositeSubscription.clear();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected void addSubscription(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    protected void removeSubscription(Subscription subscription) {
        mCompositeSubscription.remove(subscription);
    }

    /**
     * Represents the ViewPresenter for each screen. Is used here for basic calls like in onResume()
     *
     * @return ViewPresenter
     */
    public abstract ViewPresenter getViewPresenter();

    /**
     * Returns a unique view identifier for logging, usually in this way: ViewXYZ.class.getSimpleName()
     *
     * @return String
     */
    public abstract String getTagText();

    /**
     * Returns a unique layout resource for a fragment instance
     *
     * @return int
     */
    public abstract int getLayoutId();

    protected abstract void injectComponent(ApiComponent apiComponent);

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
