package X;

import com.facebook.acra.anr.processmonitor.ProcessAnrErrorMonitor;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: X.Nx  reason: case insensitive filesystem */
public final class C0259Nx extends OutputStream {
    public static final byte[] A04 = new byte[0];
    public int A00;
    public int A01;
    public byte[] A02;
    public final LinkedList A03;

    public final void A01() {
        this.A01 = 0;
        this.A00 = 0;
        LinkedList linkedList = this.A03;
        if (!linkedList.isEmpty()) {
            linkedList.clear();
        }
    }

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

    public final void A02(int i) {
        if (this.A00 >= this.A02.length) {
            A00();
        }
        byte[] bArr = this.A02;
        int i2 = this.A00;
        this.A00 = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public final void A03(int i) {
        int i2 = this.A00;
        int i3 = i2 + 2;
        byte[] bArr = this.A02;
        if (i3 < bArr.length) {
            int i4 = i2 + 1;
            this.A00 = i4;
            bArr[i2] = (byte) (i >> 16);
            int i5 = i4 + 1;
            this.A00 = i5;
            bArr[i4] = (byte) (i >> 8);
            this.A00 = i5 + 1;
            bArr[i5] = (byte) i;
            return;
        }
        A02(i >> 16);
        A02(i >> 8);
        A02(i);
    }

    public final void A04(int i) {
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
        A02(i >> 8);
        A02(i);
    }

    public final byte[] A05() {
        int i = this.A01 + this.A00;
        if (i == 0) {
            return A04;
        }
        byte[] bArr = new byte[i];
        LinkedList linkedList = this.A03;
        Iterator it = linkedList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            byte[] bArr2 = (byte[]) it.next();
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i2, length);
            i2 += length;
        }
        System.arraycopy(this.A02, 0, bArr, i2, this.A00);
        int i3 = i2 + this.A00;
        if (i3 == i) {
            if (!linkedList.isEmpty()) {
                A01();
            }
            return bArr;
        }
        StringBuilder sb = new StringBuilder("Internal error: total len assumed to be ");
        sb.append(i);
        sb.append(", copied ");
        sb.append(i3);
        sb.append(" bytes");
        throw new RuntimeException(sb.toString());
    }

    public C0259Nx() {
        this(ProcessAnrErrorMonitor.DEFAULT_POLLING_TIME_MS);
    }

    public C0259Nx(int i) {
        this.A03 = new LinkedList();
        this.A02 = new byte[i];
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        A02(i);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
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
}
