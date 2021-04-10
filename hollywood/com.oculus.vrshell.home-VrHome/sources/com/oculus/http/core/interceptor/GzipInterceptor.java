package com.oculus.http.core.interceptor;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.GzipSource;
import okio.Okio;

public class GzipInterceptor implements Interceptor {
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        ResponseBody responseBody = response.body();
        if (responseBody == null || !"gzip".equalsIgnoreCase(response.header(HEADER_CONTENT_ENCODING))) {
            return response;
        }
        GzipSource gzipSource = new GzipSource(responseBody.source());
        String contentType = response.header("Content-Type");
        return response.newBuilder().body(ResponseBody.create(contentType != null ? MediaType.parse(contentType) : null, -1, Okio.buffer(gzipSource))).removeHeader(HEADER_CONTENT_ENCODING).build();
    }
}
