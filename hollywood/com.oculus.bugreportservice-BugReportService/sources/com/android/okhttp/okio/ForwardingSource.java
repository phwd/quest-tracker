package com.android.okhttp.okio;

public abstract class ForwardingSource implements Source {
    private final Source delegate;

    public ForwardingSource(Source source) {
        if (source != null) {
            this.delegate = source;
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // com.android.okhttp.okio.Source
    public long read(Buffer buffer, long j) {
        return this.delegate.read(buffer, j);
    }

    @Override // com.android.okhttp.okio.Source
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @Override // java.io.Closeable, com.android.okhttp.okio.Source, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
