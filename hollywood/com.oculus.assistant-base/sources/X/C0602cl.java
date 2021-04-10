package X;

import java.io.InputStream;

/* renamed from: X.cl  reason: case insensitive filesystem */
public final class C0602cl extends InputStream {
    public final /* synthetic */ AnonymousClass33 A00;

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
    }

    public C0602cl(AnonymousClass33 r1) {
        this.A00 = r1;
    }

    @Override // java.io.InputStream
    public final int available() {
        return (int) Math.min(this.A00.A00, 2147483647L);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A00);
        sb.append(".inputStream()");
        return sb.toString();
    }

    @Override // java.io.InputStream
    public final int read() {
        AnonymousClass33 r5 = this.A00;
        if (r5.A00 > 0) {
            return r5.readByte() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        return this.A00.A02(bArr, i, i2);
    }
}
