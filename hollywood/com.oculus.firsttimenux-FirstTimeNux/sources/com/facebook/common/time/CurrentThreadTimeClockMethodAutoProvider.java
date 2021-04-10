package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class CurrentThreadTimeClockMethodAutoProvider extends AbstractProvider<CurrentThreadTimeClock> {
    @Override // javax.inject.Provider
    public CurrentThreadTimeClock get() {
        return TimeModule.provideCurrentThreadTimeClock();
    }
}
