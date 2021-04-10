package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MonotonicNanoClockMethodAutoProvider extends AbstractProvider<MonotonicNanoClock> {
    @Override // javax.inject.Provider
    public MonotonicNanoClock get() {
        return TimeModule.provideNanoClock();
    }
}
