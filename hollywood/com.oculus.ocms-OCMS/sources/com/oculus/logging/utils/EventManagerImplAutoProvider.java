package com.oculus.logging.utils;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class EventManagerImplAutoProvider extends AbstractProvider<EventManagerImpl> {
    @Override // javax.inject.Provider
    public EventManagerImpl get() {
        return new EventManagerImpl(this);
    }
}
