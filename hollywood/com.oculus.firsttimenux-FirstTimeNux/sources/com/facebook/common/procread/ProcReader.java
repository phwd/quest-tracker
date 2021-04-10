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
        IProcReader procReader = OldProcReader.create();
        if (procReader == null) {
            procReader = NewProcReader.create();
        }
        sProcReader = procReader;
    }

    private ProcReader() {
    }

    public static boolean parseProcLineLongs(byte[] buffer, int startIndex, int endIndex, int[] format, long[] outLongs) {
        return parseProcLine(buffer, startIndex, endIndex, format, null, outLongs, null);
    }

    public static boolean readProcFileLongs(String file, int[] format, long[] outLongs) {
        return readProcFile(file, format, null, outLongs, null);
    }

    public static String readProcFileEntirely(String file) {
        String[] outStrings = {null};
        readProcFile(file, PROC_READ_STRING_ALL_FORMAT, outStrings, null, null);
        return outStrings[0];
    }

    public static boolean readProcFile(String fileName, int[] format, @Nullable String[] outStrings, @Nullable long[] outLongs, @Nullable float[] outFloats) {
        if (sProcReader == null) {
            return false;
        }
        return sProcReader.readProcFile(fileName, format, outStrings, outLongs, outFloats);
    }

    public static boolean parseProcLine(byte[] buffer, int startIndex, int endIndex, int[] format, @Nullable String[] outStrings, @Nullable long[] outLongs, @Nullable float[] outFloats) {
        if (sProcReader == null) {
            return false;
        }
        return sProcReader.parseProcLine(buffer, startIndex, endIndex, format, outStrings, outLongs, outFloats);
    }

    public static boolean readProcLines(String path, String[] reqFields, long[] outSizes) {
        return sProcReader.readProcLines(path, reqFields, outSizes);
    }

    public static int getParentPid() {
        long[] result = new long[1];
        readProcLines("/proc/self/status", new String[]{"PPid:"}, result);
        return (int) result[0];
    }
}
