package com.oculus.os;

import java.io.IOException;
import java.io.OutputStream;

public class CalibrationOutputStream extends OutputStream {
    public CalibrationOutputStream(String str) {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        throw new RuntimeException("Stub!");
    }
}
