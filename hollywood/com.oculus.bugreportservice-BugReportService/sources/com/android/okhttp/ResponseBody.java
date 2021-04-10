package com.android.okhttp;

import com.android.okhttp.okio.BufferedSource;
import java.io.Closeable;
import java.io.InputStream;

public abstract class ResponseBody implements Closeable {
    public abstract long contentLength();

    public abstract BufferedSource source();

    public final InputStream byteStream() {
        return source().inputStream();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        source().close();
    }
}
