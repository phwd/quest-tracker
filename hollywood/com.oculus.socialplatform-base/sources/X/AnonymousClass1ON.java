package X;

import com.adobe.xmp.impl.Base64;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1ON  reason: invalid class name */
public final class AnonymousClass1ON extends FilterInputStream {
    public int A00;
    public int A01;
    public final byte[] A02;

    public final void mark(int i) {
        if (this.in.markSupported()) {
            super.mark(i);
            this.A01 = this.A00;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final void reset() throws IOException {
        if (this.in.markSupported()) {
            this.in.reset();
            this.A00 = this.A01;
            return;
        }
        throw new IOException("mark is not supported");
    }

    public AnonymousClass1ON(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        this.A02 = bArr;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            return read;
        }
        int i = this.A00;
        byte[] bArr = this.A02;
        if (i >= bArr.length) {
            return -1;
        }
        this.A00 = i + 1;
        return bArr[i] & Base64.INVALID;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read == -1) {
            read = 0;
            if (i2 != 0) {
                while (read < i2) {
                    int i3 = this.A00;
                    byte[] bArr2 = this.A02;
                    if (i3 >= bArr2.length) {
                        break;
                    }
                    this.A00 = i3 + 1;
                    int i4 = bArr2[i3] & Base64.INVALID;
                    if (i4 == -1) {
                        break;
                    }
                    bArr[i + read] = (byte) i4;
                    read++;
                }
                if (read <= 0) {
                    return -1;
                }
            }
        }
        return read;
    }
}
