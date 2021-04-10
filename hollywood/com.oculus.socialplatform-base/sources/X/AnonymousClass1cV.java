package X;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

/* renamed from: X.1cV  reason: invalid class name */
public final class AnonymousClass1cV extends InputStream {
    public static final Queue<AnonymousClass1cV> A02 = new ArrayDeque(0);
    public IOException A00;
    public InputStream A01;

    @Override // java.io.InputStream
    public final synchronized void reset() throws IOException {
        this.A01.reset();
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        return this.A01.available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        this.A01.close();
    }

    public final void mark(int i) {
        this.A01.mark(i);
    }

    public final boolean markSupported() {
        return this.A01.markSupported();
    }

    @Override // java.io.InputStream
    public final long skip(long j) {
        try {
            return this.A01.skip(j);
        } catch (IOException e) {
            this.A00 = e;
            return 0;
        }
    }

    @Override // java.io.InputStream
    public final int read() {
        try {
            return this.A01.read();
        } catch (IOException e) {
            this.A00 = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) {
        try {
            return this.A01.read(bArr);
        } catch (IOException e) {
            this.A00 = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        try {
            return this.A01.read(bArr, i, i2);
        } catch (IOException e) {
            this.A00 = e;
            return -1;
        }
    }
}
