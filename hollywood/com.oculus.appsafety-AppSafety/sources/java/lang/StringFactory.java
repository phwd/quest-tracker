package java.lang;

import android.icu.text.UTF16;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import libcore.util.CharsetUtils;
import libcore.util.EmptyArray;

public final class StringFactory {
    private static final char REPLACEMENT_CHAR = 65533;
    private static final int[] TABLE_UTF8_NEEDED = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public static native String newStringFromBytes(byte[] bArr, int i, int i2, int i3);

    static native String newStringFromChars(int i, int i2, char[] cArr);

    public static native String newStringFromString(String str);

    public static String newEmptyString() {
        return newStringFromChars(EmptyArray.CHAR, 0, 0);
    }

    public static String newStringFromBytes(byte[] data) {
        return newStringFromBytes(data, 0, data.length);
    }

    public static String newStringFromBytes(byte[] data, int high) {
        return newStringFromBytes(data, high, 0, data.length);
    }

    public static String newStringFromBytes(byte[] data, int offset, int byteCount) {
        return newStringFromBytes(data, offset, byteCount, Charset.defaultCharset());
    }

    public static String newStringFromBytes(byte[] data, int offset, int byteCount, String charsetName) throws UnsupportedEncodingException {
        return newStringFromBytes(data, offset, byteCount, Charset.forNameUEE(charsetName));
    }

    public static String newStringFromBytes(byte[] data, String charsetName) throws UnsupportedEncodingException {
        return newStringFromBytes(data, 0, data.length, Charset.forNameUEE(charsetName));
    }

    /* JADX INFO: Multiple debug info for r5v17 int: [D('s' int), D('tableLookupIndex' int)] */
    public static String newStringFromBytes(byte[] data, int offset, int byteCount, Charset charset) {
        int length;
        char[] value;
        int s;
        int s2;
        if ((offset | byteCount) < 0 || byteCount > data.length - offset) {
            throw new StringIndexOutOfBoundsException(data.length, offset, byteCount);
        }
        String canonicalCharsetName = charset.name();
        if (canonicalCharsetName.equals("UTF-8")) {
            char[] v = new char[byteCount];
            int b = offset;
            int last = offset + byteCount;
            int s3 = 0;
            int codePoint = 0;
            int utf8BytesNeeded = 0;
            int utf8BytesNeeded2 = 0;
            int lowerBound = 128;
            int upperBound = 191;
            while (b < last) {
                int idx = b + 1;
                int b2 = data[b] & 255;
                if (utf8BytesNeeded2 == 0) {
                    if ((b2 & 128) == 0) {
                        v[s3] = (char) b2;
                        s3++;
                        b = idx;
                    } else if ((b2 & 64) == 0) {
                        v[s3] = REPLACEMENT_CHAR;
                        s3++;
                        b = idx;
                    } else {
                        utf8BytesNeeded2 = TABLE_UTF8_NEEDED[b2 & 63];
                        if (utf8BytesNeeded2 == 0) {
                            v[s3] = REPLACEMENT_CHAR;
                            b = idx;
                            s3++;
                        } else {
                            codePoint = b2 & (63 >> utf8BytesNeeded2);
                            if (b2 == 224) {
                                lowerBound = 160;
                            } else if (b2 == 237) {
                                upperBound = 159;
                            } else if (b2 == 240) {
                                lowerBound = 144;
                            } else if (b2 == 244) {
                                upperBound = 143;
                            }
                        }
                    }
                } else if (b2 < lowerBound || b2 > upperBound) {
                    v[s3] = REPLACEMENT_CHAR;
                    codePoint = 0;
                    utf8BytesNeeded2 = 0;
                    utf8BytesNeeded = 0;
                    lowerBound = 128;
                    upperBound = 191;
                    b = idx - 1;
                    s3++;
                } else {
                    lowerBound = 128;
                    upperBound = 191;
                    codePoint = (codePoint << 6) | (b2 & 63);
                    utf8BytesNeeded++;
                    if (utf8BytesNeeded2 != utf8BytesNeeded) {
                        b = idx;
                    } else {
                        if (codePoint < 65536) {
                            s2 = s3 + 1;
                            v[s3] = (char) codePoint;
                        } else {
                            int s4 = s3 + 1;
                            v[s3] = (char) ((codePoint >> 10) + 55232);
                            v[s4] = (char) ((codePoint & 1023) + UTF16.TRAIL_SURROGATE_MIN_VALUE);
                            s2 = s4 + 1;
                        }
                        codePoint = 0;
                        utf8BytesNeeded2 = 0;
                        utf8BytesNeeded = 0;
                        s3 = s2;
                    }
                }
                b = idx;
            }
            if (utf8BytesNeeded2 != 0) {
                s = s3 + 1;
                v[s3] = REPLACEMENT_CHAR;
            } else {
                s = s3;
            }
            if (s == byteCount) {
                value = v;
                length = s;
            } else {
                value = new char[s];
                length = s;
                System.arraycopy((Object) v, 0, (Object) value, 0, s);
            }
        } else if (canonicalCharsetName.equals("ISO-8859-1")) {
            value = new char[byteCount];
            length = byteCount;
            CharsetUtils.isoLatin1BytesToChars(data, offset, byteCount, value);
        } else if (canonicalCharsetName.equals("US-ASCII")) {
            value = new char[byteCount];
            length = byteCount;
            CharsetUtils.asciiBytesToChars(data, offset, byteCount, value);
        } else {
            CharBuffer cb = charset.decode(ByteBuffer.wrap(data, offset, byteCount));
            length = cb.length();
            value = cb.array();
        }
        return newStringFromChars(value, 0, length);
    }

    public static String newStringFromBytes(byte[] data, Charset charset) {
        return newStringFromBytes(data, 0, data.length, charset);
    }

    public static String newStringFromChars(char[] data) {
        return newStringFromChars(data, 0, data.length);
    }

    public static String newStringFromChars(char[] data, int offset, int charCount) {
        if ((offset | charCount) >= 0 && charCount <= data.length - offset) {
            return newStringFromChars(offset, charCount, data);
        }
        throw new StringIndexOutOfBoundsException(data.length, offset, charCount);
    }

    public static String newStringFromStringBuffer(StringBuffer stringBuffer) {
        String newStringFromChars;
        synchronized (stringBuffer) {
            newStringFromChars = newStringFromChars(stringBuffer.getValue(), 0, stringBuffer.length());
        }
        return newStringFromChars;
    }

    public static String newStringFromCodePoints(int[] codePoints, int offset, int count) {
        if (codePoints == null) {
            throw new NullPointerException("codePoints == null");
        } else if ((offset | count) < 0 || count > codePoints.length - offset) {
            throw new StringIndexOutOfBoundsException(codePoints.length, offset, count);
        } else {
            char[] value = new char[(count * 2)];
            int end = offset + count;
            int length = 0;
            for (int i = offset; i < end; i++) {
                length += Character.toChars(codePoints[i], value, length);
            }
            return newStringFromChars(value, 0, length);
        }
    }

    public static String newStringFromStringBuilder(StringBuilder stringBuilder) {
        return newStringFromChars(stringBuilder.getValue(), 0, stringBuilder.length());
    }
}
