package com.facebook.common.android;

import android.net.ConnectivityManager;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class ConnectivityManagerMethodAutoProvider extends AbstractProvider<ConnectivityManager> {
    @Override // javax.inject.Provider
    public ConnectivityManager get() {
        return AndroidModule.provideConnectivityManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
