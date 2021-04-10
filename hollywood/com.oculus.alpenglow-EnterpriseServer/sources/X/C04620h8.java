package X;

import java.io.InputStream;

/* renamed from: X.0h8  reason: invalid class name and case insensitive filesystem */
public class C04620h8 extends InputStream {
    public final /* synthetic */ AnonymousClass0HR A00;

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() {
    }

    public C04620h8(AnonymousClass0HR r1) {
        this.A00 = r1;
    }

    @Override // java.io.InputStream
    public final int available() {
        return (int) Math.min(this.A00.A00, 2147483647L);
    }

    public final String toString() {
        return this.A00 + ".inputStream()";
    }

    @Override // java.io.InputStream
    public final int read() {
        AnonymousClass0HR r5 = this.A00;
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
