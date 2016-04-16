package com.rxdroid.domain.subscriber;

import rx.Subscriber;
import rx.exceptions.OnErrorNotImplementedException;

/**
 * Created by robert on 11.04.16.
 */
public abstract class ClickSubscriber extends Subscriber<Void> {

    private final String mTag;

    public ClickSubscriber(String tag) {
        mTag = tag;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        throw new OnErrorNotImplementedException(mTag, e);
    }

    @Override
    public void onNext(Void aVoid) {

    }
}
