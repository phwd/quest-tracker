package X;

import java.io.OutputStream;
import java.util.LinkedList;

/* renamed from: X.0mJ  reason: invalid class name and case insensitive filesystem */
public final class C06190mJ extends OutputStream {
    public static final byte[] A04 = new byte[0];
    public int A00;
    public int A01;
    public byte[] A02;
    public final LinkedList<byte[]> A03;

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public final void flush() {
    }

    private void A00() {
        int i = this.A01;
        byte[] bArr = this.A02;
        int length = i + bArr.length;
        this.A01 = length;
        int max = Math.max(length >> 1, 1000);
        if (max > 262144) {
            max = 262144;
        }
        this.A03.add(bArr);
        this.A02 = new byte[max];
        this.A00 = 0;
    }

    public final void A01(int i) {
        if (this.A00 >= this.A02.length) {
            A00();
        }
        byte[] bArr = this.A02;
        int i2 = this.A00;
        this.A00 = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public final void A02(int i) {
        int i2 = this.A00;
        int i3 = i2 + 1;
        byte[] bArr = this.A02;
        if (i3 < bArr.length) {
            this.A00 = i3;
            bArr[i2] = (byte) (i >> 8);
            this.A00 = i3 + 1;
            bArr[i3] = (byte) i;
            return;
        }
        A01(i >> 8);
        A01(i);
    }

    public C06190mJ() {
        this(500);
    }

    public C06190mJ(int i) {
        this.A03 = new LinkedList<>();
        this.A02 = new byte[i];
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        while (true) {
            byte[] bArr2 = this.A02;
            int length = bArr2.length;
            int i3 = this.A00;
            int min = Math.min(length - i3, i2);
            if (min > 0) {
                System.arraycopy(bArr, i, bArr2, i3, min);
                i += min;
                this.A00 += min;
                i2 -= min;
            }
            if (i2 > 0) {
                A00();
            } else {
                return;
            }
        }
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        A01(i);
    }
}
