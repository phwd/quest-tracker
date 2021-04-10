package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class AwakeTimeSinceBootClockMethodAutoProvider extends AbstractProvider<AwakeTimeSinceBootClock> {
    @Override // javax.inject.Provider
    public AwakeTimeSinceBootClock get() {
        return TimeModule.provideAwakeTimeSinceBootClock();
    }
}
