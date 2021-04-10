package com.facebook.common.android;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.support.v4.net.ConnectivityManagerCompat;

@Generated({"By: InjectorProcessor"})
public class ConnectivityManagerCompatMethodAutoProvider extends AbstractProvider<ConnectivityManagerCompat> {
    @Override // javax.inject.Provider
    public ConnectivityManagerCompat get() {
        return AndroidModule.provideConnectivityManagerCompat();
    }
}
