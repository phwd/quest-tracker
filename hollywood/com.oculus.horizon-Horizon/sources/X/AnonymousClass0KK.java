package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0KK  reason: invalid class name */
public final class AnonymousClass0KK extends FilterOutputStream {
    public long A00 = 0;

    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.out.close();
    }

    public AnonymousClass0KK(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public final void write(int i) throws IOException {
        this.out.write(i);
        this.A00++;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.A00 += (long) i2;
    }
}
