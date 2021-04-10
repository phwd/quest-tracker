package com.oculus.http.core.interceptor;

import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class RequestHeadersInterceptor implements Interceptor {
    private final Map<String, String> requestHeaders;

    public RequestHeadersInterceptor(Map<String, String> map) {
        this.requestHeaders = map;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder newBuilder = chain.request().newBuilder();
        for (Map.Entry<String, String> entry : this.requestHeaders.entrySet()) {
            newBuilder.header(entry.getKey(), entry.getValue());
        }
        return chain.proceed(newBuilder.build());
    }
}
