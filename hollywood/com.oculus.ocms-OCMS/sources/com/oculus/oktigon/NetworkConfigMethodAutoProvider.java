package com.oculus.oktigon;

import com.facebook.annotations.Generated;
import com.facebook.http.config.NetworkConfig;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class NetworkConfigMethodAutoProvider extends AbstractProvider<NetworkConfig> {
    @Override // javax.inject.Provider
    public NetworkConfig get() {
        return OculusOkTigonModule.provideNetworkConfig();
    }
}
