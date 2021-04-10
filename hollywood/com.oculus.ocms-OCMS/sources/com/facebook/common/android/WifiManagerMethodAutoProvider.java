package com.facebook.common.android;

import android.net.wifi.WifiManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class WifiManagerMethodAutoProvider extends AbstractProvider<WifiManager> {
    @Override // javax.inject.Provider
    public WifiManager get() {
        return AndroidModule.provideWifiManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
