package com.oculus.http.core;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import retrofit.ErrorHandler;

@Generated({"By: InjectorProcessor"})
public class ErrorHandlerMethodAutoProvider extends AbstractProvider<ErrorHandler> {
    @Override // javax.inject.Provider
    public ErrorHandler get() {
        return ApiModule.provideErrorHandler(LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_ACCESS_METHOD(this));
    }
}
