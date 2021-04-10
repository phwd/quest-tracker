package X;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public final class Sq implements AbstractC0069Ki {
    @Nullable
    public static volatile Method A00;
    public static volatile boolean A01;
    @Nullable
    public static volatile Method A02;
    @Nullable
    public static volatile Method A03;
    public static volatile boolean A04;
    public static volatile boolean A05;

    @Nullable
    public static Sq A00() {
        if (Build.VERSION.SDK_INT > 25 || A02() == null || A03() == null) {
            return null;
        }
        if (!A01) {
            A00 = A04("readProcLines", String.class, String[].class, long[].class);
            A01 = true;
        }
        if (A00 != null) {
            return new Sq();
        }
        return null;
    }

    public static Object A01(@Nullable Method method, Object... objArr) {
        if (method != null) {
            try {
                return method.invoke(null, objArr);
            } catch (IllegalAccessException e) {
                if (Log.isLoggable("OldProcReader", 6)) {
                    Log.e("OldProcReader", AnonymousClass06.A04("Error (IllegalAccessException - ", e.getLocalizedMessage(), ")"), e);
                }
            } catch (InvocationTargetException e2) {
                if (Log.isLoggable("OldProcReader", 6)) {
                    Log.e("OldProcReader", AnonymousClass06.A04("Error (InvocationTargetException - ", e2.getLocalizedMessage(), ")"), e2);
                }
            }
        }
        return Boolean.FALSE;
    }

    @Nullable
    public static Method A02() {
        if (!A04) {
            Class cls = Integer.TYPE;
            A02 = A04("parseProcLine", byte[].class, cls, cls, int[].class, String[].class, long[].class, float[].class);
            A04 = true;
        }
        return A02;
    }

    @Nullable
    public static Method A03() {
        if (!A05) {
            A03 = A04("readProcFile", String.class, int[].class, String[].class, long[].class, float[].class);
            A05 = true;
        }
        return A03;
    }

    @Nullable
    public static Method A04(String str, Class<?>... clsArr) {
        try {
            return Process.class.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            if (!Log.isLoggable("OldProcReader", 5)) {
                return null;
            }
            Log.w("OldProcReader", AnonymousClass06.A03("Warning! Could not get access to JNI method - ", str), e);
            return null;
        }
    }

    @Override // X.AbstractC0069Ki
    public final boolean A2m(byte[] bArr, int i, int i2, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        Object A012 = A01(A02(), bArr, Integer.valueOf(i), Integer.valueOf(i2), iArr, strArr, jArr, fArr);
        if (A012 == null) {
            return false;
        }
        return ((Boolean) A012).booleanValue();
    }

    @Override // X.AbstractC0069Ki
    public final boolean A37(String str, int[] iArr, @Nullable String[] strArr, @Nullable long[] jArr, @Nullable float[] fArr) {
        Object A012 = A01(A03(), str, iArr, strArr, jArr, fArr);
        if (A012 == null) {
            return false;
        }
        return ((Boolean) A012).booleanValue();
    }
}
