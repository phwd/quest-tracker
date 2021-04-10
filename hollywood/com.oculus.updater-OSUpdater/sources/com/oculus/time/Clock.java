package com.oculus.time;

import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;

@Dependencies
public class Clock {
    @AutoGeneratedFactoryMethod
    public static final Clock _UL__ULSEP_com_oculus_time_Clock_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new Clock();
    }

    public long now() {
        return System.currentTimeMillis();
    }
}