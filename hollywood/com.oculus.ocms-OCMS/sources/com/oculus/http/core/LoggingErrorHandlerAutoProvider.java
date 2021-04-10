package com.oculus.http.core;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class LoggingErrorHandlerAutoProvider extends AbstractProvider<LoggingErrorHandler> {
    @Override // javax.inject.Provider
    public LoggingErrorHandler get() {
        return new LoggingErrorHandler(this);
    }
}
