package X;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: X.1hv  reason: invalid class name */
public class AnonymousClass1hv {
    public final File A00;
    public final int[] A01;
    public final int[] A02;

    public AnonymousClass1hv(File file, byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.BIG_ENDIAN);
        int i = wrap.getInt();
        int[] iArr = new int[i];
        int i2 = i << 1;
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = wrap.getInt();
        }
        for (int i4 = 0; i4 < i2; i4++) {
            iArr2[i4] = wrap.getInt();
        }
        this.A00 = file;
        this.A01 = iArr;
        this.A02 = iArr2;
    }
}
