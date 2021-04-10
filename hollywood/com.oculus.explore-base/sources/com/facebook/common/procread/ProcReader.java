package com.facebook.common.procread;

public class ProcReader {
    private static final int[] PROC_READ_STRING_ALL_FORMAT = {4096};
    private static IProcReader sProcReader;

    static {
        IProcReader procReader = OldProcReader.create();
        if (procReader == null) {
            procReader = NewProcReader.create();
        }
        sProcReader = procReader;
    }

    public static String readProcFileEntirely(String file) {
        String[] outStrings = {null};
        readProcFile(file, PROC_READ_STRING_ALL_FORMAT, outStrings, null, null);
        return outStrings[0];
    }

    public static boolean readProcFile(String fileName, int[] format, String[] outStrings, long[] outLongs, float[] outFloats) {
        if (sProcReader == null) {
            return false;
        }
        return sProcReader.readProcFile(fileName, format, outStrings, outLongs, outFloats);
    }

    public static boolean parseProcLine(byte[] buffer, int startIndex, int endIndex, int[] format, String[] outStrings, long[] outLongs, float[] outFloats) {
        if (sProcReader == null) {
            return false;
        }
        return sProcReader.parseProcLine(buffer, startIndex, endIndex, format, outStrings, outLongs, outFloats);
    }
}
