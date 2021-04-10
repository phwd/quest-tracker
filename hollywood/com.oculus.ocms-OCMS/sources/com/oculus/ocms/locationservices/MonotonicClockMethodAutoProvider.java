package com.oculus.ocms.locationservices;

import com.facebook.annotations.Generated;
import com.facebook.common.time.MonotonicClock;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MonotonicClockMethodAutoProvider extends AbstractProvider<MonotonicClock> {
    @Override // javax.inject.Provider
    public MonotonicClock get() {
        return LocationServiceModule.provideMonotonicClock();
    }
}
