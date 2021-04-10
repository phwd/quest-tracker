package com.oculus.autoupdates;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.time.Clock;

@Generated({"By: InjectorProcessor"})
public class AutoUpdateTimeTrackerAutoProvider extends AbstractProvider<AutoUpdateTimeTracker> {
    @Override // javax.inject.Provider
    public AutoUpdateTimeTracker get() {
        return new AutoUpdateTimeTracker(Clock._UL__ULSEP_com_oculus_time_Clock_ULSEP_ACCESS_METHOD(this), BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
