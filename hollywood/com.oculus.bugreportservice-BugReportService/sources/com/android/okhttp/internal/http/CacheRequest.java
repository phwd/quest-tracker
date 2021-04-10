package com.android.okhttp.internal.http;

import com.android.okhttp.okio.Sink;

public interface CacheRequest {
    void abort();

    Sink body();
}
