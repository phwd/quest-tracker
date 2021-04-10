package com.oculus.http.core.interceptor;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class OculusAuthorizationInterceptorAutoProvider extends AbstractProvider<OculusAuthorizationInterceptor> {
    @Override // javax.inject.Provider
    public OculusAuthorizationInterceptor get() {
        return new OculusAuthorizationInterceptor(this);
    }
}
