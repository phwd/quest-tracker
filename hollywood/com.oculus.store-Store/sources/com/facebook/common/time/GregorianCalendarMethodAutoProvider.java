package com.facebook.common.time;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import java.util.GregorianCalendar;

@Generated({"By: InjectorProcessor"})
public class GregorianCalendarMethodAutoProvider extends AbstractProvider<GregorianCalendar> {
    @Override // javax.inject.Provider
    public GregorianCalendar get() {
        return TimeModule.provideGregorianCalendar();
    }
}
