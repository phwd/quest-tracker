package com.oculus.os;

import java.io.IOException;
import java.io.InputStream;

public class CalibrationInputStream extends InputStream {
    public CalibrationInputStream(String type) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public int read(byte[] b) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public long getSize() {
        throw new RuntimeException("Stub!");
    }
}
