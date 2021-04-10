package com.oculus.userserver.api.inject;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusUserManagerProviderAutoProvider extends AbstractProvider<OculusUserManagerProvider> {
    @Override // javax.inject.Provider
    public OculusUserManagerProvider get() {
        return new OculusUserManagerProvider(this);
    }
}
