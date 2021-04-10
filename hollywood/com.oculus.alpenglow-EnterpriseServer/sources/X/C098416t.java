package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.16t  reason: invalid class name and case insensitive filesystem */
public final class C098416t extends InputStream {
    public C098316s A00 = new C098316s();
    public AbstractC09650zy A01;

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

    public C098416t(AbstractC09650zy r2) {
        this.A01 = r2;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr);
        if (read > 1) {
            throw new IOException("Read returned more than 1 byte");
        } else if (read == 1) {
            return (short) (((short) (bArr[0] & 255)) | 0);
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
        String str;
        if (bArr != null) {
            int i3 = 0;
            if (i2 != 0) {
                if (i + i2 > bArr.length) {
                    str = "Not enough space in destination buffer.";
                } else {
                    do {
                        C098316s r1 = this.A00;
                        int available = r1.available();
                        if (available > 0) {
                            int min = Math.min(i2, available);
                            int read = r1.read(bArr, i, min);
                            if (read <= min) {
                                i += min;
                                i2 -= min;
                                i3 += min;
                            } else {
                                str = AnonymousClass006.A03("Read returned more than requested bytes. ", read, " > ", min);
                            }
                        } else {
                            this.A01.A78();
                        }
                        if (this.A00.available() != 0) {
                        }
                    } while (i3 < i2);
                    return i3;
                }
            }
            return i3;
        }
        str = "Buffer is null";
        throw new IOException(str);
    }
}
