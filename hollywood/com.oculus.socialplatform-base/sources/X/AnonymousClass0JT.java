package X;

import com.adobe.xmp.impl.Base64;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.0JT  reason: invalid class name */
public final class AnonymousClass0JT extends InputStream {
    public int A00;
    public int A01;
    public boolean A02;
    public final InputStream A03;
    public final byte[] A04;
    public final AbstractC00840Jw<byte[]> A05;

    private void A00() throws IOException {
        if (this.A02) {
            throw new IOException("stream already closed");
        }
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        int i = this.A00;
        int i2 = this.A01;
        boolean z = false;
        if (i <= i2) {
            z = true;
        }
        C00740Ii.A03(z);
        A00();
        return (i2 - i) + this.A03.available();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        if (!this.A02) {
            this.A02 = true;
            this.A05.A8y(this.A04);
            super.close();
        }
    }

    @Override // java.lang.Object
    public final void finalize() throws Throwable {
        if (!this.A02) {
            AnonymousClass0J6 r1 = AnonymousClass0J5.A00;
            if (r1.A64(6)) {
                r1.A2h("PooledByteInputStream", "Finalized without closing");
            }
            close();
        }
        super.finalize();
    }

    @Override // java.io.InputStream
    public final long skip(long j) throws IOException {
        int i = this.A00;
        int i2 = this.A01;
        boolean z = false;
        if (i <= i2) {
            z = true;
        }
        C00740Ii.A03(z);
        A00();
        long j2 = (long) (i2 - i);
        if (j2 >= j) {
            this.A00 = (int) (((long) i) + j);
            return j;
        }
        this.A00 = i2;
        return j2 + this.A03.skip(j - j2);
    }

    public AnonymousClass0JT(InputStream inputStream, byte[] bArr, AbstractC00840Jw<byte[]> r4) {
        if (inputStream != null) {
            this.A03 = inputStream;
            if (bArr != null) {
                this.A04 = bArr;
                if (r4 != null) {
                    this.A05 = r4;
                    this.A01 = 0;
                    this.A00 = 0;
                    this.A02 = false;
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        int i = this.A00;
        int i2 = this.A01;
        boolean z = false;
        if (i <= i2) {
            z = true;
        }
        C00740Ii.A03(z);
        A00();
        if (i >= i2) {
            int read = this.A03.read(this.A04);
            if (read <= 0) {
                return -1;
            }
            this.A01 = read;
            this.A00 = 0;
            i = 0;
        }
        byte[] bArr = this.A04;
        this.A00 = i + 1;
        return bArr[i] & Base64.INVALID;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.A00;
        int i4 = this.A01;
        boolean z = false;
        if (i3 <= i4) {
            z = true;
        }
        C00740Ii.A03(z);
        A00();
        if (i3 >= i4) {
            i4 = this.A03.read(this.A04);
            if (i4 <= 0) {
                return -1;
            }
            this.A01 = i4;
            this.A00 = 0;
            i3 = 0;
        }
        int min = Math.min(i4 - i3, i2);
        System.arraycopy(this.A04, i3, bArr, i, min);
        this.A00 += min;
        return min;
    }
}
