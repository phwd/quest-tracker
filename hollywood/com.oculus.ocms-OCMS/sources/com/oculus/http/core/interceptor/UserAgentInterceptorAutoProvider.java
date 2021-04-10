package com.oculus.http.core.interceptor;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class UserAgentInterceptorAutoProvider extends AbstractProvider<UserAgentInterceptor> {
    @Override // javax.inject.Provider
    public UserAgentInterceptor get() {
        return new UserAgentInterceptor(this);
    }
}
