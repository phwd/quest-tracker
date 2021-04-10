package com.android.org.bouncycastle.jcajce.io;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Mac;

class MacUpdatingOutputStream extends OutputStream {
    private Mac mac;

    MacUpdatingOutputStream(Mac mac2) {
        this.mac = mac2;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bytes, int off, int len) throws IOException {
        this.mac.update(bytes, off, len);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bytes) throws IOException {
        this.mac.update(bytes);
    }

    @Override // java.io.OutputStream
    public void write(int b) throws IOException {
        this.mac.update((byte) b);
    }
}
