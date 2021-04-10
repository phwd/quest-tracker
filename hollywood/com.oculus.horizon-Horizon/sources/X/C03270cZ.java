package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0cZ  reason: invalid class name and case insensitive filesystem */
public final class C03270cZ extends InputStream {
    public int A00;
    public final /* synthetic */ C04240hB A01;

    public C03270cZ(C04240hB r1, int i) {
        this.A01 = r1;
        this.A00 = i;
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.A00;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        while (true) {
            int available = available();
            if (available > 0) {
                skip((long) available);
            } else {
                return;
            }
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.A00 == 0) {
            return -1;
        }
        int read = this.A01.A01.read();
        if (read != -1) {
            this.A00--;
            return read;
        }
        throw new IOException("compressed stream terminated prematurely");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 0 && this.A00 == 0) {
            return -1;
        }
        int read = this.A01.A01.read(bArr, i, Math.min(this.A00, i2));
        if (read <= 0) {
            return read;
        }
        this.A00 -= read;
        return read;
    }
}
