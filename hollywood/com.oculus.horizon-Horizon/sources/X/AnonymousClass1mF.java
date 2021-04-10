package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1mF  reason: invalid class name */
public final class AnonymousClass1mF extends FilterInputStream {
    public int A00;
    public int A01;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() throws IOException {
        return Math.min(this.in.available(), this.A00);
    }

    public final void mark(int i) {
        if (this.in.markSupported()) {
            this.in.mark(i);
            this.A01 = this.A00;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void reset() throws IOException {
        String str;
        if (!this.in.markSupported()) {
            str = "mark is not supported";
        } else if (this.A01 != -1) {
            this.in.reset();
            this.A00 = this.A01;
            return;
        } else {
            str = "mark not set";
        }
        throw new IOException(str);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long skip = this.in.skip(Math.min(j, (long) this.A00));
        this.A00 = (int) (((long) this.A00) - skip);
        return skip;
    }

    public AnonymousClass1mF(InputStream inputStream, int i) {
        super(inputStream);
        if (i >= 0) {
            this.A00 = i;
            this.A01 = -1;
            return;
        }
        throw new IllegalArgumentException("limit must be >= 0");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        if (this.A00 == 0) {
            return -1;
        }
        int read = this.in.read();
        if (read != -1) {
            this.A00--;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.A00;
        if (i3 == 0) {
            return -1;
        }
        int read = this.in.read(bArr, i, Math.min(i2, i3));
        if (read <= 0) {
            return read;
        }
        this.A00 -= read;
        return read;
    }
}
