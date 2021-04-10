package okhttp3.internal.http2;

import com.facebook.breakpad.BreakpadManager;
import com.oculus.common.build.BuildConfig;
import com.oculus.panellib.Qpl;
import java.io.IOException;
import okhttp3.internal.Util;
import okio.ByteString;

public final class Http2 {
    static final String[] BINARY = new String[256];
    static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    static final String[] FLAGS = new String[64];
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    static {
        for (int i = 0; i < BINARY.length; i++) {
            BINARY[i] = Util.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        FLAGS[0] = BuildConfig.PROVIDER_SUFFIX;
        FLAGS[1] = "END_STREAM";
        int[] prefixFlags = {1};
        FLAGS[8] = "PADDED";
        for (int prefixFlag : prefixFlags) {
            FLAGS[prefixFlag | 8] = FLAGS[prefixFlag] + "|PADDED";
        }
        FLAGS[4] = "END_HEADERS";
        FLAGS[32] = "PRIORITY";
        FLAGS[36] = "END_HEADERS|PRIORITY";
        int[] frameFlags = {4, 32, 36};
        for (int frameFlag : frameFlags) {
            for (int prefixFlag2 : prefixFlags) {
                FLAGS[prefixFlag2 | frameFlag] = FLAGS[prefixFlag2] + '|' + FLAGS[frameFlag];
                FLAGS[prefixFlag2 | frameFlag | 8] = FLAGS[prefixFlag2] + '|' + FLAGS[frameFlag] + "|PADDED";
            }
        }
        for (int i2 = 0; i2 < FLAGS.length; i2++) {
            if (FLAGS[i2] == null) {
                FLAGS[i2] = BINARY[i2];
            }
        }
    }

    private Http2() {
    }

    static IllegalArgumentException illegalArgument(String message, Object... args) {
        throw new IllegalArgumentException(Util.format(message, args));
    }

    static IOException ioException(String message, Object... args) throws IOException {
        throw new IOException(Util.format(message, args));
    }

    static String frameLog(boolean inbound, int streamId, int length, byte type, byte flags) {
        String formattedType = type < FRAME_NAMES.length ? FRAME_NAMES[type] : Util.format("0x%02x", Byte.valueOf(type));
        String formattedFlags = formatFlags(type, flags);
        Object[] objArr = new Object[5];
        objArr[0] = inbound ? "<<" : ">>";
        objArr[1] = Integer.valueOf(streamId);
        objArr[2] = Integer.valueOf(length);
        objArr[3] = formattedType;
        objArr[4] = formattedFlags;
        return Util.format("%s 0x%08x %5d %-13s %s", objArr);
    }

    static String formatFlags(byte type, byte flags) {
        if (flags == 0) {
            return BuildConfig.PROVIDER_SUFFIX;
        }
        switch (type) {
            case Qpl.ACTION_SUCCESS:
            case 3:
            case BreakpadManager.SIGBUS:
            case BreakpadManager.SIGFPE:
                return BINARY[flags];
            case BreakpadManager.SIGILL:
            case BreakpadManager.SIGABRT:
                return flags == 1 ? "ACK" : BINARY[flags];
            case BreakpadManager.SIGTRAP:
            default:
                String result = flags < FLAGS.length ? FLAGS[flags] : BINARY[flags];
                if (type == 5 && (flags & 4) != 0) {
                    return result.replace("HEADERS", "PUSH_PROMISE");
                }
                if (type != 0 || (flags & 32) == 0) {
                    return result;
                }
                return result.replace("PRIORITY", "COMPRESSED");
        }
    }
}
