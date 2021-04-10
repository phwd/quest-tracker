package com.squareup.okhttp.internal.http;

import X.WG;
import java.io.IOException;

public interface CacheRequest {
    void abort();

    WG body() throws IOException;
}
