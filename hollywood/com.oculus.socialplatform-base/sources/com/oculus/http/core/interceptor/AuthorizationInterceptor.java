package com.oculus.http.core.interceptor;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class AuthorizationInterceptor implements Interceptor {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;

    @Nullable
    public abstract Request.Builder addAuthorization(Request request) throws IOException;

    public abstract void onUnauthorized(Response response);

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder addAuthorization;
        Request request = chain.request();
        if (request.header("Authorization") == null && (addAuthorization = addAuthorization(request)) != null) {
            request = addAuthorization.build();
        }
        Response proceed = chain.proceed(request);
        if (proceed.code == 401) {
            onUnauthorized(proceed);
        }
        return proceed;
    }
}
