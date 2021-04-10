package com.facebook.common.android;

import android.net.NetworkInfo;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class NetworkInfoMethodAutoProvider extends AbstractProvider<NetworkInfo> {
    @Override // javax.inject.Provider
    public NetworkInfo get() {
        return AndroidModule.provideActiveNetworkInfo(AndroidModule._UL__ULSEP_android_net_ConnectivityManager_ULSEP_ACCESS_METHOD(this));
    }
}
