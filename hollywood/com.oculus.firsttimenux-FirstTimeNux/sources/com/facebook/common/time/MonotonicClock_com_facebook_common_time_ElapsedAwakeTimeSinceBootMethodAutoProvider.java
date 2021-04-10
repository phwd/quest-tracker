package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MonotonicClock_com_facebook_common_time_ElapsedAwakeTimeSinceBootMethodAutoProvider extends AbstractProvider<MonotonicClock> {
    @Override // javax.inject.Provider
    public MonotonicClock get() {
        return TimeModule.provideElapsedAwakeTimeClock(TimeModule.$ul_$xXXcom_facebook_common_time_AwakeTimeSinceBootClock$xXXACCESS_METHOD(this));
    }
}
