package X;

import androidx.annotation.VisibleForTesting;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1qk  reason: invalid class name and case insensitive filesystem */
public final class C09981qk extends InputStream {
    @VisibleForTesting
    public int A00;
    @VisibleForTesting
    public int A01;
    @VisibleForTesting
    public final C10021qp A02;

    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public final int available() {
        return this.A02.A02() - this.A01;
    }

    public final void mark(int i) {
        this.A00 = this.A01;
    }

    @Override // java.io.InputStream
    public final void reset() {
        this.A01 = this.A00;
    }

    @Override // java.io.InputStream
    public final long skip(long j) {
        boolean z = false;
        if (j >= 0) {
            z = true;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z));
        int min = Math.min((int) j, available());
        this.A01 += min;
        return (long) min;
    }

    public C09981qk(C10021qp r2) {
        boolean z;
        synchronized (r2) {
            z = !AnonymousClass1qa.A02(r2.A00);
        }
        AnonymousClass0KU.A01(Boolean.valueOf(!z));
        this.A02 = r2;
        this.A01 = 0;
        this.A00 = 0;
    }

    @Override // java.io.InputStream
    public final int read() {
        if (available() <= 0) {
            return -1;
        }
        C10021qp r2 = this.A02;
        int i = this.A01;
        this.A01 = i + 1;
        return r2.A01(i) & 255;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            StringBuilder sb = new StringBuilder("length=");
            sb.append(bArr.length);
            sb.append("; regionStart=");
            sb.append(i);
            sb.append("; regionLength=");
            sb.append(i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int available = available();
        if (available <= 0) {
            return -1;
        }
        if (i2 <= 0) {
            return 0;
        }
        int min = Math.min(available, i2);
        this.A02.A03(this.A01, bArr, i, min);
        this.A01 += min;
        return min;
    }
}
