package com.oculus.http.core.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.GzipSource;
import okio.Okio;

public class GzipInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        ResponseBody body = proceed.body();
        if (body == null || !"gzip".equalsIgnoreCase(proceed.header("Content-Encoding"))) {
            return proceed;
        }
        GzipSource gzipSource = new GzipSource(body.source());
        String header = proceed.header("Content-Type");
        return proceed.newBuilder().body(ResponseBody.create(header != null ? MediaType.parse(header) : null, -1, Okio.buffer(gzipSource))).removeHeader("Content-Encoding").build();
    }
}
