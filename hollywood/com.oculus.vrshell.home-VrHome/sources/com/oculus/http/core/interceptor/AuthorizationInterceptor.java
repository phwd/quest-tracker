package com.oculus.http.core.interceptor;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class AuthorizationInterceptor implements Interceptor {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    static final int HTTP_STATUS_UNAUTHORIZED = 401;

    @Nullable
    public abstract Request.Builder addAuthorization(Request request) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void onUnauthorized(Response response);

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder requestBuilder;
        Request request = chain.request();
        if (request.header("Authorization") == null && (requestBuilder = addAuthorization(request)) != null) {
            request = requestBuilder.build();
        }
        Response response = chain.proceed(request);
        if (response.code() == HTTP_STATUS_UNAUTHORIZED) {
            onUnauthorized(response);
        }
        return response;
    }
}
