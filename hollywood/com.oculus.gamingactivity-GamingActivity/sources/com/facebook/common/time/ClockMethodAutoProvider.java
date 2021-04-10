package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ClockMethodAutoProvider extends AbstractProvider<Clock> {
    @Override // javax.inject.Provider
    public Clock get() {
        return TimeModule.provideDefaultClock(TimeModule.$ul_$xXXcom_facebook_common_time_SystemClock$xXXACCESS_METHOD(this));
    }
}
