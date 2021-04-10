package com.facebook.common.android;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class Object_com_facebook_common_android_WifiP2pManagerSystemServiceMethodAutoProvider extends AbstractProvider<Object> {
    @Override // javax.inject.Provider
    public Object get() {
        return AndroidModule.provideWifiP2pManagerSystemService(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
