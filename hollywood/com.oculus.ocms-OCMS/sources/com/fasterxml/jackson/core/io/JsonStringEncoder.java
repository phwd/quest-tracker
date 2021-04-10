package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

public final class JsonStringEncoder {
    private static final byte[] HEX_BYTES = CharTypes.copyHexBytes();
    private static final char[] HEX_CHARS = CharTypes.copyHexChars();
    private static final int INT_0 = 48;
    private static final int INT_BACKSLASH = 92;
    private static final int INT_U = 117;
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();
    protected ByteArrayBuilder _byteBuilder;
    protected final char[] _quoteBuffer = new char[6];
    protected TextBuffer _textBuffer;

    public JsonStringEncoder() {
        char[] cArr = this._quoteBuffer;
        cArr[0] = '\\';
        cArr[2] = '0';
        cArr[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        JsonStringEncoder jsonStringEncoder;
        SoftReference<JsonStringEncoder> softReference = _threadEncoder.get();
        if (softReference == null) {
            jsonStringEncoder = null;
        } else {
            jsonStringEncoder = softReference.get();
        }
        if (jsonStringEncoder != null) {
            return jsonStringEncoder;
        }
        JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference<>(jsonStringEncoder2));
        return jsonStringEncoder2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r9 >= 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        r1 = _appendNumericEscape(r1, r11._quoteBuffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        r1 = _appendNamedEscape(r9, r11._quoteBuffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0040, code lost:
        r9 = r7 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        if (r9 <= r6.length) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        r9 = r6.length - r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        if (r9 <= 0) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r6, r7, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        r6 = r0.finishCurrentSegment();
        r1 = r1 - r9;
        java.lang.System.arraycopy(r11._quoteBuffer, r9, r6, 0, r1);
        r7 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005a, code lost:
        java.lang.System.arraycopy(r11._quoteBuffer, 0, r6, r7, r1);
        r7 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0029, code lost:
        r8 = r1 + 1;
        r1 = r12.charAt(r1);
        r9 = r2[r1];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 126
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        if (r5 < r4.length) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0044, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0049, code lost:
        r7 = r2 + 1;
        r2 = r11.charAt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        if (r2 > 127) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0051, code lost:
        r5 = _appendByteEscape(r2, r6[r2], r0, r5);
        r4 = r0.getCurrentSegment();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005f, code lost:
        if (r2 > 2047) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0061, code lost:
        r4[r5] = (byte) ((r2 >> 6) | 192);
        r2 = (r2 & '?') | 128;
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0074, code lost:
        if (r2 < com.fasterxml.jackson.core.io.JsonStringEncoder.SURR1_FIRST) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0079, code lost:
        if (r2 <= com.fasterxml.jackson.core.io.JsonStringEncoder.SURR2_LAST) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007f, code lost:
        if (r2 <= com.fasterxml.jackson.core.io.JsonStringEncoder.SURR1_LAST) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
        _illegalSurrogate(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0084, code lost:
        if (r7 < r1) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0086, code lost:
        _illegalSurrogate(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0089, code lost:
        r6 = r7 + 1;
        r2 = _convertSurrogate(r2, r11.charAt(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0096, code lost:
        if (r2 <= 1114111) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0098, code lost:
        _illegalSurrogate(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009b, code lost:
        r7 = r5 + 1;
        r4[r5] = (byte) ((r2 >> 18) | 240);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a5, code lost:
        if (r7 < r4.length) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a7, code lost:
        r4 = r0.finishCurrentSegment();
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ac, code lost:
        r5 = r7 + 1;
        r4[r7] = (byte) (((r2 >> 12) & 63) | 128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b8, code lost:
        if (r5 < r4.length) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ba, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bf, code lost:
        r4[r5] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & 63) | 128;
        r5 = r5 + 1;
        r7 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d1, code lost:
        r6 = r5 + 1;
        r4[r5] = (byte) ((r2 >> '\f') | 224);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00db, code lost:
        if (r6 < r4.length) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00dd, code lost:
        r4 = r0.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e2, code lost:
        r5 = r6 + 1;
        r4[r6] = (byte) (((r2 >> 6) & 63) | 128);
        r2 = (r2 & '?') | 128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f2, code lost:
        if (r5 < r4.length) goto L_0x00f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f4, code lost:
        r4 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f9, code lost:
        r4[r5] = (byte) r2;
        r5 = r5 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] quoteAsUTF8(java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 264
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    public byte[] encodeAsUTF8(String str) {
        int i;
        char c;
        ByteArrayBuilder byteArrayBuilder = this._byteBuilder;
        if (byteArrayBuilder == null) {
            byteArrayBuilder = new ByteArrayBuilder((BufferRecycler) null);
            this._byteBuilder = byteArrayBuilder;
        }
        int length = str.length();
        byte[] resetAndGetFirstSegment = byteArrayBuilder.resetAndGetFirstSegment();
        int length2 = resetAndGetFirstSegment.length;
        byte[] bArr = resetAndGetFirstSegment;
        int i2 = 0;
        int i3 = 0;
        loop0:
        while (true) {
            if (i2 >= length) {
                break;
            }
            int i4 = i2 + 1;
            char c2 = str.charAt(i2);
            while (c2 <= 127) {
                if (i3 >= length2) {
                    byte[] finishCurrentSegment = byteArrayBuilder.finishCurrentSegment();
                    i3 = 0;
                    bArr = finishCurrentSegment;
                    length2 = finishCurrentSegment.length;
                }
                int i5 = i3 + 1;
                bArr[i3] = (byte) c2;
                if (i4 >= length) {
                    i3 = i5;
                    break loop0;
                }
                char charAt = str.charAt(i4);
                i4++;
                c2 = charAt;
                i3 = i5;
            }
            if (i3 >= length2) {
                bArr = byteArrayBuilder.finishCurrentSegment();
                length2 = bArr.length;
                i3 = 0;
            }
            if (c2 < 2048) {
                bArr[i3] = (byte) ((c2 >> 6) | 192);
                i = i3 + 1;
                c = c2;
            } else if (c2 < SURR1_FIRST || c2 > SURR2_LAST) {
                int i6 = i3 + 1;
                bArr[i3] = (byte) ((c2 >> '\f') | 224);
                if (i6 >= length2) {
                    byte[] finishCurrentSegment2 = byteArrayBuilder.finishCurrentSegment();
                    i6 = 0;
                    bArr = finishCurrentSegment2;
                    length2 = finishCurrentSegment2.length;
                }
                i = i6 + 1;
                bArr[i6] = (byte) (((c2 >> 6) & 63) | 128);
                c = c2;
            } else {
                if (c2 > SURR1_LAST) {
                    _illegalSurrogate(c2);
                }
                if (i4 >= length) {
                    _illegalSurrogate(c2);
                }
                int i7 = i4 + 1;
                int _convertSurrogate = _convertSurrogate(c2, str.charAt(i4));
                if (_convertSurrogate > 1114111) {
                    _illegalSurrogate(_convertSurrogate);
                }
                int i8 = i3 + 1;
                bArr[i3] = (byte) ((_convertSurrogate >> 18) | 240);
                if (i8 >= length2) {
                    bArr = byteArrayBuilder.finishCurrentSegment();
                    length2 = bArr.length;
                    i8 = 0;
                }
                int i9 = i8 + 1;
                bArr[i8] = (byte) (((_convertSurrogate >> 12) & 63) | 128);
                if (i9 >= length2) {
                    byte[] finishCurrentSegment3 = byteArrayBuilder.finishCurrentSegment();
                    i9 = 0;
                    bArr = finishCurrentSegment3;
                    length2 = finishCurrentSegment3.length;
                }
                bArr[i9] = (byte) (((_convertSurrogate >> 6) & 63) | 128);
                i = i9 + 1;
                i4 = i7;
                c = _convertSurrogate;
            }
            if (i >= length2) {
                byte[] finishCurrentSegment4 = byteArrayBuilder.finishCurrentSegment();
                i = 0;
                bArr = finishCurrentSegment4;
                length2 = finishCurrentSegment4.length;
            }
            bArr[i] = (byte) (((c == 1 ? 1 : 0) & 63) | 128);
            i2 = i4;
            i3 = i + 1;
        }
        return this._byteBuilder.completeAndCoalesce(i3);
    }

    private int _appendNumericEscape(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HEX_CHARS;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private int _appendNamedEscape(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByteEscape(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HEX_BYTES[i4 >> 4]);
                byteArrayBuilder.append(HEX_BYTES[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HEX_BYTES[i >> 4]);
            byteArrayBuilder.append(HEX_BYTES[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    protected static int _convertSurrogate(int i, int i2) {
        if (i2 >= SURR2_FIRST && i2 <= SURR2_LAST) {
            return ((i - SURR1_FIRST) << 10) + 65536 + (i2 - SURR2_FIRST);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    protected static void _illegalSurrogate(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }
}
