package com.oculus.http.core.interceptor;

import java.io.IOException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHeadersInterceptor implements Interceptor {
    private final Map<String, String> requestHeaders;

    public RequestHeadersInterceptor(Map<String, String> requestHeaders2) {
        this.requestHeaders = requestHeaders2;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        for (Map.Entry<String, String> requestHeader : this.requestHeaders.entrySet()) {
            builder.header(requestHeader.getKey(), requestHeader.getValue());
        }
        return chain.proceed(builder.build());
    }
}
