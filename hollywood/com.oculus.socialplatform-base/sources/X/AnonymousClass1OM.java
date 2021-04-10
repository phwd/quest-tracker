package X;

import com.adobe.xmp.impl.Base64;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1OM  reason: invalid class name */
public final class AnonymousClass1OM extends FilterInputStream {
    public static final byte[] A02 = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
    public int A00;
    public final byte A01;

    public final boolean markSupported() {
        return false;
    }

    public final void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    public AnonymousClass1OM(InputStream inputStream, int i) {
        super(inputStream);
        if (i < -1 || i > 8) {
            throw new IllegalArgumentException(AnonymousClass006.A03("Cannot add invalid orientation: ", i));
        }
        this.A01 = (byte) i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long skip = super.skip(j);
        if (skip > 0) {
            this.A00 = (int) (((long) this.A00) + skip);
        }
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        int read;
        int i = this.A00;
        if (i < 2 || i > 31) {
            read = super.read();
        } else if (i == 31) {
            read = this.A01;
        } else {
            read = A02[i - 2] & Base64.INVALID;
        }
        if (read != -1) {
            this.A00++;
        }
        return read;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r1 > 0) goto L_0x000c;
     */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int read(@androidx.annotation.NonNull byte[] r5, int r6, int r7) throws java.io.IOException {
        /*
            r4 = this;
            int r3 = r4.A00
            r0 = 31
            if (r3 <= r0) goto L_0x0012
            int r1 = super.read(r5, r6, r7)
        L_0x000a:
            if (r1 <= 0) goto L_0x0011
        L_0x000c:
            int r0 = r4.A00
            int r0 = r0 + r1
            r4.A00 = r0
        L_0x0011:
            return r1
        L_0x0012:
            if (r3 != r0) goto L_0x001a
            byte r0 = r4.A01
            r5[r6] = r0
            r1 = 1
            goto L_0x000c
        L_0x001a:
            r2 = 2
            if (r3 >= r2) goto L_0x0023
            int r2 = r2 - r3
            int r1 = super.read(r5, r6, r2)
            goto L_0x000a
        L_0x0023:
            int r0 = r0 - r3
            int r1 = java.lang.Math.min(r0, r7)
            byte[] r0 = X.AnonymousClass1OM.A02
            int r3 = r3 - r2
            java.lang.System.arraycopy(r0, r3, r5, r6, r1)
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1OM.read(byte[], int, int):int");
    }
}
