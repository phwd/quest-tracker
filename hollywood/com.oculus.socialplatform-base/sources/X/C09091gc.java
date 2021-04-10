package X;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1gc  reason: invalid class name and case insensitive filesystem */
public final class C09091gc implements AnonymousClass1gm {
    public final InputStream A00;

    @Override // X.AnonymousClass1gm
    public final int A8r(byte[] bArr, int i) throws IOException {
        int i2 = 0;
        int i3 = 0;
        while (i2 < i && (i3 = this.A00.read(bArr, i2, i - i2)) != -1) {
            i2 += i3;
        }
        if (i2 != 0 || i3 != -1) {
            return i2;
        }
        throw new AnonymousClass1hG();
    }

    @Override // X.AnonymousClass1gm
    public final short A5B() throws IOException {
        int read = this.A00.read();
        if (read != -1) {
            return (short) read;
        }
        throw new AnonymousClass1hG();
    }

    @Override // X.AnonymousClass1gm
    public final long skip(long j) throws IOException {
        if (j < 0) {
            return 0;
        }
        long j2 = j;
        while (j2 > 0) {
            InputStream inputStream = this.A00;
            long skip = inputStream.skip(j2);
            if (skip <= 0) {
                if (inputStream.read() == -1) {
                    break;
                }
                skip = 1;
            }
            j2 -= skip;
        }
        return j - j2;
    }

    public C09091gc(InputStream inputStream) {
        this.A00 = inputStream;
    }

    @Override // X.AnonymousClass1gm
    public final int A5A() throws IOException {
        return (A5B() << 8) | A5B();
    }
}
