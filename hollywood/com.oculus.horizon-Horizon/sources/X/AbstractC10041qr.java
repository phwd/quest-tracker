package X;

import com.facebook.infer.annotation.Nullsafe;
import java.io.UnsupportedEncodingException;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qr  reason: invalid class name and case insensitive filesystem */
public interface AbstractC10041qr {
    public static final int A01 = A03.length;
    public static final byte[] A02;
    public static final byte[] A03 = {73, 73, 42, 0};
    public static final byte[] A04 = {77, 77, 0, 42};
    public static final byte[] A05 = A01("GIF87a");
    public static final byte[] A06 = A01("GIF89a");
    public static final byte[] A07 = A01("ftyp");
    public static final byte[] A08 = {0, 0, 1, 0};
    public static final byte[] A09 = {-1, -40, -1};
    public static final byte[] A0A = {-119, 80, 78, 71, 13, 10, 26, 10};
    public static final byte[][] A0B = {A01("heic"), A01("heix"), A01("hevc"), A01("hevx"), A01("mif1"), A01("msf1")};
    public static final int A0C;
    public final int A00;

    static {
        byte[] A012 = A01("BM");
        A02 = A012;
        A0C = A012.length;
    }

    static default boolean A00(byte[] bArr, byte[] bArr2, int i) {
        if (bArr2 != null) {
            int length = bArr2.length;
            if (length + i <= bArr.length) {
                for (int i2 = 0; i2 < length; i2++) {
                    if (bArr[i + i2] == bArr2[i2]) {
                    }
                }
                return true;
            }
            return false;
        }
        throw null;
    }

    static default byte[] A01(String str) {
        if (str != null) {
            try {
                return str.getBytes("ASCII");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("ASCII not found!", e);
            }
        } else {
            throw null;
        }
    }

    default AbstractC10041qr() {
        int[] iArr = {21, 20, 3, 8, 6, A0C, 4, 12};
        int i = 1;
        AnonymousClass0KU.A01(true);
        int i2 = iArr[0];
        do {
            i2 = iArr[i] > i2 ? iArr[i] : i2;
            i++;
        } while (i < 8);
        this.A00 = i2;
    }
}
