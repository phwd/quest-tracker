package com.facebook.flatbuffers;

import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.dataformat.smile.SmileConstants;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.nio.ByteBuffer;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Utf8Decoder {
    private static final boolean CHECK_ERRORS = true;
    public static final int ERROR_INVALID_CODE_POINT = 1114113;
    public static final int ERROR_INVALID_FIRST_BYTE = 1114114;
    private static final int ERROR_LAST = 1114117;
    public static final int ERROR_OVERFLOW = 1114115;
    public static final int ERROR_OVERLONG = 1114116;
    public static final int ERROR_SUCCESS = 1114112;
    public static final int ERROR_UNDERFLOW = 1114117;

    public static boolean isError(int i) {
        return i > 1114112 && i <= 1114117;
    }

    private static int minUtf8ByteCount(int i) {
        if (i < 128) {
            return 1;
        }
        if (i < 2048) {
            return 2;
        }
        return i < 65536 ? 3 : 4;
    }

    Utf8Decoder() {
    }

    public static boolean isSuccessOrData(int i) {
        return !isError(i);
    }

    public static String getErrorName(int i) {
        switch (i) {
            case 1114112:
                return "ERROR_SUCCESS";
            case 1114113:
                return "ERROR_INVALID_CODE_POINT";
            case ERROR_INVALID_FIRST_BYTE /*{ENCODED_INT: 1114114}*/:
                return "ERROR_INVALID_FIRST_BYTE";
            case ERROR_OVERFLOW /*{ENCODED_INT: 1114115}*/:
                return "ERROR_OVERFLOW";
            case ERROR_OVERLONG /*{ENCODED_INT: 1114116}*/:
                return "ERROR_OVERLONG";
            case 1114117:
                return "ERROR_UNDERFLOW";
            default:
                throw new IllegalArgumentException("Unknown error code: " + i);
        }
    }

    public static int decode(ByteBuffer byteBuffer, Appendable appendable) {
        byte b;
        int i;
        byte b2;
        byte[] array = byteBuffer.hasArray() ? byteBuffer.array() : null;
        int limit = byteBuffer.limit();
        for (int position = byteBuffer.position(); position < limit; position += i) {
            if (array != null) {
                b = array[position];
            } else {
                b = byteBuffer.get();
            }
            if ((b & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                i = 1;
            } else if ((b & SmileConstants.TOKEN_MISC_LONG_TEXT_ASCII) == 192) {
                i = 2;
            } else if ((b & 240) == 224) {
                i = 3;
            } else if ((b & SmileConstants.TOKEN_LITERAL_START_ARRAY) != 240) {
                return ERROR_INVALID_FIRST_BYTE;
            } else {
                i = 4;
            }
            if (i - 1 > byteBuffer.remaining()) {
                return 1114117;
            }
            int i2 = b & (127 >>> (7 - (i == 1 ? 7 : 7 - i)));
            for (int i3 = 1; i3 < i; i3++) {
                if (array != null) {
                    b2 = array[position + i3];
                } else {
                    b2 = byteBuffer.get();
                }
                if ((b2 & 192) != 128) {
                    return 1114113;
                }
                i2 = (i2 << 6) | (b2 & 63);
            }
            if (i2 > 1114111) {
                return 1114113;
            }
            if (i > minUtf8ByteCount(i2)) {
                return ERROR_OVERLONG;
            }
            int encodeCodePoint = encodeCodePoint(i2, appendable);
            if (isError(encodeCodePoint)) {
                return encodeCodePoint;
            }
        }
        return 1114112;
    }

    private static int encodeCodePoint(int i, Appendable appendable) {
        if (i <= 65535) {
            try {
                appendable.append((char) i);
                return 1114112;
            } catch (IOException unused) {
                return ERROR_OVERFLOW;
            }
        } else {
            int i2 = i & 65535;
            appendable.append((char) ((((((i >>> 16) & 31) - 1) & 65535) << 6) | 55296 | (i2 >> 10)));
            appendable.append((char) (56320 | (i2 & 1023)));
            return 1114112;
        }
    }
}
