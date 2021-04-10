package com.android.okhttp.internal.http;

import com.android.okhttp.Request;
import com.android.okhttp.Response;
import com.android.okhttp.ResponseBody;
import com.android.okhttp.okio.Sink;

public interface HttpStream {
    Sink createRequestBody(Request request, long j);

    void finishRequest();

    ResponseBody openResponseBody(Response response);

    Response.Builder readResponseHeaders();

    void setHttpEngine(HttpEngine httpEngine);

    void writeRequestBody(RetryableSink retryableSink);

    void writeRequestHeaders(Request request);
}
