package com.oculus.http.core;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class HttpCoreLoggerAutoProvider extends AbstractProvider<HttpCoreLogger> {
    @Override // javax.inject.Provider
    public HttpCoreLogger get() {
        return new HttpCoreLogger(this);
    }
}
