package X;

import com.adobe.xmp.impl.Base64;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1oD  reason: invalid class name */
public final class AnonymousClass1oD extends InputStream implements AnonymousClass1oG {
    public int A00 = -1;
    public int A01 = 0;
    public int A02 = 0;
    public byte[] A03 = new byte[1024];

    public final synchronized void A00() {
        this.A00 = this.A02;
    }

    public final synchronized void mark(int i) {
        A00();
    }

    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public final synchronized void reset() throws IOException {
        if (this.A03 != null) {
            int i = this.A00;
            if (i != -1) {
                this.A02 = i;
                this.A00 = -1;
            } else {
                throw new IOException("No marked position found.");
            }
        } else {
            throw new IOException("Stream is closed.");
        }
    }

    @Override // X.AnonymousClass1oG
    public final void A1S(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        byte[] bArr2 = this.A03;
        byte[] bArr3 = bArr2;
        if (bArr2 == null) {
            throw new IOException("Stream is closed.");
        } else if (bArr != null && i2 != 0) {
            if (i + i2 <= bArr.length) {
                int i4 = this.A02;
                int i5 = this.A01;
                int i6 = i5;
                if (i4 == i5 && this.A00 == -1) {
                    this.A01 = 0;
                    i5 = 0;
                    i6 = 0;
                    this.A02 = 0;
                    i4 = 0;
                }
                int i7 = i5 + i2;
                int length = bArr2.length;
                if (i7 > length) {
                    int i8 = this.A00;
                    int i9 = i5 - i8;
                    if (i8 == -1) {
                        i9 = i5 - i4;
                    }
                    int i10 = length - i9;
                    if (i10 < i2) {
                        bArr3 = new byte[(length + (((int) Math.ceil(((double) (i2 - i10)) / 1024.0d)) << 10))];
                        i3 = 0;
                        if (i8 == -1) {
                            int i11 = i5 - i4;
                            System.arraycopy(bArr2, i4, bArr3, 0, i11);
                            this.A02 = 0;
                            this.A01 = i11;
                            i6 = i11;
                            this.A00 = -1;
                        }
                        System.arraycopy(bArr2, i8, bArr3, i3, i9);
                        int i12 = this.A02;
                        int i13 = this.A00;
                        this.A02 = i12 - i13;
                        i6 = this.A01 - i13;
                        this.A01 = i6;
                        this.A00 = i3;
                    } else {
                        bArr3 = new byte[length];
                        i3 = 0;
                        if (i8 == -1) {
                            int i14 = i5 - i4;
                            System.arraycopy(bArr2, i4, bArr3, 0, i14);
                            this.A02 = 0;
                            this.A01 = i14;
                            i6 = i14;
                            this.A00 = -1;
                        }
                        System.arraycopy(bArr2, i8, bArr3, i3, i9);
                        int i122 = this.A02;
                        int i132 = this.A00;
                        this.A02 = i122 - i132;
                        i6 = this.A01 - i132;
                        this.A01 = i6;
                        this.A00 = i3;
                    }
                    this.A03 = bArr3;
                }
                System.arraycopy(bArr, i, bArr3, i6, i2);
                this.A01 += i2;
                return;
            }
            throw new IOException(AnonymousClass006.A04("Len ", i2, " exceeds supplied buffer limits."));
        }
    }

    @Override // java.io.InputStream
    public final int available() {
        if (this.A03 == null) {
            return -1;
        }
        return this.A01 - this.A02;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        if (this.A03 != null) {
            super.close();
            this.A03 = null;
            this.A01 = 0;
            this.A02 = 0;
            this.A00 = -1;
            return;
        }
        throw new IOException("Stream is already closed.");
    }

    @Override // java.io.InputStream
    public final long skip(long j) throws IOException {
        if (this.A03 == null) {
            throw new IOException("Stream is closed.");
        } else if (j <= 0) {
            return 0;
        } else {
            long j2 = ((long) this.A02) + j;
            int i = this.A01;
            if (j2 >= ((long) i)) {
                int available = available();
                this.A02 = i;
                return (long) available;
            }
            this.A02 = (int) j2;
            return j;
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        byte[] bArr = new byte[1];
        int read = read(bArr);
        if (read > 1) {
            throw new IOException("Read returned more than 1 byte");
        } else if (read == 1) {
            return (short) (((short) (bArr[0] & Base64.INVALID)) | 0);
        } else {
            return -1;
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr) throws IOException {
        if (bArr != null) {
            return read(bArr, 0, bArr.length);
        }
        throw new IOException("Dst buffer is null");
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new IOException("Dst buffer is null");
        } else if (i2 == 0) {
            return i2;
        } else {
            if (i + i2 <= bArr.length) {
                int available = available();
                if (available < 1) {
                    return available;
                }
                int min = Math.min(available, i2);
                System.arraycopy(this.A03, this.A02, bArr, i, min);
                this.A02 += min;
                return min;
            }
            throw new IOException("Not enough space in destination buffer.");
        }
    }
}
