package X;

import androidx.annotation.Nullable;
import com.adobe.xmp.impl.Base64;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/* renamed from: X.1gL  reason: invalid class name */
public final class AnonymousClass1gL {
    public int A00 = 0;
    public AnonymousClass1gT A01;
    public ByteBuffer A02;
    public final byte[] A03 = new byte[256];

    public static int A00(AnonymousClass1gL r1) {
        try {
            return r1.A02.get() & Base64.INVALID;
        } catch (Exception unused) {
            r1.A01.A05 = 1;
            return 0;
        }
    }

    @Nullable
    public static int[] A03(AnonymousClass1gL r10, int i) {
        byte[] bArr = new byte[(i * 3)];
        int[] iArr = null;
        try {
            r10.A02.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                int i4 = i2 + 1;
                int i5 = bArr[i2] & Base64.INVALID;
                int i6 = i4 + 1;
                int i7 = bArr[i4] & Base64.INVALID;
                i2 = i6 + 1;
                iArr[i3] = (i5 << 16) | -16777216 | (i7 << 8) | (bArr[i6] & Base64.INVALID);
            }
        } catch (BufferUnderflowException unused) {
            r10.A01.A05 = 1;
        }
        return iArr;
    }

    public static void A01(AnonymousClass1gL r4) {
        int A002 = A00(r4);
        r4.A00 = A002;
        if (A002 > 0) {
            int i = 0;
            while (true) {
                try {
                    int i2 = r4.A00;
                    if (i < i2) {
                        int i3 = i2 - i;
                        r4.A02.get(r4.A03, i, i3);
                        i += i3;
                    } else {
                        return;
                    }
                } catch (Exception unused) {
                    r4.A01.A05 = 1;
                    return;
                }
            }
        }
    }

    public static void A02(AnonymousClass1gL r3) {
        int A002;
        do {
            A002 = A00(r3);
            r3.A02.position(Math.min(r3.A02.position() + A002, r3.A02.limit()));
        } while (A002 > 0);
    }
}
