package com.oculus.http.core.interceptor;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.GzipSource;
import okio.Okio;

public class GzipInterceptor implements Interceptor {
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        MediaType mediaType;
        Response proceed = chain.proceed(chain.request());
        ResponseBody responseBody = proceed.body;
        if (responseBody == null || !"gzip".equalsIgnoreCase(proceed.header(HEADER_CONTENT_ENCODING))) {
            return proceed;
        }
        GzipSource gzipSource = new GzipSource(responseBody.source());
        String header = proceed.header(HttpRequestMultipart.CONTENT_TYPE);
        if (header != null) {
            mediaType = MediaType.parse(header);
        } else {
            mediaType = null;
        }
        ResponseBody create = ResponseBody.create(mediaType, -1, Okio.buffer(gzipSource));
        Response.Builder newBuilder = proceed.newBuilder();
        newBuilder.body = create;
        newBuilder.headers.removeAll(HEADER_CONTENT_ENCODING);
        return newBuilder.build();
    }
}
