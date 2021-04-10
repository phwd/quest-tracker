package X;

import androidx.annotation.NonNull;
import com.adobe.xmp.impl.Base64;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.1ax  reason: invalid class name and case insensitive filesystem */
public final class C06741ax extends FilterInputStream {
    public int A00;
    public int A01 = -1;
    public int A02;
    public int A03;
    public final AnonymousClass1hX A04;
    public volatile byte[] A05;

    public final synchronized void A01() {
        if (this.A05 != null) {
            this.A04.A05(this.A05);
            this.A05 = null;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int available() throws IOException {
        InputStream inputStream;
        inputStream = this.in;
        if (this.A05 == null || inputStream == null) {
            throw new IOException("BufferedInputStream is closed");
        }
        return (this.A02 - this.A03) + inputStream.available();
    }

    public final synchronized void mark(int i) {
        this.A00 = Math.max(this.A00, i);
        this.A01 = this.A03;
    }

    public final boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() throws IOException {
        if (this.A05 != null) {
            int i = this.A01;
            if (-1 != i) {
                this.A03 = i;
            } else {
                throw new C06751ay(AnonymousClass006.A05("Mark has been invalidated, pos: ", this.A03, " markLimit: ", this.A00));
            }
        } else {
            throw new IOException("Stream is closed");
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized long skip(long j) throws IOException {
        long j2;
        if (j < 1) {
            j = 0;
        } else {
            byte[] bArr = this.A05;
            if (bArr != null) {
                InputStream inputStream = this.in;
                if (inputStream != null) {
                    int i = this.A02;
                    int i2 = this.A03;
                    if (((long) (i - i2)) >= j) {
                        j2 = ((long) i2) + j;
                    } else {
                        long j3 = ((long) i) - ((long) i2);
                        this.A03 = i;
                        if (this.A01 == -1 || j > ((long) this.A00)) {
                            j3 += inputStream.skip(j - j3);
                        } else if (A00(inputStream, bArr) != -1) {
                            int i3 = this.A02;
                            int i4 = this.A03;
                            if (((long) (i3 - i4)) >= j - j3) {
                                j2 = (((long) i4) + j) - j3;
                            } else {
                                j3 = (j3 + ((long) i3)) - ((long) i4);
                                this.A03 = i3;
                            }
                        }
                        return j3;
                    }
                    this.A03 = (int) j2;
                } else {
                    throw new IOException("BufferedInputStream is closed");
                }
            } else {
                throw new IOException("BufferedInputStream is closed");
            }
        }
        return j;
    }

    public C06741ax(@NonNull InputStream inputStream, @NonNull AnonymousClass1hX r4) {
        super(inputStream);
        this.A04 = r4;
        this.A05 = (byte[]) r4.A04(65536, byte[].class);
    }

    private int A00(InputStream inputStream, byte[] bArr) throws IOException {
        int read;
        int i;
        int length;
        int i2 = this.A01;
        if (i2 == -1 || this.A03 - i2 >= (i = this.A00)) {
            read = inputStream.read(bArr);
            if (read > 0) {
                this.A01 = -1;
                this.A03 = 0;
                this.A02 = read;
                return read;
            }
        } else {
            if (i2 == 0 && i > (length = bArr.length) && this.A02 == length) {
                int i3 = length << 1;
                if (i3 > i) {
                    i3 = i;
                }
                AnonymousClass1hX r1 = this.A04;
                byte[] bArr2 = (byte[]) r1.A04(i3, byte[].class);
                System.arraycopy(bArr, 0, bArr2, 0, length);
                this.A05 = bArr2;
                r1.A05(bArr);
                bArr = bArr2;
            } else if (i2 > 0) {
                System.arraycopy(bArr, i2, bArr, 0, bArr.length - i2);
            }
            int i4 = this.A03 - this.A01;
            this.A03 = i4;
            this.A01 = 0;
            this.A02 = 0;
            read = inputStream.read(bArr, i4, bArr.length - i4);
            int i5 = this.A03;
            if (read > 0) {
                i5 += read;
            }
            this.A02 = i5;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public final void close() throws IOException {
        if (this.A05 != null) {
            this.A04.A05(this.A05);
            this.A05 = null;
        }
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized int read() throws IOException {
        byte[] bArr = this.A05;
        InputStream inputStream = this.in;
        if (bArr == null || inputStream == null) {
            throw new IOException("BufferedInputStream is closed");
        }
        if (this.A03 < this.A02 || A00(inputStream, bArr) != -1) {
            if (bArr == this.A05 || (bArr = this.A05) != null) {
                int i = this.A02;
                int i2 = this.A03;
                if (i - i2 > 0) {
                    this.A03 = i2 + 1;
                    return bArr[i2] & Base64.INVALID;
                }
            } else {
                throw new IOException("BufferedInputStream is closed");
            }
        }
        return -1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0072, code lost:
        return r9;
     */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int read(@androidx.annotation.NonNull byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 140
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06741ax.read(byte[], int, int):int");
    }
}
