package com.oculus.http.core.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class NpeExceptionHandlerInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        try {
            return chain.proceed(chain.request());
        } catch (NullPointerException e) {
            throw new IOException(e.getMessage());
        }
    }
}
