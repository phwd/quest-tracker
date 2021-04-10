package com.facebook.common.procread;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* access modifiers changed from: package-private */
@ThreadSafe
public interface IProcReader {
    boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr);

    boolean readProcFile(String str, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr);

    boolean readProcLines(String str, String[] strArr, long[] jArr);
}
