package com.oculus.os;

import java.io.IOException;
import java.io.InputStream;

public class CalibrationInputStream extends InputStream {
    public CalibrationInputStream(String str) throws IOException {
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
    public int read(byte[] bArr) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public long getSize() {
        throw new RuntimeException("Stub!");
    }
}
