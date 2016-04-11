package com.rxdroid.extensecalc.internal.di;

/**
 * Created by robert on 21.03.16.
 * <p/>
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
public interface HasComponent<C> {
    C getComponent();
}
