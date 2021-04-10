package com.facebook.common.procread;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class OldProcReader implements IProcReader {
    private static final String TAG = "OldProcReader";
    private static volatile Method sParseProcLine = null;
    private static volatile boolean sParseProcLineInited = false;
    private static volatile Method sReadProcFile = null;
    private static volatile boolean sReadProcFileInited = false;
    private static volatile Method sReadProcLines = null;
    private static volatile boolean sReadProcLinesInited = false;

    static OldProcReader create() {
        if (Build.VERSION.SDK_INT > 25) {
            return null;
        }
        boolean z = false;
        if (!(getParseProcLine() == null || getReadProcFile() == null)) {
            if (!sReadProcLinesInited) {
                sReadProcLines = initMethod("readProcLines", String.class, String[].class, long[].class);
                sReadProcLinesInited = true;
            }
            if (sReadProcLines != null) {
                z = true;
            }
        }
        if (z) {
            return new OldProcReader();
        }
        return null;
    }

    OldProcReader() {
    }

    @Override // com.facebook.common.procread.IProcReader
    public final boolean parseProcLine(byte[] bArr, int i, int i2, int[] iArr, String[] strArr, long[] jArr, float[] fArr) {
        Object invoke = invoke(getParseProcLine(), bArr, Integer.valueOf(i), Integer.valueOf(i2), iArr, strArr, jArr, fArr);
        if (invoke == null) {
            return false;
        }
        return ((Boolean) invoke).booleanValue();
    }

    @Override // com.facebook.common.procread.IProcReader
    public final boolean readProcFile(String str, int[] iArr, String[] strArr, long[] jArr, float[] fArr) {
        Object invoke = invoke(getReadProcFile(), str, iArr, strArr, jArr, fArr);
        if (invoke == null) {
            return false;
        }
        return ((Boolean) invoke).booleanValue();
    }

    private static Object invoke(Method method, Object... objArr) {
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

    private static Method getParseProcLine() {
        if (!sParseProcLineInited) {
            sParseProcLine = initParseProcLine();
            sParseProcLineInited = true;
        }
        return sParseProcLine;
    }

    private static Method getReadProcFile() {
        if (!sReadProcFileInited) {
            sReadProcFile = initReadProcFile();
            sReadProcFileInited = true;
        }
        return sReadProcFile;
    }

    private static Method initParseProcLine() {
        return initMethod("parseProcLine", byte[].class, Integer.TYPE, Integer.TYPE, int[].class, String[].class, long[].class, float[].class);
    }

    private static Method initReadProcFile() {
        return initMethod("readProcFile", String.class, int[].class, String[].class, long[].class, float[].class);
    }

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
