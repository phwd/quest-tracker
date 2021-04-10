package com.android.org.bouncycastle.crypto.io;

import com.android.org.bouncycastle.crypto.Mac;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MacInputStream extends FilterInputStream {
    protected Mac mac;

    public MacInputStream(InputStream stream, Mac mac2) {
        super(stream);
        this.mac = mac2;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int b = this.in.read();
        if (b >= 0) {
            this.mac.update((byte) b);
        }
        return b;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] b, int off, int len) throws IOException {
        int n = this.in.read(b, off, len);
        if (n >= 0) {
            this.mac.update(b, off, n);
        }
        return n;
    }

    public Mac getMac() {
        return this.mac;
    }
}
