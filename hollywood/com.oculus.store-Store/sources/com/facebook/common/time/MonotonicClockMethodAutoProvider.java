package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class MonotonicClockMethodAutoProvider extends AbstractProvider<MonotonicClock> {
    @Override // javax.inject.Provider
    public MonotonicClock get() {
        return TimeModule.provideDefaultClock(TimeModule.$ul_$xXXcom_facebook_common_time_AwakeTimeSinceBootClock$xXXACCESS_METHOD(this));
    }
}
