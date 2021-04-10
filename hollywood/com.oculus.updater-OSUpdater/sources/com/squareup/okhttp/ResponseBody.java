package com.squareup.okhttp;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import okio.BufferedSource;

public abstract class ResponseBody implements Closeable {
    public abstract long contentLength() throws IOException;

    public abstract MediaType contentType();

    public abstract BufferedSource source() throws IOException;

    public final InputStream byteStream() throws IOException {
        return source().inputStream();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        source().close();
    }
}
