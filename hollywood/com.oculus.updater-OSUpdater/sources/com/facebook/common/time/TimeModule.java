package com.facebook.common.time;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;

@InjectorModule
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TimeModule extends AbstractLibraryModule {
    private static volatile CurrentThreadTimeClock _UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_INSTANCE;

    @AutoGeneratedFactoryMethod
    public static final AwakeTimeSinceBootClock _UL__ULSEP_com_facebook_common_time_AwakeTimeSinceBootClock_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideAwakeTimeSinceBootClock();
    }

    @AutoGeneratedFactoryMethod
    public static final CurrentThreadTimeClock _UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_INSTANCE == null) {
            synchronized (CurrentThreadTimeClock.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        injectorLike.getApplicationInjector();
                        _UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_INSTANCE = provideCurrentThreadTimeClock();
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final RealtimeSinceBootClock _UL__ULSEP_com_facebook_common_time_RealtimeSinceBootClock_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideRealtimeSinceBootClock();
    }

    @AutoGeneratedFactoryMethod
    public static final SystemClock _UL__ULSEP_com_facebook_common_time_SystemClock_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideSystemClock();
    }

    @ProviderMethod
    public static SystemClock provideSystemClock() {
        return SystemClock.get();
    }

    @ProviderMethod
    public static AwakeTimeSinceBootClock provideAwakeTimeSinceBootClock() {
        return AwakeTimeSinceBootClock.get();
    }

    @ProviderMethod
    public static RealtimeSinceBootClock provideRealtimeSinceBootClock() {
        return RealtimeSinceBootClock.get();
    }

    @ApplicationScoped
    @ProviderMethod
    public static CurrentThreadTimeClock provideCurrentThreadTimeClock() {
        return new CurrentThreadTimeClock();
    }
}