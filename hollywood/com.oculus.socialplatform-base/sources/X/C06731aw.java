package X;

import androidx.annotation.NonNull;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.1aw  reason: invalid class name and case insensitive filesystem */
public final class C06731aw extends OutputStream {
    public int A00;
    public AnonymousClass1hX A01;
    public byte[] A02;
    @NonNull
    public final OutputStream A03;

    public C06731aw(@NonNull OutputStream outputStream, @NonNull AnonymousClass1hX r4) {
        this.A03 = outputStream;
        this.A01 = r4;
        this.A02 = (byte[]) r4.A04(65536, byte[].class);
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() throws IOException {
        int i = this.A00;
        if (i > 0) {
            this.A03.write(this.A02, 0, i);
            this.A00 = 0;
        }
        this.A03.flush();
    }

    /* JADX INFO: finally extract failed */
    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        try {
            flush();
            this.A03.close();
            byte[] bArr = this.A02;
            if (bArr != null) {
                this.A01.A05(bArr);
                this.A02 = null;
            }
        } catch (Throwable th) {
            this.A03.close();
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        byte[] bArr = this.A02;
        int i2 = this.A00;
        int i3 = i2 + 1;
        this.A00 = i3;
        bArr[i2] = (byte) i;
        if (i3 == bArr.length && i3 > 0) {
            this.A03.write(bArr, 0, i3);
            this.A00 = 0;
        }
    }

    @Override // java.io.OutputStream
    public final void write(@NonNull byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(@NonNull byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        do {
            int i4 = i2 - i3;
            int i5 = i + i3;
            int i6 = this.A00;
            if (i6 != 0 || i4 < this.A02.length) {
                byte[] bArr2 = this.A02;
                int min = Math.min(i4, bArr2.length - i6);
                System.arraycopy(bArr, i5, bArr2, i6, min);
                int i7 = this.A00 + min;
                this.A00 = i7;
                i3 += min;
                byte[] bArr3 = this.A02;
                if (i7 == bArr3.length && i7 > 0) {
                    this.A03.write(bArr3, 0, i7);
                    this.A00 = 0;
                    continue;
                }
            } else {
                this.A03.write(bArr, i5, i4);
                return;
            }
        } while (i3 < i2);
    }
}
