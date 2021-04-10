package com.oculus.http.socketconfig;

import com.facebook.annotations.Generated;
import com.facebook.http.config.SocketConfig;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class SocketConfigMethodAutoProvider extends AbstractProvider<SocketConfig> {
    @Override // javax.inject.Provider
    public SocketConfig get() {
        return SocketConfigModule.provideSocketConfig();
    }
}
