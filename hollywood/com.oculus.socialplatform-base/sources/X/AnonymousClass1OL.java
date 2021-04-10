package X;

import androidx.annotation.NonNull;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1OL  reason: invalid class name */
public final class AnonymousClass1OL extends FilterInputStream {
    public int A00 = Integer.MIN_VALUE;

    public final synchronized void mark(int i) {
        super.mark(i);
        this.A00 = i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() throws IOException {
        super.reset();
        this.A00 = Integer.MIN_VALUE;
    }

    private void A00(long j) {
        int i = this.A00;
        if (i != Integer.MIN_VALUE && j != -1) {
            this.A00 = (int) (((long) i) - j);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() throws IOException {
        int i = this.A00;
        if (i == Integer.MIN_VALUE) {
            return super.available();
        }
        return Math.min(i, super.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        int i = this.A00;
        if (i == 0) {
            return 0;
        }
        if (i != Integer.MIN_VALUE) {
            long j2 = (long) i;
            if (j > j2) {
                j = j2;
            }
        }
        if (j == -1) {
            return 0;
        }
        long skip = super.skip(j);
        A00(skip);
        return skip;
    }

    public AnonymousClass1OL(@NonNull InputStream inputStream) {
        super(inputStream);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        int i = this.A00;
        if (i == 0) {
            return -1;
        }
        if (i != Integer.MIN_VALUE) {
            long j = (long) i;
            if (1 > j && j == -1) {
                return -1;
            }
        }
        int read = super.read();
        A00(1);
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(@NonNull byte[] bArr, int i, int i2) throws IOException {
        long j = (long) i2;
        int i3 = this.A00;
        if (i3 == 0) {
            j = -1;
        } else if (i3 != Integer.MIN_VALUE) {
            long j2 = (long) i3;
            if (j > j2) {
                j = j2;
            }
        }
        int i4 = (int) j;
        if (i4 == -1) {
            return -1;
        }
        int read = super.read(bArr, i, i4);
        A00((long) read);
        return read;
    }
}
