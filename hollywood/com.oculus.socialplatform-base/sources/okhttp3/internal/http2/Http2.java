package okhttp3.internal.http2;

import X.AnonymousClass006;
import java.io.IOException;
import java.util.Locale;
import okio.ByteString;

public final class Http2 {
    public static final String[] BINARY;
    public static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    public static final String[] FLAGS;
    public static final byte FLAG_ACK = 1;
    public static final byte FLAG_COMPRESSED = 32;
    public static final byte FLAG_END_HEADERS = 4;
    public static final byte FLAG_END_PUSH_PROMISE = 4;
    public static final byte FLAG_END_STREAM = 1;
    public static final byte FLAG_NONE = 0;
    public static final byte FLAG_PADDED = 8;
    public static final byte FLAG_PRIORITY = 32;
    public static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
    public static final int INITIAL_MAX_FRAME_SIZE = 16384;
    public static final byte TYPE_CONTINUATION = 9;
    public static final byte TYPE_DATA = 0;
    public static final byte TYPE_GOAWAY = 7;
    public static final byte TYPE_HEADERS = 1;
    public static final byte TYPE_PING = 6;
    public static final byte TYPE_PRIORITY = 2;
    public static final byte TYPE_PUSH_PROMISE = 5;
    public static final byte TYPE_RST_STREAM = 3;
    public static final byte TYPE_SETTINGS = 4;
    public static final byte TYPE_WINDOW_UPDATE = 8;

    static {
        String[] strArr;
        String[] strArr2 = new String[64];
        FLAGS = strArr2;
        String[] strArr3 = new String[256];
        BINARY = strArr3;
        for (int i = 0; i < 256; i++) {
            strArr3[i] = String.format(Locale.US, "%8s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i2 = 0; i2 < 1; i2++) {
            int i3 = iArr[i2];
            strArr2[i3 | 8] = AnonymousClass006.A07(strArr2[i3], "|PADDED");
        }
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        int i4 = 0;
        do {
            int i5 = iArr2[i4];
            int i6 = iArr[0];
            strArr = FLAGS;
            int i7 = i6 | i5;
            strArr[i7] = AnonymousClass006.A02(strArr[i6], '|', strArr[i5]);
            StringBuilder sb = new StringBuilder();
            sb.append(strArr[i6]);
            sb.append('|');
            sb.append(strArr[i5]);
            sb.append("|PADDED");
            strArr[i7 | 8] = sb.toString();
            i4++;
        } while (i4 < 3);
        for (int i8 = 0; i8 < strArr.length; i8++) {
            if (strArr[i8] == null) {
                strArr[i8] = BINARY[i8];
            }
        }
    }

    public static String formatFlags(byte b, byte b2) {
        String str;
        String str2;
        if (b2 == 0) {
            return "";
        }
        if (!(b == 2 || b == 3)) {
            if (b == 4 || b == 6) {
                if (b2 == 1) {
                    return "ACK";
                }
            } else if (!(b == 7 || b == 8)) {
                String[] strArr = FLAGS;
                if (b2 >= strArr.length) {
                    strArr = BINARY;
                }
                String str3 = strArr[b2];
                if (b == 5) {
                    if ((b2 & 4) != 0) {
                        str = "HEADERS";
                        str2 = "PUSH_PROMISE";
                    }
                    return str3;
                }
                if (b == 0 && (b2 & 32) != 0) {
                    str = "PRIORITY";
                    str2 = "COMPRESSED";
                }
                return str3;
                return str3.replace(str, str2);
            }
        }
        return BINARY[b2];
    }

    public static String frameLog(boolean z, int i, int i2, byte b, byte b2) {
        String format;
        String str;
        String[] strArr = FRAME_NAMES;
        if (b < strArr.length) {
            format = strArr[b];
        } else {
            format = String.format(Locale.US, "0x%02x", Byte.valueOf(b));
        }
        String formatFlags = formatFlags(b, b2);
        if (z) {
            str = "<<";
        } else {
            str = ">>";
        }
        return String.format(Locale.US, "%s 0x%08x %5d %-13s %s", str, Integer.valueOf(i), Integer.valueOf(i2), format, formatFlags);
    }

    public static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(Locale.US, str, objArr));
    }

    public static IOException ioException(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(Locale.US, str, objArr));
    }
}
