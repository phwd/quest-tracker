package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class Clock_com_facebook_common_time_CurrentThreadTimeMethodAutoProvider extends AbstractProvider<Clock> {
    @Override // javax.inject.Provider
    public Clock get() {
        return TimeModule.provideCurrentThreadTimeClock(TimeModule._UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_ACCESS_METHOD(this));
    }
}
