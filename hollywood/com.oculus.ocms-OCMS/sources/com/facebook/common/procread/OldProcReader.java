package com.facebook.common.procread;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

final class OldProcReader implements IProcReader {
    private static final String TAG = "OldProcReader";
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
    public boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        Object invoke = invoke(getParseProcLine(), bArr, Integer.valueOf(i), Integer.valueOf(i2), iArr, strArr, jArr, fArr);
        if (invoke == null) {
            return false;
        }
        return ((Boolean) invoke).booleanValue();
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean readProcFile(String str, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        Object invoke = invoke(getReadProcFile(), str, iArr, strArr, jArr, fArr);
        if (invoke == null) {
            return false;
        }
        return ((Boolean) invoke).booleanValue();
    }

    @Override // com.facebook.common.procread.IProcReader
    public boolean readProcLines(String str, String[] strArr, long[] jArr) {
        return true ^ Boolean.FALSE.equals(invoke(getReadProcLines(), str, strArr, jArr));
    }

    private static Object invoke(@Nullable Method method, Object... objArr) {
        if (method == null) {
            return Boolean.FALSE;
        }
        try {
            return method.invoke(null, objArr);
        } catch (IllegalAccessException e) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Error (IllegalAccessException - " + e.getLocalizedMessage() + ")", e);
            }
            return Boolean.FALSE;
        } catch (InvocationTargetException e2) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Error (InvocationTargetException - " + e2.getLocalizedMessage() + ")", e2);
            }
            return Boolean.FALSE;
        }
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
    private static Method initMethod(String str, Class<?>... clsArr) {
        try {
            return Process.class.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            if (!Log.isLoggable(TAG, 5)) {
                return null;
            }
            Log.w(TAG, "Warning! Could not get access to JNI method - " + str, e);
            return null;
        }
    }
}
