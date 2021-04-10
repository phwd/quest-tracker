package com.facebook.common.procread;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

final class OldProcReader implements IProcReader {
    private static final String TAG = OldProcReader.class.getSimpleName();
    @Nullable
    private static volatile Method sParseProcLine = null;
    private static volatile boolean sParseProcLineInited = false;
    @Nullable
    private static volatile Method sReadProcFile = null;
    private static volatile boolean sReadProcFileInited = false;
    @Nullable
    private static volatile Method sReadProcLines = null;
    private static volatile boolean sReadProcLinesInited = false;

    @Nullable
    static OldProcReader create() {
        if (Build.VERSION.SDK_INT > 25 || !init()) {
            return null;
        }
        return new OldProcReader();
    }

    OldProcReader() {
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean parseProcLine(byte[] buffer, int startIndex, int endIndex, int[] format, @Nullable String[] outStrings, @Nullable long[] outLongs, @Nullable float[] outFloats) {
        Object successObj = invoke(getParseProcLine(), buffer, Integer.valueOf(startIndex), Integer.valueOf(endIndex), format, outStrings, outLongs, outFloats);
        if (successObj == null) {
            return false;
        }
        return ((Boolean) successObj).booleanValue();
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean readProcFile(String file, int[] format, @Nullable String[] outStrings, @Nullable long[] outLongs, @Nullable float[] outFloats) {
        Object successObj = invoke(getReadProcFile(), file, format, outStrings, outLongs, outFloats);
        if (successObj == null) {
            return false;
        }
        return ((Boolean) successObj).booleanValue();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return java.lang.Boolean.FALSE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object invoke(@javax.annotation.Nullable java.lang.reflect.Method r4, java.lang.Object... r5) {
        /*
            r3 = 6
            if (r4 != 0) goto L_0x0006
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
        L_0x0005:
            return r2
        L_0x0006:
            r2 = 0
            java.lang.Object r2 = r4.invoke(r2, r5)     // Catch:{ IllegalAccessException -> 0x000c, InvocationTargetException -> 0x0036 }
            goto L_0x0005
        L_0x000c:
            r0 = move-exception
            java.lang.String r2 = com.facebook.common.procread.OldProcReader.TAG
            boolean r2 = android.util.Log.isLoggable(r2, r3)
            if (r2 == 0) goto L_0x0033
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Error (IllegalAccessException - "
            r2.<init>(r3)
            java.lang.String r3 = r0.getLocalizedMessage()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ")"
            java.lang.StringBuilder r1 = r2.append(r3)
            java.lang.String r2 = com.facebook.common.procread.OldProcReader.TAG
            java.lang.String r3 = r1.toString()
            android.util.Log.e(r2, r3, r0)
        L_0x0033:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            goto L_0x0005
        L_0x0036:
            r0 = move-exception
            java.lang.String r2 = com.facebook.common.procread.OldProcReader.TAG
            boolean r2 = android.util.Log.isLoggable(r2, r3)
            if (r2 == 0) goto L_0x0033
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Error (InvocationTargetException - "
            r2.<init>(r3)
            java.lang.String r3 = r0.getLocalizedMessage()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = ")"
            java.lang.StringBuilder r1 = r2.append(r3)
            java.lang.String r2 = com.facebook.common.procread.OldProcReader.TAG
            java.lang.String r3 = r1.toString()
            android.util.Log.e(r2, r3, r0)
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.procread.OldProcReader.invoke(java.lang.reflect.Method, java.lang.Object[]):java.lang.Object");
    }

    static boolean init() {
        return (getParseProcLine() == null || getReadProcFile() == null || getReadProcLines() == null) ? false : true;
    }

    @Nullable
    private static Method getParseProcLine() {
        if (!sParseProcLineInited) {
            sParseProcLine = initParseProcLine();
            sParseProcLineInited = true;
        }
        return sParseProcLine;
    }

    @Nullable
    private static Method getReadProcFile() {
        if (!sReadProcFileInited) {
            sReadProcFile = initReadProcFile();
            sReadProcFileInited = true;
        }
        return sReadProcFile;
    }

    @Nullable
    private static Method getReadProcLines() {
        if (!sReadProcLinesInited) {
            sReadProcLines = initReadProcLines();
            sReadProcLinesInited = true;
        }
        return sReadProcLines;
    }

    @Nullable
    private static Method initParseProcLine() {
        return initMethod("parseProcLine", byte[].class, Integer.TYPE, Integer.TYPE, int[].class, String[].class, long[].class, float[].class);
    }

    @Nullable
    private static Method initReadProcFile() {
        return initMethod("readProcFile", String.class, int[].class, String[].class, long[].class, float[].class);
    }

    @Nullable
    private static Method initReadProcLines() {
        return initMethod("readProcLines", String.class, String[].class, long[].class);
    }

    @Nullable
    private static Method initMethod(String name, Class<?>... parameterTypes) {
        try {
            return Process.class.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Warning! Could not get access to JNI method - " + name, e);
            }
            return null;
        }
    }
}
