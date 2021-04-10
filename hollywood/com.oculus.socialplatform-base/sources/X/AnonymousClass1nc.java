package X;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* renamed from: X.1nc  reason: invalid class name */
public final class AnonymousClass1nc implements Closeable {
    public int A00;
    public byte[] A01;
    public int A02;
    public final InputStream A03;
    public final Charset A04;

    public AnonymousClass1nc(InputStream inputStream, Charset charset) {
        if (charset == null) {
            throw null;
        } else if (charset.equals(C06051On.A01)) {
            this.A03 = inputStream;
            this.A04 = charset;
            this.A01 = new byte[8192];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    private void A00() throws IOException {
        InputStream inputStream = this.A03;
        byte[] bArr = this.A01;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read != -1) {
            this.A02 = 0;
            this.A00 = read;
            return;
        }
        throw new EOFException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        if (r3[r2] == 13) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String A01() throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1nc.A01():java.lang.String");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        InputStream inputStream = this.A03;
        synchronized (inputStream) {
            if (this.A01 != null) {
                this.A01 = null;
                inputStream.close();
            }
        }
    }
}
