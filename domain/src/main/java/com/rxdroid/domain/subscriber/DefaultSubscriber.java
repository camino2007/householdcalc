package com.rxdroid.domain.subscriber;

import rx.Subscriber;

/**
 * Created by robert on 24.02.16.
 */
public abstract class DefaultSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
