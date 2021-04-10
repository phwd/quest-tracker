package com.oculus.appmanager.installer.service;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AnalyticsUpdateStateListenerAutoProvider extends AbstractProvider<AnalyticsUpdateStateListener> {
    @Override // javax.inject.Provider
    public AnalyticsUpdateStateListener get() {
        return new AnalyticsUpdateStateListener(this);
    }
}
