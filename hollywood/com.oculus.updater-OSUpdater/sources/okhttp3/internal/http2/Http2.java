package okhttp3.internal.http2;

import com.oculus.common.build.BuildConfig;
import java.io.IOException;
import okhttp3.internal.Util;
import okio.ByteString;

public final class Http2 {
    static final String[] BINARY = new String[256];
    static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    static final String[] FLAGS = new String[64];
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    static {
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = BINARY;
            if (i2 >= strArr.length) {
                break;
            }
            strArr[i2] = Util.format("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
            i2++;
        }
        String[] strArr2 = FLAGS;
        strArr2[0] = BuildConfig.PROVIDER_SUFFIX;
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i3 : iArr) {
            FLAGS[i3 | 8] = FLAGS[i3] + "|PADDED";
        }
        String[] strArr3 = FLAGS;
        strArr3[4] = "END_HEADERS";
        strArr3[32] = "PRIORITY";
        strArr3[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i4 : iArr2) {
            for (int i5 : iArr) {
                int i6 = i5 | i4;
                FLAGS[i6] = FLAGS[i5] + '|' + FLAGS[i4];
                FLAGS[i6 | 8] = FLAGS[i5] + '|' + FLAGS[i4] + "|PADDED";
            }
        }
        while (true) {
            String[] strArr4 = FLAGS;
            if (i < strArr4.length) {
                if (strArr4[i] == null) {
                    strArr4[i] = BINARY[i];
                }
                i++;
            } else {
                return;
            }
        }
    }

    private Http2() {
    }

    static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(Util.format(str, objArr));
    }

    static IOException ioException(String str, Object... objArr) throws IOException {
        throw new IOException(Util.format(str, objArr));
    }

    static String frameLog(boolean z, int i, int i2, byte b, byte b2) {
        String[] strArr = FRAME_NAMES;
        String format = b < strArr.length ? strArr[b] : Util.format("0x%02x", Byte.valueOf(b));
        String formatFlags = formatFlags(b, b2);
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = format;
        objArr[4] = formatFlags;
        return Util.format("%s 0x%08x %5d %-13s %s", objArr);
    }

    static String formatFlags(byte b, byte b2) {
        if (b2 == 0) {
            return BuildConfig.PROVIDER_SUFFIX;
        }
        if (!(b == 2 || b == 3)) {
            if (b == 4 || b == 6) {
                return b2 == 1 ? "ACK" : BINARY[b2];
            }
            if (!(b == 7 || b == 8)) {
                String[] strArr = FLAGS;
                String str = b2 < strArr.length ? strArr[b2] : BINARY[b2];
                if (b == 5 && (b2 & 4) != 0) {
                    return str.replace("HEADERS", "PUSH_PROMISE");
                }
                if (b != 0 || (b2 & 32) == 0) {
                    return str;
                }
                return str.replace("PRIORITY", "COMPRESSED");
            }
        }
        return BINARY[b2];
    }
}
