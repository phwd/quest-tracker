package android.icu.text;

import android.icu.impl.Normalizer2Impl;

public final class UnicodeCompressor implements SCSU {
    private static boolean[] sSingleTagTable = {false, true, true, true, true, true, true, true, true, false, false, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private static boolean[] sUnicodeTagTable = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private int fCurrentWindow = 0;
    private int[] fIndexCount = new int[256];
    private int fMode = 0;
    private int[] fOffsets = new int[8];
    private int fTimeStamp = 0;
    private int[] fTimeStamps = new int[8];

    public UnicodeCompressor() {
        reset();
    }

    public static byte[] compress(String buffer) {
        return compress(buffer.toCharArray(), 0, buffer.length());
    }

    public static byte[] compress(char[] buffer, int start, int limit) {
        UnicodeCompressor comp = new UnicodeCompressor();
        int len = Math.max(4, ((limit - start) * 3) + 1);
        byte[] temp = new byte[len];
        int byteCount = comp.compress(buffer, start, limit, null, temp, 0, len);
        byte[] result = new byte[byteCount];
        System.arraycopy(temp, 0, result, 0, byteCount);
        return result;
    }

    /* JADX INFO: Multiple debug info for r5v25 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r11v1 int: [D('hiByte' int), D('loByte' int)] */
    /* JADX INFO: Multiple debug info for r5v36 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r5v43 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r5v49 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r5v59 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r11v3 int: [D('hiByte' int), D('loByte' int)] */
    /* JADX INFO: Multiple debug info for r5v64 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r5v75 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r10v5 int: [D('whichWindow' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r5v113 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r5v126 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r5v132 int: [D('ucPos' int), D('whichWindow' int)] */
    /* JADX INFO: Multiple debug info for r10v8 int: [D('whichWindow' int), D('bytePos' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0236, code lost:
        if ((r4 + 3) < r27) goto L_0x023c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0238, code lost:
        r5 = -1 + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x023c, code lost:
        r5 = r4 + 1;
        r25[r4] = 15;
        r4 = r6 >>> '\b';
        r11 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0248, code lost:
        if (android.icu.text.UnicodeCompressor.sUnicodeTagTable[r4] == false) goto L_0x0251;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x024a, code lost:
        r12 = r5 + 1;
        r25[r5] = -16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0251, code lost:
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0252, code lost:
        r5 = r12 + 1;
        r25[r12] = (byte) r4;
        r12 = r5 + 1;
        r25[r5] = (byte) r11;
        r20.fMode = 1;
        r5 = r14;
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0334, code lost:
        if ((r4 + 3) < r27) goto L_0x033a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0336, code lost:
        r5 = -1 + r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x033a, code lost:
        r5 = r4 + 1;
        r25[r4] = 15;
        r4 = r6 >>> '\b';
        r11 = r6 & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0346, code lost:
        if (android.icu.text.UnicodeCompressor.sUnicodeTagTable[r4] == false) goto L_0x034f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0348, code lost:
        r12 = r5 + 1;
        r25[r5] = -16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x034f, code lost:
        r12 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0350, code lost:
        r5 = r12 + 1;
        r25[r12] = (byte) r4;
        r12 = r5 + 1;
        r25[r5] = (byte) r11;
        r20.fMode = 1;
        r5 = r14;
        r4 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x014f, code lost:
        if ((r4 + 2) < r27) goto L_0x0155;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0151, code lost:
        r5 = r14 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0155, code lost:
        r5 = getLRDefinedWindow();
        r10 = r4 + 1;
        r25[r4] = (byte) (r5 + 232);
        r4 = r10 + 1;
        r25[r10] = (byte) r7;
        r10 = r4 + 1;
        r25[r4] = (byte) ((r6 - android.icu.text.UnicodeCompressor.sOffsetTable[r7]) + 128);
        r20.fOffsets[r5] = android.icu.text.UnicodeCompressor.sOffsetTable[r7];
        r20.fCurrentWindow = r5;
        r4 = r20.fTimeStamps;
        r13 = r20.fTimeStamp + 1;
        r20.fTimeStamp = r13;
        r4[r5] = r13;
        r20.fMode = 0;
        r4 = r10;
        r5 = r14;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compress(char[] r21, int r22, int r23, int[] r24, byte[] r25, int r26, int r27) {
        /*
        // Method dump skipped, instructions count: 957
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.UnicodeCompressor.compress(char[], int, int, int[], byte[], int, int):int");
    }

    public void reset() {
        int[] iArr = this.fOffsets;
        iArr[0] = 128;
        iArr[1] = 192;
        iArr[2] = 1024;
        iArr[3] = 1536;
        iArr[4] = 2304;
        iArr[5] = 12352;
        iArr[6] = 12448;
        iArr[7] = 65280;
        for (int i = 0; i < 8; i++) {
            this.fTimeStamps[i] = 0;
        }
        for (int i2 = 0; i2 <= 255; i2++) {
            this.fIndexCount[i2] = 0;
        }
        this.fTimeStamp = 0;
        this.fCurrentWindow = 0;
        this.fMode = 0;
    }

    private static int makeIndex(int c) {
        if (c >= 192 && c < 320) {
            return 249;
        }
        if (c >= 592 && c < 720) {
            return 250;
        }
        if (c >= 880 && c < 1008) {
            return 251;
        }
        if (c >= 1328 && c < 1424) {
            return 252;
        }
        if (c >= 12352 && c < 12448) {
            return 253;
        }
        if (c >= 12448 && c < 12576) {
            return 254;
        }
        if (c >= 65376 && c < 65439) {
            return 255;
        }
        if (c >= 128 && c < 13312) {
            return (c / 128) & 255;
        }
        if (c < 57344 || c > 65535) {
            return 0;
        }
        return ((c - Normalizer2Impl.Hangul.HANGUL_BASE) / 128) & 255;
    }

    private boolean inDynamicWindow(int c, int whichWindow) {
        int[] iArr = this.fOffsets;
        return c >= iArr[whichWindow] && c < iArr[whichWindow] + 128;
    }

    private static boolean inStaticWindow(int c, int whichWindow) {
        return c >= sOffsets[whichWindow] && c < sOffsets[whichWindow] + 128;
    }

    private static boolean isCompressible(int c) {
        return c < 13312 || c >= 57344;
    }

    private int findDynamicWindow(int c) {
        for (int i = 7; i >= 0; i--) {
            if (inDynamicWindow(c, i)) {
                int[] iArr = this.fTimeStamps;
                iArr[i] = iArr[i] + 1;
                return i;
            }
        }
        return -1;
    }

    private static int findStaticWindow(int c) {
        for (int i = 7; i >= 0; i--) {
            if (inStaticWindow(c, i)) {
                return i;
            }
        }
        return -1;
    }

    private int getLRDefinedWindow() {
        int leastRU = Integer.MAX_VALUE;
        int whichWindow = -1;
        for (int i = 7; i >= 0; i--) {
            int[] iArr = this.fTimeStamps;
            if (iArr[i] < leastRU) {
                leastRU = iArr[i];
                whichWindow = i;
            }
        }
        return whichWindow;
    }
}
