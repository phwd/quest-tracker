package com.oculus.os;

import java.io.IOException;
import java.io.OutputStream;

public class CalibrationOutputStream extends OutputStream {
    public CalibrationOutputStream(String type) {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        throw new RuntimeException("Stub!");
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        throw new RuntimeException("Stub!");
    }
}
