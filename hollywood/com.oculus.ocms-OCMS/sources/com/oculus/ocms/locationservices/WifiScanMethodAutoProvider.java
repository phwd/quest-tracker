package com.oculus.ocms.locationservices;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.wifiscan.WifiScan;
import com.oculus.executors.ExecutorsModule;

@Generated({"By: InjectorProcessor"})
public class WifiScanMethodAutoProvider extends AbstractProvider<WifiScan> {
    @Override // javax.inject.Provider
    public WifiScan get() {
        return LocationServiceModule.provideWifiScan(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this), ExecutorsModule._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_ACCESS_METHOD(this), LocationServiceModule._UL__ULSEP_com_facebook_common_time_MonotonicClock_ULSEP_ACCESS_METHOD(this), LocationServiceModule._UL__ULSEP_com_facebook_common_time_Clock_ULSEP_ACCESS_METHOD(this));
    }
}
