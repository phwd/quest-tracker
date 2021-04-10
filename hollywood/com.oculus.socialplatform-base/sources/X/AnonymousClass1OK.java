package X;

import androidx.annotation.NonNull;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1OK  reason: invalid class name */
public final class AnonymousClass1OK extends FilterInputStream {
    public int A00;
    public final long A01;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int available() throws IOException {
        return (int) Math.max(this.A01 - ((long) this.A00), (long) this.in.available());
    }

    private void A00(int i) throws IOException {
        if (i >= 0) {
            this.A00 += i;
            return;
        }
        long j = this.A01;
        int i2 = this.A00;
        if (j - ((long) i2) > 0) {
            StringBuilder sb = new StringBuilder("Failed to read all expected data, expected: ");
            sb.append(j);
            sb.append(", but read: ");
            sb.append(i2);
            throw new IOException(sb.toString());
        }
    }

    public AnonymousClass1OK(@NonNull InputStream inputStream, long j) {
        super(inputStream);
        this.A01 = j;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read() throws IOException {
        int read;
        read = super.read();
        int i = -1;
        if (read >= 0) {
            i = 1;
        }
        A00(i);
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        int read;
        read = super.read(bArr, i, i2);
        A00(read);
        return read;
    }
}
