package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class RealtimeSinceBootClockMethodAutoProvider extends AbstractProvider<RealtimeSinceBootClock> {
    @Override // javax.inject.Provider
    public RealtimeSinceBootClock get() {
        return TimeModule.provideRealtimeSinceBootClock();
    }
}
