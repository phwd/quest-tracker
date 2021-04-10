package com.facebook.common.android;

import android.telephony.TelephonyManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class TelephonyManagerMethodAutoProvider extends AbstractProvider<TelephonyManager> {
    @Override // javax.inject.Provider
    public TelephonyManager get() {
        return AndroidModule.provideTelephonyManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
