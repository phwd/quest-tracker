package X;

import com.adobe.xmp.impl.Base64;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1oC  reason: invalid class name */
public final class AnonymousClass1oC extends InputStream {
    public AnonymousClass1oD A00 = new AnonymousClass1oD();
    public AnonymousClass1oF A01;

    public final synchronized void mark(int i) {
        this.A00.mark(i);
    }

    @Override // java.io.InputStream
    public final synchronized void reset() throws IOException {
        this.A00.reset();
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        return this.A00.available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        this.A00.close();
    }

    public final boolean markSupported() {
        return this.A00.markSupported();
    }

    @Override // java.io.InputStream
    public final long skip(long j) throws IOException {
        return this.A00.skip(j);
    }

    public AnonymousClass1oC(AnonymousClass1oF r2) {
        this.A01 = r2;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr);
        if (read > 1) {
            throw new IOException("Read returned more than 1 byte");
        } else if (read == 1) {
            return (short) (((short) (bArr[0] & Base64.INVALID)) | 0);
        } else {
            return -1;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        if (bArr != null) {
            return read(bArr, 0, bArr.length);
        }
        throw new IOException("Buffer is null.");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr != null) {
            int i3 = 0;
            if (i2 != 0) {
                if (i + i2 > bArr.length) {
                    throw new IOException("Not enough space in destination buffer.");
                }
                do {
                    AnonymousClass1oD r1 = this.A00;
                    int available = r1.available();
                    if (available > 0) {
                        int min = Math.min(i2, available);
                        int read = r1.read(bArr, i, min);
                        if (read <= min) {
                            i += min;
                            i2 -= min;
                            i3 += min;
                        } else {
                            throw new IOException(AnonymousClass006.A05("Read returned more than requested bytes. ", read, " > ", min));
                        }
                    } else {
                        this.A01.A8s();
                    }
                    if (this.A00.available() != 0) {
                    }
                } while (i3 < i2);
                return i3;
            }
            return i3;
        }
        throw new IOException("Buffer is null");
    }
}
