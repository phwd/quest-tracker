package com.oculus.logging.utils;

import com.facebook.inject.AbstractProvider;

public class EventManagerImplAutoProvider extends AbstractProvider<EventManagerImpl> {
    @Override // javax.inject.Provider
    public EventManagerImpl get() {
        return new EventManagerImpl(this);
    }
}
