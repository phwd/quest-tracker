package com.facebook.common.procread;

/* access modifiers changed from: package-private */
public interface IProcReader {
    boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, String[] strArr, long[] jArr, float[] fArr);

    boolean readProcFile(String str, int[] iArr, String[] strArr, long[] jArr, float[] fArr);
}
