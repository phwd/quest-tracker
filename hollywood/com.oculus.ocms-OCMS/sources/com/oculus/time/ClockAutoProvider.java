package com.oculus.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ClockAutoProvider extends AbstractProvider<Clock> {
    @Override // javax.inject.Provider
    public Clock get() {
        return new Clock();
    }
}
