package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MonotonicClock_com_facebook_common_time_ElapsedRealtimeSinceBootMethodAutoProvider extends AbstractProvider<MonotonicClock> {
    @Override // javax.inject.Provider
    public MonotonicClock get() {
        return TimeModule.provideElapsedRealtimeClock(TimeModule._UL__ULSEP_com_facebook_common_time_RealtimeSinceBootClock_ULSEP_ACCESS_METHOD(this));
    }
}
