package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class SystemClockMethodAutoProvider extends AbstractProvider<SystemClock> {
    @Override // javax.inject.Provider
    public SystemClock get() {
        return TimeModule.provideSystemClock();
    }
}
