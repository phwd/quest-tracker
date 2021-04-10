package com.android.okhttp.okio;

import java.io.Closeable;
import java.io.Flushable;

public interface Sink extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    Timeout timeout();

    void write(Buffer buffer, long j);
}
