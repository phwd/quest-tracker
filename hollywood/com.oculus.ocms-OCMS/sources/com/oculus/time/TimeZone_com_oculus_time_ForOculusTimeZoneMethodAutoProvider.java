package com.oculus.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import java.util.TimeZone;

@Generated({"By: InjectorProcessor"})
public class TimeZone_com_oculus_time_ForOculusTimeZoneMethodAutoProvider extends AbstractProvider<TimeZone> {
    @Override // javax.inject.Provider
    public TimeZone get() {
        return TimeModule.provideTimeZone();
    }
}
