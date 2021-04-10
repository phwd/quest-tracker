package X;

import java.io.IOException;
import java.util.Locale;

/* renamed from: X.0hX  reason: invalid class name and case insensitive filesystem */
public final class C04810hX {
    public static final C04610h7 A00 = C04610h7.A04("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    public static final String[] A01;
    public static final String[] A02;
    public static final String[] A03 = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    static {
        String[] strArr;
        String[] strArr2 = new String[64];
        A02 = strArr2;
        String[] strArr3 = new String[256];
        A01 = strArr3;
        for (int i = 0; i < 256; i++) {
            strArr3[i] = String.format(Locale.US, "%8s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i2 = 0; i2 < 1; i2++) {
            int i3 = iArr[i2];
            strArr2[i3 | 8] = AnonymousClass006.A05(strArr2[i3], "|PADDED");
        }
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        int i4 = 0;
        do {
            int i5 = iArr2[i4];
            int i6 = iArr[0];
            strArr = A02;
            int i7 = i6 | i5;
            strArr[i7] = AnonymousClass006.A00(strArr[i6], '|', strArr[i5]);
            strArr[i7 | 8] = strArr[i6] + '|' + strArr[i5] + "|PADDED";
            i4++;
        } while (i4 < 3);
        for (int i8 = 0; i8 < strArr.length; i8++) {
            if (strArr[i8] == null) {
                strArr[i8] = A01[i8];
            }
        }
    }

    public static String A00(boolean z, int i, int i2, byte b, byte b2) {
        String format;
        String str;
        String str2;
        String str3;
        String str4;
        String[] strArr = A03;
        if (b < strArr.length) {
            format = strArr[b];
        } else {
            format = String.format(Locale.US, "0x%02x", Byte.valueOf(b));
        }
        if (b2 == 0) {
            str = "";
        } else {
            if (!(b == 2 || b == 3)) {
                if (b == 4 || b == 6) {
                    if (b2 == 1) {
                        str = "ACK";
                    }
                } else if (!(b == 7 || b == 8)) {
                    String[] strArr2 = A02;
                    if (b2 >= strArr2.length) {
                        strArr2 = A01;
                    }
                    str = strArr2[b2];
                    if (b == 5) {
                        if ((b2 & 4) != 0) {
                            str2 = "HEADERS";
                            str3 = "PUSH_PROMISE";
                        }
                    } else if (b == 0 && (b2 & 32) != 0) {
                        str2 = "PRIORITY";
                        str3 = "COMPRESSED";
                    }
                    str = str.replace(str2, str3);
                }
            }
            str = A01[b2];
        }
        if (z) {
            str4 = "<<";
        } else {
            str4 = ">>";
        }
        return String.format(Locale.US, "%s 0x%08x %5d %-13s %s", str4, Integer.valueOf(i), Integer.valueOf(i2), format, str);
    }

    public static void A01(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(Locale.US, str, objArr));
    }
}
