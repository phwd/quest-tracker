package com.facebook.common.procread;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class ProcReader {
    private static final int[] PROC_READ_STRING_ALL_FORMAT = {4096};
    private static IProcReader sProcReader;

    static {
        IProcReader create = OldProcReader.create();
        if (create == null) {
            create = new NewProcReader();
        }
        sProcReader = create;
    }

    public static String readProcFileEntirely(String str) {
        String[] strArr = {null};
        int[] iArr = PROC_READ_STRING_ALL_FORMAT;
        IProcReader iProcReader = sProcReader;
        if (iProcReader != null) {
            iProcReader.readProcFile(str, iArr, strArr, null, null);
        }
        return strArr[0];
    }

    public static boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, String[] strArr, long[] jArr, float[] fArr) {
        IProcReader iProcReader = sProcReader;
        if (iProcReader == null) {
            return false;
        }
        return iProcReader.parseProcLine(bArr, i, i2, iArr, strArr, null, null);
    }
}
