package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0h4  reason: invalid class name and case insensitive filesystem */
public class C04580h4 extends InputStream {
    public final /* synthetic */ AnonymousClass0HO A00;

    public C04580h4(AnonymousClass0HO r1) {
        this.A00 = r1;
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        AnonymousClass0HO r1 = this.A00;
        if (!r1.A00) {
            return (int) Math.min(r1.A01.A00, 2147483647L);
        }
        throw new IOException("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        this.A00.close();
    }

    public final String toString() {
        return this.A00 + ".inputStream()";
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        AnonymousClass0HO r6 = this.A00;
        if (!r6.A00) {
            AnonymousClass0HR r5 = r6.A01;
            if (r5.A00 == 0 && r6.A02.read(r5, 8192) == -1) {
                return -1;
            }
            return r5.readByte() & 255;
        }
        throw new IOException("closed");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        AnonymousClass0HO r3 = this.A00;
        if (!r3.A00) {
            C04530gy.A00((long) bArr.length, (long) i, (long) i2);
            AnonymousClass0HR r5 = r3.A01;
            if (r5.A00 == 0 && r3.A02.read(r5, 8192) == -1) {
                return -1;
            }
            return r5.A02(bArr, i, i2);
        }
        throw new IOException("closed");
    }
}
