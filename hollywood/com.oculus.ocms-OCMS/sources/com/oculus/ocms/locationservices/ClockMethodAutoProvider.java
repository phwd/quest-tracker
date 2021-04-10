package com.oculus.ocms.locationservices;

import com.facebook.annotations.Generated;
import com.facebook.common.time.Clock;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ClockMethodAutoProvider extends AbstractProvider<Clock> {
    @Override // javax.inject.Provider
    public Clock get() {
        return LocationServiceModule.provideClock();
    }
}
