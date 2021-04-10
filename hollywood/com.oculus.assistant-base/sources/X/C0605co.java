package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.co  reason: case insensitive filesystem */
public final class C0605co extends InputStream {
    public final /* synthetic */ C00222y A00;

    public C0605co(C00222y r1) {
        this.A00 = r1;
    }

    @Override // java.io.InputStream
    public final int available() {
        C00222y r1 = this.A00;
        if (!r1.A00) {
            return (int) Math.min(r1.A01.A00, 2147483647L);
        }
        throw new IOException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
        this.A00.close();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A00);
        sb.append(".inputStream()");
        return sb.toString();
    }

    @Override // java.io.InputStream
    public final int read() {
        C00222y r6 = this.A00;
        if (!r6.A00) {
            AnonymousClass33 r5 = r6.A01;
            if (r5.A00 == 0 && r6.A02.A4c(r5, 8192) == -1) {
                return -1;
            }
            return r5.readByte() & 255;
        }
        throw new IOException("closed");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        C00222y r3 = this.A00;
        if (!r3.A00) {
            C0611cu.A00((long) bArr.length, (long) i, (long) i2);
            AnonymousClass33 r5 = r3.A01;
            if (r5.A00 == 0 && r3.A02.A4c(r5, 8192) == -1) {
                return -1;
            }
            return r5.A02(bArr, i, i2);
        }
        throw new IOException("closed");
    }
}
