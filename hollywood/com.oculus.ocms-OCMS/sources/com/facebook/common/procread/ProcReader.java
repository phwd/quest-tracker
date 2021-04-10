package com.facebook.common.procread;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ProcReader {
    public static final int PROC_CHAR = 2048;
    public static final int PROC_COMBINE = 256;
    public static final int PROC_OUT_FLOAT = 16384;
    public static final int PROC_OUT_LONG = 8192;
    public static final int PROC_OUT_STRING = 4096;
    public static final int PROC_PARENS = 512;
    public static final int PROC_QUOTES = 1024;
    private static final int[] PROC_READ_STRING_ALL_FORMAT = {4096};
    public static final int PROC_SPACE_TERM = 32;
    public static final int PROC_TERM_MASK = 255;
    public static final int PROC_ZERO_TERM = 0;
    private static final String TAG = "ProcReader";
    private static IProcReader sProcReader;

    static {
        IProcReader create = OldProcReader.create();
        if (create == null) {
            create = NewProcReader.create();
        }
        sProcReader = create;
    }

    private ProcReader() {
    }

    public static boolean parseProcLineLongs(byte[] bArr, int i, int i2, int[] iArr, long[] jArr) {
        return parseProcLine(bArr, i, i2, iArr, null, jArr, null);
    }

    public static boolean readProcFileLongs(String str, int[] iArr, long[] jArr) {
        return readProcFile(str, iArr, null, jArr, null);
    }

    public static String readProcFileEntirely(String str) {
        String[] strArr = {null};
        readProcFile(str, PROC_READ_STRING_ALL_FORMAT, strArr, null, null);
        return strArr[0];
    }

    public static boolean readProcFile(String str, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        IProcReader iProcReader = sProcReader;
        if (iProcReader == null) {
            return false;
        }
        return iProcReader.readProcFile(str, iArr, strArr, jArr, fArr);
    }

    public static boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        IProcReader iProcReader = sProcReader;
        if (iProcReader == null) {
            return false;
        }
        return iProcReader.parseProcLine(bArr, i, i2, iArr, strArr, jArr, fArr);
    }

    public static boolean readProcLines(String str, String[] strArr, long[] jArr) {
        return sProcReader.readProcLines(str, strArr, jArr);
    }

    public static int getParentPid() {
        long[] jArr = new long[1];
        readProcLines("/proc/self/status", new String[]{"PPid:"}, jArr);
        return (int) jArr[0];
    }
}
